package com.jt.util;

import com.jt.pojo.user;

public class userThreadLocal {
//    在同一线程内有效
    private   static   ThreadLocal<user>  userThreadLocal=new ThreadLocal<>();
    public   static   void  set(user  user){
        //存
        userThreadLocal.set(user);
    }
    public   static  user  get(){
        //取
        return   userThreadLocal.get();
    }
    public   static  void   remove(){
        //删除
        userThreadLocal.remove();
    }
}
