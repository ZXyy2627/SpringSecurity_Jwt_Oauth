package com.lucky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @ClassName OrderController
 * @Description 请描述类的业务用途
 * @Author zhangxiang
 * @Date 2023/3/1
 * @Version 1.0
 **/
@Controller
public class OrderController {

    @GetMapping("/order")
    public String getOrder(HttpSession session, Model model){
        Object userLogin = session.getAttribute("userLogin");
        if(userLogin != null){
            // 说明认证了
            model.addAttribute("list", Arrays.asList("order1","order2","order3"));
            return "order";
        }
        return "redirect:http://sso.server.com:8080/loginPage?redirect=http://client2.com:8082/order";
    }
}
