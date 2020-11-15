package com.jt.controller;
import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;
	/**
	 *  url:http://localhost:8091/item/query/item/desc/1474391975
	 *  参数：商品ID号
	 *  返回值：SysResult对象
	 */
	@RequestMapping("/query/item/desc/{id}")
  public  SysResult queryItemDesc(@PathVariable  Long id){
		ItemDesc itemDesc = itemService.itemDesc(id);
		return   SysResult.success(itemDesc);

	}
/**
 * 业务需求：修改item上架、下架状态
 * url：http://localhost:8091/item/updateStatus/2
 * 参数：状态码值
 * 返回值：sysresult
 */
@RequestMapping("/updateStatus/{status}")
public SysResult  updateStatus(@PathVariable Integer status,Long... ids){
	itemService.updateStatus(ids,status);
  return  SysResult.success();
}
	/**
	 * 业务需求：完成item数据的删除
	 * 地址：http://localhost:8091/item/delete
	 * 请求参数：ids: 1474391973
	 */
	@RequestMapping("/delete")
	public  SysResult deleteItem(Long[] ids){
		itemService.deleteItem(ids);
		return   SysResult.success();
	}
	/**
	 * 业务需求：完成将数据的修改
	 * 地址：url：/item/update
	 * 请求参数 from表单 对象接收
	 * 返回值  ：vo系统级别的对象
	 */
	@RequestMapping("/update")
	public SysResult  updateItem(Item item,ItemDesc itemDesc){
		itemService.updateItem(item,itemDesc);
		return    SysResult.success();
	}
	/**
	 * 业务需求：
	 * 完成商品入库操作。
	 * 注意事项：
	 * 1.方法出错添加try  catch
	 * 2.新增商品的状态信息为1
	 * 3.入库操作时完成时间的记录
	 * 请求地址：1.url 地址Request URL: http://localhost:8091/item/save
	 * 请求参数：from 表单  对象接收
	 * 3.返回值 ：系统级别的vo对象
	 *
	 */
	@RequestMapping("/save")
	public SysResult    saveItem(Item  item, ItemDesc itemDesc){
		itemService.saveItem(item,itemDesc);
	//		int  a=1/0;//测试异常捕捉
		return SysResult.success();
//		try {
//			itemService.saveItem(item);
//			return  SysResult.success();
//		}catch (Exception e){
//			 e.printStackTrace();
//			 return   SysResult.fail();
//		}
	}
	/**
	 *地址： http://localhost:8091/item/query?page=1&rows=20
	 * 业务需求：page当前页数  rows 每页展现的行数
	 * 返回值EasyUITable对象
	 * 方法一：1.手写sql实现分页
	 * 方法二：利用mp实现分页
	 * @return
	 */
	@RequestMapping("/query")
	public EasyUITable findAll(int page, int rows){
		return itemService.findItemPage(page,rows);
	}
}
