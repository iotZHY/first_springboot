package com.iotzzhy.first_spring_boot.service;

import com.iotzzhy.first_spring_boot.dto.PageDTO;
import com.iotzzhy.first_spring_boot.dto.QuestionDTO;
import com.iotzzhy.first_spring_boot.mapper.QuestionMapper;
import com.iotzzhy.first_spring_boot.mapper.UserMapper;
import com.iotzzhy.first_spring_boot.model.Question;
import com.iotzzhy.first_spring_boot.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//转换Question to QuestionDTO
public class PageService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PageDTO getPage(int pageNum, int size){
        int maxPage,questionNum;
        //计算最大页数
        questionNum = questionMapper.count();
        if(questionNum%size==0){
            maxPage = questionNum/size;
        }else{
            maxPage = questionNum/size + 1;
        }
        //防止页数越界
        if(pageNum<1){
            pageNum=1;
        }
        if(pageNum>maxPage){
            pageNum =maxPage;
        }

        int offset = (pageNum-1)*size;//从1开始
        List<Question> questionList = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question:questionList){
            User user = userMapper.findById(question.getCreater());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(questionDTOList);
        pageDTO.setOthers(pageNum,maxPage);
        return pageDTO;
    }
}
