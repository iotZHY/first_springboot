package com.iotzzhy.first_spring_boot.model;

import lombok.Data;

@Data
public class User {
    public int id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
