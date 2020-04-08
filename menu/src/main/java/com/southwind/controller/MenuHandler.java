package com.southwind.controller;

import com.alibaba.fastjson.JSON;
import com.southwind.entity.Menu;
import com.southwind.entity.MenuVo;
import com.southwind.entity.Type;
import com.southwind.repository.MenuRepository;
import com.southwind.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2020/3/12 0012.
 */
@RestController
@RequestMapping("/menu")
public class MenuHandler {

    @Value("${server.port}")
    private String port;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/index")
    public String index(){
        return "menu的端口："+this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    public MenuVo findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        List<Menu> all = menuRepository.findAll(index,limit);
        int count = menuRepository.count();
        MenuVo menuVo = new MenuVo(0, "", count, all);
        System.out.println("menu返回的结果："+JSON.toJSONString(menuVo));
        return menuVo;
    }

    @GetMapping("/deleteById/{id}")
    @ResponseBody
    public void deleteById(@PathVariable("id") long id){
        menuRepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes(){
        List<Type> types = typeRepository.findTypes();
        System.out.println("menu的findTypes返回的结果："+JSON.toJSONString(types));
        return types;
    }

    @PostMapping("/save")
    public void save(@RequestBody Menu menu){
        menuRepository.save(menu);
    }

    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id){
        return menuRepository.findById(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody Menu menu){
        menuRepository.update(menu);
    }
}
