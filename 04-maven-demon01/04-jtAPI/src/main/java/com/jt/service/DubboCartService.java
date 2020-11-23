package com.jt.service;

import com.jt.pojo.cart;

import java.util.List;

public interface DubboCartService {
    List<cart> findCartListByUserId(Long userID);

    void updateCartNum(cart cart);

    void deleteCart(cart cart);

    void addCart(cart cart);
}
