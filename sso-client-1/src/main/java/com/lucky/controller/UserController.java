package com.lucky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * @ClassName UserController
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2023/3/1
 * @Version 1.0
 **/
@Controller
public class UserController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 没有认证就无法访问，加上认证的逻辑
     * @param model
     * @return
     * @Version 1.0
     */
    @GetMapping("/queryUser")
    public String queryUser(Model model) {
        model.addAttribute("list", Arrays.asList("张三", "李四", "王五"));
        return "user";
    }
}
