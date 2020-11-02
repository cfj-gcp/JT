package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)//开启链式加载结构
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")//标识对象与表的映射关系，如果表名字与对象一致则可以省略不写
public class user  implements Serializable {
    private static final long serialVersionUID = 6588633626657342685L;
    //    实体类型的对象必须都是包装类型
    @TableId(type = IdType.AUTO)
    private  Integer  id;//主键自增的写法
//@TableField(value = "name")//标识属性和字段，如果名字一致可以省略不写
    private  String  name;
    private  Integer  age;
    private   String  sex;
}
