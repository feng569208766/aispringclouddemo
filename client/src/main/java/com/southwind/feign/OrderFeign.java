package com.southwind.feign;

import com.southwind.entity.Menu;
import com.southwind.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(value = "order")
public interface OrderFeign {

    @GetMapping("/order/index")
    public String index();

    @PostMapping("/order/save")
    public void save(Order order);

    @GetMapping("/order/findAllByUid/{uid}/{index}/{limit}")
    public List<Order> findAllByUid(@PathVariable("uid") int uid,@PathVariable("index") int index,@PathVariable("limit") int limit);

    @GetMapping("/order/countByUid/{uid}")
    public int countByUid(@PathVariable("uid") int uid);

    @GetMapping("/order/findAll/{index}/{limit}")
    public List<Order> findAll(@PathVariable("index") int index,@PathVariable("limit") int limit);

    @GetMapping("/order/updateState/{id}")
    public void updateState(@PathVariable("id") long id);

    @GetMapping("/order/count")
    public int count();
}
