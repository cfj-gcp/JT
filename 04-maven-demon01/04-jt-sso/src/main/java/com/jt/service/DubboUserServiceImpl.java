package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.userMapper;
import com.jt.pojo.user;
import com.jt.util.objectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import redis.clients.jedis.JedisCluster;

import java.util.UUID;

@Service(timeout = 3000)
public class DubboUserServiceImpl implements  DubboUserService{
    @Autowired
    private userMapper usermapper;
@Autowired
private JedisCluster  jedisCluster;
    @Override
    public void saveUser(user user) {
        //密码采用md5方式进行加密
           String password=  user.getPassword();
      String md5Pass=  DigestUtils.md5DigestAsHex(password.getBytes());
      user.setEmail(user.getPhone()).setPassword(md5Pass);
        usermapper.insert(user);
    }

    /**
     * 1.根据用户名和密码查询后端服务器数据
     *     将密码进行加密
     * @param user
     * @return
     */
    @Override
    public String doLogin(user user) {
     String md5Pass=   DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
      user.setPassword(md5Pass);
      QueryWrapper<user> queryWrapper= new QueryWrapper<>(user);
//       根据对象不为空的属性，充当where条件
        user userDB=usermapper.selectOne(queryWrapper);
        if(userDB==null){
//根据用户和密码错误
            return null;
        }
//        开始实现单点登录业务操作
 String  uuid= UUID.randomUUID().toString()
         .replace("-", "");
        userDB.setPassword("123456你信么");//去除有效信息
        String  userJson= objectMapperUtil.tojson(userDB);
         jedisCluster.setex(uuid, 30*24*60*60, userJson);
        return uuid;
    }
}
