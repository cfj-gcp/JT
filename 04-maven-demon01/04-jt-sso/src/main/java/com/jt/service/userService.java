package com.jt.service;

import com.jt.pojo.user;

import java.util.List;

public interface userService {
     List<user> findAll();

    boolean checkUser(String param, Integer type);
}
