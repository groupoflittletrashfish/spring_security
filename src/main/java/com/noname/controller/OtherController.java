package com.noname.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by noname on 2019/4/10.
 */

@RequestMapping("other")
@Controller
public class OtherController {


    @RequestMapping("getValue")
    @ResponseBody
    public String getValue(){
        return "检查spring security 是否可以登录拦截";
    }
}
