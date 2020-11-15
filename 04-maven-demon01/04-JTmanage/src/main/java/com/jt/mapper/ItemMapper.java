package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item>{
    @Select("select * from tb_item order by updated  desc limit #{startNum},#{rows}")
    List<Item> findItemByPage(int startNum, int rows);

    void deleteItems(@Param("ids") Long[] ids);

    void updateStatus(@Param("ids") Long[] ids, Integer status);
}
