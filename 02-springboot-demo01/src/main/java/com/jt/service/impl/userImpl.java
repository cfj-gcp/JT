package com.jt.service.impl;

import com.jt.mapper.userDao;
import com.jt.pojo.user;
import com.jt.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userImpl  implements userService {
   @Autowired
   private userDao user;

   @Override
   public List<user> findAll() {
//      List<user> u= user.findAll();
//      return u;
      return user.selectList(null);
   }
}
