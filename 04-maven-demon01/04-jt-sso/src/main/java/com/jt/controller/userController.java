package com.jt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.user;
import com.jt.service.userService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import java.util.List;

@RestController
public class userController {
    @Autowired
    private userService   userService;
    @Autowired
    private JedisCluster   jedisCluster;
    /**
     * 业务需求：通过http://sso.jt.com/findAll获取所有的user表数据然后返回json
     */
    @RequestMapping("/findAll")
    public List<user> findAll(){
        return   userService.findAll();
    }
    /**
     * 需求：实现用户的信息校验
     * 校验步骤：需要接收用户的请求，之后利用restful获取数据
     * 实现数据库的校验，按照jsonp的方式返回数据
     * url地址：Request URL: http://sso.jt.com/user/check/
     *              adimin-54545/1?r=0.9698084230971407&callback=jsonp1605667622836&_=160566765034
     *         参数：
     *         返回值：jsonpObject
     */
    @RequestMapping("/user/check/{param}/{type}")
    public JSONPObject checkUser(@PathVariable String param, @PathVariable Integer type, String callback){
//       只需要校验数据库中是否有结果
        boolean  flag=userService.checkUser(param,type);
        SysResult sysResult = SysResult.success(flag);
//        int i=1/0;
      return  new  JSONPObject(callback, sysResult);
    }
    /**
     * http://sso.jt.com/user/testHttpClient
     * 返回list<user>
     */
    @RequestMapping("/user/testHttpClient")
    public  List<user>  testHttpClien(){
        return  userService.findAll();
    }
    /**
     * 业务说明：通过跨域请求方式，获取用户的json数据
     * url：http://sso.jt.com/user/query/3953fc88662e469e91707d6339f1d36a?callback=jsonp1605859032077&_=1605859032191
     * 请求参数：ticket
     * 返回值：sysresultd对象userjson
     * 对象
     */
    @RequestMapping("/user/query/{ticket}")
    public  JSONPObject   findUserByTicket(@PathVariable String ticket,String callback){
      String userJSON= jedisCluster.get(ticket);
        if(StringUtils.isEmpty(userJSON)){
            return new JSONPObject(callback, SysResult.fail());
        }else{
            return   new JSONPObject(callback, SysResult.success(userJSON));
        }
    }



}



















