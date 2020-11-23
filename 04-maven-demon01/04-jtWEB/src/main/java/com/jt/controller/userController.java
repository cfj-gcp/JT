package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.user;
import com.jt.service.DubboUserService;
import com.jt.service.userService;
import com.jt.vo.SysResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService  userService;
    @Reference(check = false)//启动消费者时不去校验是否有生产者
    private DubboUserService  dubboUserService;
    @Autowired
    private JedisCluster jedisCluster;
    /**
     * 实现页面登录、注册的页面跳转
     */
    @RequestMapping("/{moduleName}")
    public  String module(@PathVariable String moduleName){
        return  moduleName;
    }

    @RequestMapping("/testHttpClient")
    @ResponseBody
    public List<user>  testHttpClient(){
        return  userService.testHttpClient();
    }

    /**
     * 完成用户的注册
     *  http://www.jt.com/user/doRegister
     *  请求参数
     *  password: 3vfqV9jVwf3w3F
     * username: admin1234
     * phone: 18674079522
     * 返回值类型：SysResult对象
     *
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult  saveUser(user  user){
        dubboUserService.saveUser(user);
        return   SysResult.success();
    }
    /**
     * 业务：完成用户登录操作
     * url地址：http://www.jt.com/user/doLogin?r=0.49067880160718635
     * 参数：username: admin123
     *          password: 3vfqV9jVwf3w3F
     *          返回值：sysresult
     */
    @RequestMapping("doLogin")
    @ResponseBody
    public   SysResult  doLogin(user user, HttpServletResponse response){
//        String uuid="abc";
        String uuid=dubboUserService.doLogin(user);
        if(StringUtils.isEmpty(uuid)){
            return  SysResult.fail();
        }
//    将uuid保存到cookie
        Cookie cookie= new Cookie("JT_TICKET",uuid);
        cookie.setMaxAge(30*24*60*60);//30天有效
        cookie.setPath("/");//cookie子那个url路径生效
        cookie.setDomain("jt.com");//设定cookie共享
        response.addCookie(cookie);
        return   SysResult.success();
    }
    /**
     * 完成用户退出操作
     * url 地址：http：//www.jt.com/user/logout.html
     * 参数：没有参数
     * 返回值：重定向到系统首页
     *业务：
     * 1.删除redis
     * 2.删除cookie
     *
     */
    @RequestMapping("/logout")
    public   String  logout(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("JT_TICKET")){
                    String ticket=cookie.getValue();
//                    redis 删除ticket信息
                    jedisCluster.del(ticket);
                    cookie.setMaxAge(0);//0表示立即删除
//                   规则cookie如果需要操作，必须严格定义
                    cookie.setPath("/");
                    cookie.setDomain("jt.com");
                    response.addCookie(cookie);
                }
            }

            }
        return  "redirect:/";
        }

    }











