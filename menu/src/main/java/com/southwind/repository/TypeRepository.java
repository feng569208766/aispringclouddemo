package com.southwind.repository;

import com.southwind.entity.Menu;
import com.southwind.entity.Type;

import java.util.List;

public interface TypeRepository {
    public Type findById(int id);
    public List<Type> findTypes();
}
