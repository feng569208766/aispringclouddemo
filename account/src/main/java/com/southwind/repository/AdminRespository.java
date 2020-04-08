package com.southwind.repository;

import com.southwind.entity.Admin;

public interface AdminRespository {

    public Admin login(String username, String password);
}
