package com.iotzzhy.first_spring_boot.model;

import lombok.Data;

@Data
public class Question {
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int creater;
    private int commentCount;
    private int readCount;
    private int likeCount;
    private String tag;
}
