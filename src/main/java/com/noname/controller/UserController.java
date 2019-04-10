package com.noname.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by noname on 2019/4/10.
 */
@Controller
@RequestMapping
public class UserController {


    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("login-error")
    public String loginError() {
        return "login-error";
    }

    /**
     * 该方法是用于返回security内置的用户信息的，即访问这个路径后会返回类似如下，一般不需要
     * {"username":"admin","password":"123456","accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true,"authorities":[{"authority":"ROLE_ADMIN"}]}
     * @return
     */
    @RequestMapping("/whoim")
    @ResponseBody
    public Object whoIm(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
