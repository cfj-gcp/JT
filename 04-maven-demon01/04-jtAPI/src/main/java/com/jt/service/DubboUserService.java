package com.jt.service;

import com.jt.pojo.user;

public interface DubboUserService {

    void saveUser(user user);

    String doLogin(user user);
}
