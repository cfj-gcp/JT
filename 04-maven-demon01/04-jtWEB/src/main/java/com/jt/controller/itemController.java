package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.DubboItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class itemController {
    @Reference(check = false)
    private DubboItemService  itemService;
    /**
     * 实现商品的展现
     * url:http://www.jt.com/items/562379.html
     * 参数：562379商品ID
     * 返回值：item.jsp
     * 页面取值：item对象/itemDesc对象
     * { item.id/title}
     */
    @RequestMapping("/items/{itemId}")
    public String findItemByID(@PathVariable Long itemId, Model model){
        Item item=itemService.findItemById(itemId);
      ItemDesc itemDesc= itemService.findItemDescById(itemId);
      model.addAttribute("item", item);
      model.addAttribute("itemDesc", itemDesc);
       return  "item";
    }
}
