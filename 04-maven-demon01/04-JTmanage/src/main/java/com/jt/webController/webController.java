package com.jt.webController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.ItemDesc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class webController {
    /**需求实现跨越请求
     * http://manage.jt.com/web/testJSONP?callback=hello&_=1605613316344
     * 请求参数：callback
     */
    @RequestMapping("/web/testJSONP")
    public JSONPObject   test(String  callback){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(12L).setItemDesc("错了没");
        JSONPObject jsonpObject = new JSONPObject(callback, itemDesc);
                   return  jsonpObject;
    }
}
