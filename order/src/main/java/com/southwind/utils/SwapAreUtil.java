package com.southwind.utils;

import com.southwind.entity.SwapAreEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static java.lang.Math.sin;

@Component
public class SwapAreUtil {

    private static SwapAreEntity swapAreEntity;
    private static final String treadKey = (String) new ThreadLocal<>().get();
    private static final HashMap<String,Object> map = new HashMap<>();

    public static void setSwap(String s ,Object object){
        map.put(s,object);

    }

    @Override
    public void finalize(){
        System.out.println("111");
    }

    public static Object getSwap(String s){
        return map.get(s);
    }

    public static void main(String[] args) throws Throwable {
        SwapAreUtil swapAreUtil = new SwapAreUtil();
        swapAreUtil.finalize();
        System.gc();
    }


}
