package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.userDao;
import com.jt.pojo.user;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
@SpringBootTest
public class userTest {
    @Autowired
    private userDao  u;
    //根据主键查询
    @Test
    public void selectUser01(){
        user user = u.selectById(21);
        System.out.println(user);//通过id查询
        System.out.println(u.selectCount(null));//查询总记录数
//        查询age=18性别为 女    wehere  age=18  and sex="女"
//    where  条件构造器
        QueryWrapper<user> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.gt("age", 18)
                .lt("age", 2000)
                .eq("sex", "女");
        List<user> users = u.selectList(QueryWrapper);
        System.out.println(users);
//        查询 id=3,5,7,9
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(3);
//        list.add(5);
//        list.add(7);
//        list.add(9);
        Integer[] ids={1,3,5,7,9};
        List<Integer> idlist = Arrays.asList(ids);//数组转化成集合
        List<com.jt.pojo.user> users1 = u.selectBatchIds(idlist);
        System.out.println(users1);
//    如果需要获取表中的第一列主键信息
        System.out.println("---------");
          QueryWrapper<user>    a=  new QueryWrapper<>();
                                a.select("id","name","age");
        List<Object> list = u.selectObjs(a);
        System.out.println(list);
    }
    @Test
    public  void  insertTest(){
        user user = new user();
        user.setName("上海名媛")
                .setAge(30)
                .setSex("女");
        int n = u.insert(user);
        System.out.println(n);
    }
    @Test
    public  void  updateTest(){
//        通过主键来修改
//        user user = new user();
//        user.setId(53)
//                .setName("北京大爷");
//        u.updateById(user);
//        通过参数值来修改
        user user = new user();
        user.setName("上海交际花");
        QueryWrapper<user> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("name", "北京大爷");
        u.update(user, updateWrapper);

    }
    @Test
    public  void  deleteTest(){
//        u.deleteById(53);//根据主键删除
//        u.deleteBatchIds("id集合信息")
        //根据出主键之外的其他数据信息删除
        QueryWrapper<user> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "上海交际花");
        u.delete(wrapper);
    }
    @Test
    public  void  test(){
        user user = new user();
        user.setId(13)
                .setAge(20)
                .setName("libai");

    }

}
