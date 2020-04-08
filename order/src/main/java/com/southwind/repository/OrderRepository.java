package com.southwind.repository;

import com.southwind.entity.Order;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);
    public List<Order> findAllByUid(int uid,int index,int limit);
    public int countByUid(int uid);
    public List<Order> findAll(int index,int limit);
    public void updateState(long id);
    public int count();
}
