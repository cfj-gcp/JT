package com.jt.pojo;


import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@TableName("tb_order_item")
@Data
@Accessors(chain=true)
public class OrderItem extends BasePojo{
//	对象中只能有一个主键 删除多余的主键
	@TableId
    private String itemId;
    private String orderId;
    private Integer num;
    private String title;

    private Long price;

    private Long totalFee;

    private String picPath;
}