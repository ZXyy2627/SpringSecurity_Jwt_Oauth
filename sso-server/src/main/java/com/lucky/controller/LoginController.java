package com.lucky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * @ClassName LoginController
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2023/3/1
 * @Version 1.0
 **/
@Controller
public class LoginController {


    /**
     * 跳转到登录界面
     * @param url
     * @param model
     * @return
     */
    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(value = "redirect",required = false)String url, Model model) {
        model.addAttribute("url", url);
        return "login";
    }

    /**
     * 处理登录请求
     * @param userName
     * @param password
     * @param url
     * @return
     */
    @PostMapping("/ssoLogin")
    public String login(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam(value = "url", required = false) String url) {
        if ("zhang".equals(userName) && "123".equals(password)) {
            //如果登录成功，生成一个token
            String uuid = UUID.randomUUID().toString().replace("-", "");
            //把生成的信息放到redis中

            //登录成功
            return "redirect:" + url+"?token="+uuid;
        }
        return "redirect:loginPage";
    }
}
