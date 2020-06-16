package com.southwind.beans;

import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class testBean implements InitializingBean {

    private Map<String,ServiceMeta> map = new ConcurrentHashMap();
    @Override
    public void afterPropertiesSet() throws Exception {
        ServiceMeta meta = new ServiceMeta();
        meta.setBeanId("order1");
        meta.setHttpRequestMethod("POST");
        meta.setMethod("test1");
        map.put("/order1|@|POST",meta);
    }
}
