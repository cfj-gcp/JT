package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Order;
import com.jt.pojo.cart;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.util.userThreadLocal;

import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
public class orderController {
    @Reference
    private DubboCartService  CartService;
    @Reference
    private DubboOrderService OrderService;

    /**
     * 跳转订单确认页面
     * url:http://www.jt.com/order/create.html
     * 参数：
     * 返回一个页面：order-cart.jsp
     */
    @RequestMapping("/create")
    public  String create(Model model){
        Long  userId= userThreadLocal.get().getId();
       List<cart>  carts=CartService.findCartListByUserId(userId);
       model.addAttribute("carts", carts);
         return   "order-cart";
    }
    /**
     * 完成订单的入库操作
     * url:http://www.jt.com/order/submit
     * 参数：整个表单对象  用 order对象接收
     * 返回值： sysResult对象
     */
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult saveOrder(Order  order){
        Long   userId=userThreadLocal.get().getId();
        order.setUserId(userId);
        String  orderId=OrderService.saveOrder(order);
        if(StringUtils.isEmpty(orderId)){
            return  SysResult.fail();
        }else {
            return   SysResult.success(orderId);
        }
    }
    /**
     * 完成订单的查询
     * url地址：http://www.jt.com/order/success.html?id=111606111410035
     * 参数：orderId
     * 返回值：订单成功页面success.jsp
     * 获取页面值：${order.orderId}
     */
    @RequestMapping("/success")
    public  String  success(String  id,Model  model){
        Order  order=OrderService.findOrderId(id);
        model.addAttribute("order", order);
       return   "success";
    }
}













