package com.jt.controller;

import com.jt.pojo.user;
import com.jt.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class userController {
    @Autowired
    private userService   userService;
    @RequestMapping("user")
    public List<user>  findAll(){
        List<user> userList = userService.findAll();
        return  userList;
    }
}
