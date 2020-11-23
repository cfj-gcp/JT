package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.cartMapper;
import com.jt.pojo.cart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 3000)
public class DubboCartServicelImpl implements   DubboCartService {
    @Autowired
    private cartMapper  cartMapper;

    @Override
    public List<cart> findCartListByUserId(Long userID) {
        QueryWrapper<cart>  queryWrapper= new QueryWrapper<>();
          queryWrapper.eq("user_id", userID);
        return cartMapper.selectList(queryWrapper);
    }

    /**
     * sql:update  tb_cart set num=#{num},updated=#{update}
     * where user_id=#{userId} and item_id=#{itemId}
     *
     * @param cart
     */
    @Override
    public void updateCartNum(cart cart) {
        cart  cartTemp= new cart();
              cartTemp.setNum(cart.getNum());
              QueryWrapper<cart>  queryWrapper=new QueryWrapper<>();
              queryWrapper.eq("user_id", cart.getUserId())
              .eq("item_id", cart.getItemId());
          cartMapper.update(cartTemp,queryWrapper);
    }

    @Override
    public void deleteCart(cart cart) {
        cartMapper.delete(new QueryWrapper<>(cart));//根据对象中不为null的属性当做where条件
    }

    /**
     * 如果购物车已经存在，则更新数量，否则新增
     * 如何判断购物车数据是否存在  userId  itemId
     *
     * @param cart
     */
    @Override
    public void addCart(cart cart) {
//1.查询购物车信息 userid  itemid
        QueryWrapper<cart>  queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id", cart.getUserId())
          .eq("item_id", cart.getItemId());
        cart  cartDB=cartMapper.selectOne(queryWrapper);
        if(cartDB==null){
//第一次新增购物车
            cartMapper.insert(cart);
        }else {
//            用户已添加，更新数量
            int  num=cartDB.getNum()+cart.getNum();
            cart  cartTemp=new cart();
            cartTemp.setNum(num).setId(cartDB.getId());
            cartMapper.updateById(cartTemp);
        }
    }
}

