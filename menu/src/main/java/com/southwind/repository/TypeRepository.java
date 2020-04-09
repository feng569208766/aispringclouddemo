package com.southwind.repository;

import com.southwind.entity.Menu;
import com.southwind.entity.Type;

import java.util.List;

public interface TypeRepository {
    Type findById(int id);
    List<Type> findTypes();
}
