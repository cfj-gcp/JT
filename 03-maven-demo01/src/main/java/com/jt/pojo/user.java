package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@TableName
@Accessors(chain = true)
public class user implements Serializable {
    private static final long serialVersionUID = 6487018978001459261L;
    private   String name;
    @TableId(type = IdType.AUTO)
    private   Integer  id;
    private   String  sex;
    private   Integer  age;
}
