package com.iotzzhy.first_spring_boot.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String login;
    private long id;
    private String bio;
    private String avatar_url;
}
