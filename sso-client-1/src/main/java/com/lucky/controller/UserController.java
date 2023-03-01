package com.lucky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    public String queryUser(
            Model model,
            HttpSession session,
            @RequestParam(value = "token" ,required = false) String token) {
        if (token != null && !"".equals(token)) {
            //token有值，说明认证了,把用户信息放到session中
            session.setAttribute("userLogin","zhang");
        }
        Object userLogin = session.getAttribute("userLogin");
        if (!ObjectUtils.isEmpty(userLogin)) {
            //说明登录过了，就直接放过
            model.addAttribute("list", Arrays.asList("张三", "李四", "王五"));
            return "user";
        }
        return "redirect:http://sso-server.com:8080/loginPage?redirect=http://client1.com:8081/queryUser";
    }
}
