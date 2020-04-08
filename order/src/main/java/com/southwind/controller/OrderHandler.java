package com.southwind.controller;

import com.southwind.entity.Order;
import com.southwind.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2020/3/12 0012.
 */
@RestController
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private OrderRepository orderRepository;

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return "order的端口："+this.port;
    }

    @PostMapping("/save")
    public void save(@RequestBody Order order){
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @GetMapping("/findAllByUid/{uid}/{index}/{limit}")
    public List<Order> findAllByUid(@PathVariable("uid") int uid,@PathVariable("index") int index,@PathVariable("limit") int limit){
        return orderRepository.findAllByUid(uid,index,limit);
    }

    @GetMapping("/countByUid/{uid}")
    public int countByUid(@PathVariable("uid") int uid){
        return orderRepository.countByUid(uid);
    }

    @GetMapping("/findAll/{index}/{limit}")
    public List<Order> findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        return orderRepository.findAll(index,limit);
    }

    @GetMapping("/updateState/{id}")
    public void updateState(@PathVariable("id") long id){
        orderRepository.updateState(id);
    }

    @GetMapping("/count")
    public int count(){
        return orderRepository.count();
    }
}
