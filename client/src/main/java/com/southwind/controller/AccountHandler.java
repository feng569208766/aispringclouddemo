package com.southwind.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.southwind.entity.Admin;
import com.southwind.entity.User;
import com.southwind.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private AccountFeign accountFeign;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session) {
        Object login = accountFeign.login(username, password, type);
        LinkedHashMap<String,Object> hashMap = (LinkedHashMap) login;
        System.out.println("client客户端的login" + JSON.toJSONString(login));
        System.out.println("client客户端的login" + login);
        String result = null;
        if (login == null) {
            result = "login";
        }else {
            JSONObject jsonObject = new JSONObject(hashMap);
            switch (type) {
                case "user":
                    jsonObject = new JSONObject(hashMap);
                    User user = jsonObject.toJavaObject(User.class);
                    session.setAttribute("user",user);
                    result = "index";
                    break;
                case "admin":
                    jsonObject = new JSONObject((hashMap));
                    Admin admin = jsonObject.toJavaObject(Admin.class);
                    session.setAttribute("admin",admin);
                    result = "main";
                    break;
                default:
            }
        }
        return result;
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }

}
