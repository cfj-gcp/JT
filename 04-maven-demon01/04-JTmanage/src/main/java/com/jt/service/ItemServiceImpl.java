package com.jt.service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper  itemDescMapper;

	@Override
	public EasyUITable findItemPage(int page, int rows) {
//		使用mp进行分页
		IPage<Item>  iPage = new Page<>(page, rows);
		QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
		queryWrapper.orderByDesc("updated");
		IPage<Item> iPage1 = itemMapper.selectPage(iPage, queryWrapper);
//		total
		long total = iPage1.getTotal();
//		rows
		List<Item>  records= iPage1.getRecords();
		return new EasyUITable(total,records);
	}

	@Override
	public void saveItem(Item item, ItemDesc itemDesc) {
		item.setStatus(1);
//				.setCreated(new Date())//配置自动入库
//				.setUpdated(item.getCreated());
		itemMapper.insert(item);//执行数据库入库操作，动态生成ID
//		如何实现主键自增回显功能？可以通过标签的配置来实现，但是MP已经实现好了
//		2.入库详情信息如何保证item与itemdesc主键信息一致
		itemDesc.setItemId(item.getId());
//		入库详情信息
		itemDescMapper.insert(itemDesc);
	}

	@Override
	@Transactional
	public void updateItem(Item item, ItemDesc itemDesc) {
//		根据对象中不为null的元素
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId());
		itemDescMapper.updateById(itemDesc);
	}

	@Override
	@Transactional
	public void deleteItem(Long[] ids) {
     	List<Long> longList = Arrays.asList(ids);
//		itemMapper.deleteBatchIds(longList);
//		手写sql方法
		itemMapper.deleteItems(ids);
		itemDescMapper.deleteBatchIds(longList);
	}
	@Override
	public void updateStatus(Long[] ids, Integer status) {
//		Item entity= new Item();
//		entity.setStatus(status);
//		UpdateWrapper<Item> updateWrapper = new UpdateWrapper<>();
//		updateWrapper.in("id", Arrays.asList(ids));
//		itemMapper.update(entity, updateWrapper);
		itemMapper.updateStatus(ids,status);
	}

	@Override
	public ItemDesc itemDesc(Long id) {
		return itemDescMapper.selectById(id);
	}


//	/**
//	 * 1.后端产寻数据库记录
//	 * 2.将后端数据通过业务调用转化为vo对象
//	 * 3.前端通过vo对象的json进行数据的解析
//	 * @param page
//	 * @param rows
//	 * @return
//	 */
//	@Override
//	public EasyUITable findItemPage(int page, int rows) {
////1.total、、商品记录总数
//		long total = itemMapper.selectCount(null);
////		2.rows分页查询的结果
//		 int startNum=(page-1)*rows;
//          List<Item>  itemList=itemMapper.findItemByPage(startNum,rows);
//		return new EasyUITable(total,itemList);
//	}
}
