package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.cart;
import com.jt.pojo.user;
import com.jt.service.DubboCartService;
import com.jt.util.userThreadLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class cartController {
    @Reference(check = false)
    private DubboCartService  cartService;
    /**
     * 业务描述：展现购物车列表的页面，同时查询购物车数据
     *url：http://www.jt.com/cart/show.html
     * 参数：userID=7L
     * 返回值：页面逻辑名称 cart.jsp
     * 页面取值：${cartList}
     */
    @RequestMapping("show")
  public  String  show(Model  model, HttpServletRequest request){
//        Long userID=7L;//暂时写死
       user user=(user)request.getAttribute("JT_USER");
          Long  userID=user.getId();
        List<cart>  cartList=cartService.findCartListByUserId(userID);
        model.addAttribute("cartList", cartList);
      return   "cart";
  }
    /**
     * 业务描述：
     * 完成购物车数量的修改操作
     * url地址： URL: http://www.jt.com/cart/update/num/562379/9
     * 参数：restful
     * 返回值：void
     */
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody//让ajax程序结束
    public  void  updateNum(cart cart){//springMvc 针对restful提供的功能，名称和属性一直
          //Long  userId=7L;
        Long  userId= userThreadLocal.get().getId();
          cart.setUserId(userId);
          cartService.updateCartNum(cart);
    }
    /**
     * 实现购物车删除操作
     * 1.url地址：http://www.jt.com/cart/delete/562379.html
     * 2.参数  562379 itemId
     * 3.返回值：string 重定向列表页面
     *
     */
    @RequestMapping("/delete/{itemId}")
    public  String  deleteCart(cart  cart){
          //Long  userId=7L;
        Long  userId= userThreadLocal.get().getId();
          cart.setUserId(userId);
          cartService.deleteCart(cart);
          return   "redirect:/cart/show.html";
    }
    /**
     * 购物车新增操作
     * url地址：http://www.jt.com/cart/add/562379.html
     * 参数：购物车属性数据
     * 返回值：重定向到购物车页面
     */
    @RequestMapping("/add/{itemId}")
    public   String  addcart(cart cart){
        //Long  userId=7L;
        Long  userId= userThreadLocal.get().getId();
        cart.setUserId(userId);
        cartService.addCart(cart);
       return  "redirect:/cart/show.html";
    }

}













