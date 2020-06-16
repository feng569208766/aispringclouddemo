package com.southwind.controller;

import com.southwind.entity.Order;
import com.southwind.entity.SwapAreEntity;
import com.southwind.repository.OrderRepository;
import com.southwind.utils.SwapAreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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

    @GetMapping("/count1")
    public int count1(){
        System.out.println("执行中");
//        SwapAreEntity swapAreEntity = new SwapAreEntity();
//        swapAreEntity.setPath("QUE");
//        swapAreEntity.setValue("shuzi1");
        SwapAreUtil.setSwap("QUE","shuzizi");
        //return orderRepository.count();
        return 1;
    }

    @GetMapping("/count2")
    public int count2(){
        System.out.println("执行中");
//        SwapAreEntity swapAreEntity = new SwapAreEntity();
//        swapAreEntity.setPath("QUE");
//        swapAreEntity.setValue("shuzi1");
        Object swap = SwapAreUtil.getSwap("QUE");
        System.out.println("交换区数据"+swap);
        //return orderRepository.count();
        return 1;
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("测试");
        return "6";
    }

    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println("1111111111");
        System.out.println(System.getProperty("user. "));
        System.out.println("22222222222222");
        System.out.println(
                System.getProperty("java.library.path")
        );
    }

}
