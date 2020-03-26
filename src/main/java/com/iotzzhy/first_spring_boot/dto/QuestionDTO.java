package com.iotzzhy.first_spring_boot.dto;

import com.iotzzhy.first_spring_boot.model.User;
import lombok.Data;

@Data
//与前端交互
public class QuestionDTO {
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private User user;
    private int commentCount;
    private int readCount;
    private int likeCount;
    private String tag;
}
