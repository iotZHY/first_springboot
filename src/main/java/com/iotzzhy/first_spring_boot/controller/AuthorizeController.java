package com.iotzzhy.first_spring_boot.controller;

import com.iotzzhy.first_spring_boot.dto.AccessTokenDTO;
import com.iotzzhy.first_spring_boot.dto.GithubUser;
import com.iotzzhy.first_spring_boot.mapper.UserMapper;
import com.iotzzhy.first_spring_boot.model.User;
import com.iotzzhy.first_spring_boot.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String clientUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(clientUri);
        accessTokenDTO.setState(state);
        String token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(token);
        if(githubUser!=null){
            //模拟session添加，实际加入数据库
            String token_cookie = UUID.randomUUID().toString();
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getLogin());
            user.setToken(token_cookie);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userMapper.insert(user);
            //添加cookie
            response.addCookie(new Cookie("token",token_cookie));
        }
        return "redirect:/";
    }
}
