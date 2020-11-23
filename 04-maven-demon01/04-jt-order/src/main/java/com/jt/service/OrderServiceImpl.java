package com.jt.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.pojo.OrderShipping;

@Service(timeout = 3000)
public class OrderServiceImpl implements DubboOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;

	/**
	 *
	 * @param order
	 * @return
	 */
	@Override
	@Transactional //控制事务
	public String saveOrder(Order order) {
		String  orderId=""+order.getUserId()+ System.currentTimeMillis();
		order.setOrderId(orderId).setStatus(1);
		orderMapper.insert(order);

		OrderShipping  orderShipping=order.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShippingMapper.insert(orderShipping);


		List<OrderItem>  orderItems=order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrderId(orderId);
			orderItemMapper.insert(orderItem);

		}
		return orderId;
	}

	/**
	 * 需要通过order对象，返回3部分数据
	 * @param id
	 * @return
	 */
	@Override
	public Order findOrderId(String id) {
		Order  order=orderMapper.selectById(id);
		OrderShipping  orderShipping=orderShippingMapper.selectById(id);
		QueryWrapper   query=new QueryWrapper();
		query.eq("order_id", id);
	List<OrderItem> orderItems=	orderItemMapper.selectList(query);
		return order.setOrderShipping(orderShipping)
				.setOrderItems(orderItems);
	}
}
