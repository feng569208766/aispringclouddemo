package com.southwind.controller;

import com.southwind.entity.User;
import com.southwind.entity.UserVo;
import com.southwind.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    @ResponseBody
    public String index(){
        System.out.println("端口"+port);
        return "server的端口"+port;
    }

    /*@GetMapping("/findAll/{index}/{limit}")
    @ResponseBody
    public List<User> findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        return userFeign.findAll(index,limit);
    }*/

    @GetMapping("/findAll")
    @ResponseBody
    public UserVo findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        int index = (page-1)*limit;
        UserVo userVo = new UserVo();
        userVo.setCode(0);
        userVo.setMsg("");
        userVo.setCount(userFeign.count());
        userVo.setData(userFeign.findAll(index,limit));
        return userVo ;
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public User findById(@PathVariable("id") long id){
        return userFeign.findById(id);
    }

    @GetMapping("/count")
    @ResponseBody
    public int count(){
        return userFeign.count();
    }

    @PostMapping("/save")
    //@ResponseBody
    public String save(User user){
        user.setRegisterdate(new Date());
        userFeign.save(user);
        return "redirect:/menu/redirect/user_manage";
    }

    @PutMapping("/update")
    @ResponseBody
    public void update(@RequestBody User user){
        userFeign.update(user);
    }

    @GetMapping("/deleteById/{id}")
    //@ResponseBody
    public String deleteById(@PathVariable long id){
        userFeign.deleteById(id);
        return "redirect:/menu/redirect/user_manage";
    }
}
