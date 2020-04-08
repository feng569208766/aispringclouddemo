package com.southwind.controller;

import com.alibaba.fastjson.JSON;
import com.southwind.entity.Menu;
import com.southwind.entity.MenuVo;
import com.southwind.entity.Type;
import com.southwind.feign.MenuFeign;
import com.southwind.feign.OrderFeign;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    private MenuFeign menuFeign;

    @Autowired
    private OrderFeign orderFeign;


    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        System.out.println("端口"+port);
        return "server的端口"+port;
    }

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }

    @GetMapping("/findAll")
    @ResponseBody
    public MenuVo findAll(@RequestParam("page") int page, @RequestParam("limit") int limit){
        System.out.println("开始menu请求"+page+"-"+limit);
        int index = (page-1)*limit;
        MenuVo all = menuFeign.findAll(index, limit);
        System.out.println("返回的值："+ JSON.toJSONString(all));
        return all;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        menuFeign.deleteById(id);
        return "redirect:/menu/redirect/menu_manage";
    }


    @GetMapping("/find")
    public String find(){
        System.out.println("开始");
        String index = orderFeign.index();
        System.out.println("端口："+index);
        return index;
    }

    @GetMapping("/findTypes")
    public ModelAndView findTypes(){
        List<Type> types = menuFeign.findTypes();
        System.out.println("client返回的findTypes是："+JSON.toJSONString(types));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_add");
        modelAndView.addObject("list",types);
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(Menu menu){
        menuFeign.save(menu);
        return "redirect:/menu/redirect/menu_manage";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_update");
        modelAndView.addObject("menu",menuFeign.findById(id));
        modelAndView.addObject("list",menuFeign.findTypes());
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(Menu menu){
        menuFeign.update(menu);
        return "redirect:/menu/redirect/menu_manage";
    }

}
