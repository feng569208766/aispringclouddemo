package com.southwind.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Component
public class urlAspectException {

    public void process(){
        int i = 1;
        HashMap<String, String> hashMap = new HashMap<>();
        System.out.println("url有问题");
    }
}
