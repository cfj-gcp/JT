package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class redisController {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private  Integer prot;
    @RequestMapping("do")
    @ResponseBody
    public  String  getmsg(){
        return host+":"+prot;
    }
    @RequestMapping("pages")
    public  String  getIndex(){
        return "index";
    }
    @RequestMapping("pages1")
    public  String  getIndex1(Model model){
        model.addAttribute("a",6);
        return "index";
    }
}
