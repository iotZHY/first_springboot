package com.iotzzhy.first_spring_boot.controller;

import com.iotzzhy.first_spring_boot.dto.PageDTO;
import com.iotzzhy.first_spring_boot.mapper.UserMapper;
import com.iotzzhy.first_spring_boot.model.User;
import com.iotzzhy.first_spring_boot.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PageService pageService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "curPage",defaultValue = "1") int curPage,
                        @RequestParam(name = "size", defaultValue = "2") int size
    ){
        //取出cookie中的token与数据库中对比，保证用户的登录态
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if("token".equals(cookie.getName())){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                }
            }
        }
        PageDTO pageDTO = pageService.getPage(curPage,size);
        model.addAttribute("page",pageDTO);
        return "index";
    }
}
