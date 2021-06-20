package org.jeecg.modules.demo3.intel9.service.impl;

import org.jeecg.modules.demo3.intel9.entity.ZnPromGoods9;
import org.jeecg.modules.demo3.intel9.entity.ZnPromDetail9;
import org.jeecg.modules.demo3.intel9.mapper.ZnPromDetail9Mapper;
import org.jeecg.modules.demo3.intel9.mapper.ZnPromGoods9Mapper;
import org.jeecg.modules.demo3.intel9.service.IZnPromGoods9Service;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: zn_prom_goods9
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
@Service
public class ZnPromGoods9ServiceImpl extends ServiceImpl<ZnPromGoods9Mapper, ZnPromGoods9> implements IZnPromGoods9Service {

	@Autowired
	private ZnPromGoods9Mapper znPromGoods9Mapper;
	@Autowired
	private ZnPromDetail9Mapper znPromDetail9Mapper;
	
	@Override
	@Transactional
	public void saveMain(ZnPromGoods9 znPromGoods9, List<ZnPromDetail9> znPromDetail9List) {
		znPromGoods9Mapper.insert(znPromGoods9);
		if(znPromDetail9List!=null && znPromDetail9List.size()>0) {
			for(ZnPromDetail9 entity:znPromDetail9List) {
				//外键设置
				entity.setIdGoods(znPromGoods9.getId());
				znPromDetail9Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(ZnPromGoods9 znPromGoods9,List<ZnPromDetail9> znPromDetail9List) {
		znPromGoods9Mapper.updateById(znPromGoods9);
		
		//1.先删除子表数据
		znPromDetail9Mapper.deleteByMainId(znPromGoods9.getId());
		
		//2.子表数据重新插入
		if(znPromDetail9List!=null && znPromDetail9List.size()>0) {
			for(ZnPromDetail9 entity:znPromDetail9List) {
				//外键设置
				entity.setIdGoods(znPromGoods9.getId());
				znPromDetail9Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		znPromDetail9Mapper.deleteByMainId(id);
		znPromGoods9Mapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			znPromDetail9Mapper.deleteByMainId(id.toString());
			znPromGoods9Mapper.deleteById(id);
		}
	}
	
}
