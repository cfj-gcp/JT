package com.jt.service.impl;

import com.jt.mapper.userDao;
import com.jt.pojo.user;
import com.jt.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl  implements userService {
    @Autowired
    private userDao  userDao;

    @Override
    public List<user> findAll() {
        return userDao.selectList(null);
    }
}
