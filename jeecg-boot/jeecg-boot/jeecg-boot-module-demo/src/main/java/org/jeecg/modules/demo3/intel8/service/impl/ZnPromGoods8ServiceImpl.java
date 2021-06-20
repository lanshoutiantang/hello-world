package org.jeecg.modules.demo3.intel8.service.impl;

import org.jeecg.modules.demo3.intel8.entity.ZnPromGoods8;
import org.jeecg.modules.demo3.intel8.entity.ZnPromDetail8;
import org.jeecg.modules.demo3.intel8.mapper.ZnPromDetail8Mapper;
import org.jeecg.modules.demo3.intel8.mapper.ZnPromGoods8Mapper;
import org.jeecg.modules.demo3.intel8.service.IZnPromGoods8Service;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: zn_prom_goods8
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
@Service
public class ZnPromGoods8ServiceImpl extends ServiceImpl<ZnPromGoods8Mapper, ZnPromGoods8> implements IZnPromGoods8Service {

	@Autowired
	private ZnPromGoods8Mapper znPromGoods8Mapper;
	@Autowired
	private ZnPromDetail8Mapper znPromDetail8Mapper;
	
	@Override
	@Transactional
	public void saveMain(ZnPromGoods8 znPromGoods8, List<ZnPromDetail8> znPromDetail8List) {
		znPromGoods8Mapper.insert(znPromGoods8);
		if(znPromDetail8List!=null && znPromDetail8List.size()>0) {
			for(ZnPromDetail8 entity:znPromDetail8List) {
				//外键设置
				entity.setIdGoods(znPromGoods8.getId());
				znPromDetail8Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(ZnPromGoods8 znPromGoods8,List<ZnPromDetail8> znPromDetail8List) {
		znPromGoods8Mapper.updateById(znPromGoods8);
		
		//1.先删除子表数据
		znPromDetail8Mapper.deleteByMainId(znPromGoods8.getId());
		
		//2.子表数据重新插入
		if(znPromDetail8List!=null && znPromDetail8List.size()>0) {
			for(ZnPromDetail8 entity:znPromDetail8List) {
				//外键设置
				entity.setIdGoods(znPromGoods8.getId());
				znPromDetail8Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		znPromDetail8Mapper.deleteByMainId(id);
		znPromGoods8Mapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			znPromDetail8Mapper.deleteByMainId(id.toString());
			znPromGoods8Mapper.deleteById(id);
		}
	}
	
}
