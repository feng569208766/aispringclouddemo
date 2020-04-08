package com.southwind.repository;

import com.southwind.entity.User;

import java.util.List;

public interface UserRepository {

    public User login(String username, String password);
}
