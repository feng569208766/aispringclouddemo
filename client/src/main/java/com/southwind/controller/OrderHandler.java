package com.southwind.controller;

import com.alibaba.fastjson.JSON;
import com.southwind.entity.*;
import com.southwind.feign.MenuFeign;
import com.southwind.feign.OrderFeign;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderFeign orderFeign;

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index() {
        System.out.println("端口" + port);
        return "server的端口" + port;
    }

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") int mid, HttpSession session) {
        Order order = new Order();
        User user = (User) session.getAttribute("user");
        order.setUser(user);
        Menu menu = new Menu();
        menu.setId(mid);
        order.setMenu(menu);
        order.setState(0);
        orderFeign.save(order);
        return "order";
    }

    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVo findAllByUid(@RequestParam("page") int page, @RequestParam("limit") int limit, HttpSession session) {
        int index = (page - 1) * limit;
        User user = (User) session.getAttribute("user");
        int uid = (int) user.getId();
        int count = orderFeign.countByUid(uid);
        List<Order> orders = orderFeign.findAllByUid(uid, index, limit);
        OrderVo orderVo = new OrderVo();
        orderVo.setCount(count);
        orderVo.setMsg("");
        orderVo.setData(orders);
        return orderVo;
    }

    @GetMapping("/findAll")
    @ResponseBody
    public OrderVo findAll(@RequestParam("page") int page,@RequestParam int limit){
        int index = (page - 1) * limit;
        List<Order> orders = orderFeign.findAll(index, limit);
        OrderVo orderVo = new OrderVo();
        orderVo.setCount(orderFeign.count());
        orderVo.setMsg("");
        orderVo.setData(orders);
        return orderVo;
    }

    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") int id){
        orderFeign.updateState(id);
        return "redirect:/menu/redirect/order_handler";
    }

}