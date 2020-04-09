package com.southwind.controller;

import com.alibaba.fastjson.JSON;
import com.southwind.entity.Admin;
import com.southwind.entity.User;
import com.southwind.repository.AdminRespository;
import com.southwind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private AdminRespository adminRespository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("type") String type) {
        System.out.println("account开始登陆操作");
        Object object = null;
        switch (type) {
            case "user":
                object = userRepository.login(username, password);
                break;
            case "admin":
                object = adminRespository.login(username, password);
                break;
            default:
        }
        return object;
    }
}
