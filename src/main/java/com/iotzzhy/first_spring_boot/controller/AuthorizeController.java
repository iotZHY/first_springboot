package com.iotzzhy.first_spring_boot.controller;

import com.iotzzhy.first_spring_boot.dto.AccessTokenDTO;
import com.iotzzhy.first_spring_boot.dto.GithubUser;
import com.iotzzhy.first_spring_boot.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("9b5498ade1e9c7b97078");
        accessTokenDTO.setClient_secret("0bbf52ef27722d26d08832f7f26a8d28fa9b5a09");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(token);
        System.out.println(githubUser.getId()+githubUser.getLogin()+githubUser.getBio());
        return "index";
    }
}
