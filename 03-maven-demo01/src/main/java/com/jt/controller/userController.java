package com.jt.controller;
import com.jt.pojo.user;
import com.jt.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class userController {
    @Autowired
    private userService   userService;
    @RequestMapping("findAll")
    public   String  user(Model model){
        System.out.println("我是查询");
        List<user> userList= userService.findAll();
        model.addAttribute("userList",userList);
        return "userList";
    }
    @RequestMapping("/ajax")
    public  String  ajax(){
        return  "ajax";
    }
    @RequestMapping("/find")
    @ResponseBody
    public List<user>  findId(Integer id){
       //List<user> a = userService.findAll();
        return userService.findAll();
    }
}
