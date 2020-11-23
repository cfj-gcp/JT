package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.userMapper;
import com.jt.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class userServiceImpl implements  userService{
    @Autowired
    private userMapper  userMapper;
    private  static Map<Integer,String> map=new HashMap<>();
    static {
        map.put(1,"username");
        map.put(2,"phone");
        map.put(3,"email");
    }

    @Override
    public List<user> findAll() {
        return userMapper.selectList(null);
    }
//校验数据库中是否有数据
    @Override
    public boolean checkUser(String param, Integer type) {
        String  column=map.get(type);
        QueryWrapper<user> qu = new QueryWrapper<>();
        qu.eq(column, param);
        int count = userMapper.selectCount(qu);
        return count>0?true:false;
    }
}
