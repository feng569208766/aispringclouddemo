package com.southwind.beans;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component("order1")
public class order1 {
    private String name;

    public void test1(){
        System.out.println("进入test1");
    }

}
