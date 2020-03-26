package com.iotzzhy.first_spring_boot.controller;

import com.iotzzhy.first_spring_boot.mapper.QuestionMapper;
import com.iotzzhy.first_spring_boot.model.Question;
import com.iotzzhy.first_spring_boot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper  questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model
    ){
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");


        if(user==null){
            model.addAttribute("error","用户未登录");
            model.addAttribute("title",title);
            model.addAttribute("description",description);
            model.addAttribute("tag",tag);
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreater(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
