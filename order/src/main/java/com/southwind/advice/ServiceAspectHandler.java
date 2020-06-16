package com.southwind.advice;


import com.alibaba.fastjson.JSON;
import com.southwind.beans.ServiceMeta;
import com.southwind.utils.SpringContexUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Order(value = 2)
@Component
public class ServiceAspectHandler {

    @Autowired
    private static ApplicationContext applicationContext;

    @Pointcut("execution( * com.southwind.controller.OrderHandler.*())")
    //@Pointcut("bean(*order1)")
    private void requestServiceException() {

    }

    @AfterThrowing(value = "requestServiceException()" ,throwing = "e")
    public void afterThrowing(JoinPoint pjp, Exception e) {
        System.out.println("Order2的--afterThrowing报错了");
    }

   @Around("requestServiceException()")
    public Object requestServiceExcAround(ProceedingJoinPoint pjp) throws Throwable {
        Map<String, ServiceMeta> map = new ConcurrentHashMap();
        ServiceMeta meta = new ServiceMeta();
        meta.setBeanId("order1");
        meta.setHttpRequestMethod("POST");
        meta.setMethod("test1");
        map.put("/order1|@|POST", meta);

        Object result = null;

        ApplicationContext context = SpringContexUtil.getApplicatonContext();
        String[] beans = context.getBeanDefinitionNames();
        List<String> beanList = Arrays.asList(beans);
        Collections.sort(beanList);
        System.out.println(JSON.toJSONString(beanList));

        if (SpringContexUtil.getBean("order1").equals(null) || SpringContexUtil.getBean("serviceMeta").equals(null)) {
            System.out.println("没有注入");
        }

        System.out.println("order2开始切面");
        /*Method method = ClassUtils.getMethod(SpringContexUtil.getBean("order1").getClass(), "test1");
        result = method.invoke(SpringContexUtil.getBean("order1"));*/
       Object proceed = null;
       proceed = pjp.proceed();
       System.out.println("order2结束切面");
        return proceed;
    }
}
