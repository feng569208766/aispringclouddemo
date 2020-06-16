package com.southwind.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class Springfactory implements BeanFactoryAware {
    private static BeanFactory beanFactory;
    // private static ApplicationContext context;
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        this.beanFactory = factory;
    }
    /**
     * 根据beanName名字取得bean
     *
     * @param beanName
     * @return
     */
    public static <T> T getBean(String beanName) {
        if (null != beanFactory) {
            return (T) beanFactory.getBean(beanName);
        }
        return null;
    }

    public static void main(String[] args) {
//        Object order1 = Springfactory.getBean("serviceMeta");
//        System.out.println(order1);


    }
}
