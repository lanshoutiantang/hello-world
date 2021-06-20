package org.jeecg.modules.demo3.difference.service.impl;

import org.jeecg.modules.demo3.difference.entity.DifferenceBuhuo;
import org.jeecg.modules.demo3.difference.entity.DifferenceBuhuo2;
import org.jeecg.modules.demo3.difference.mapper.DifferenceBuhuo2Mapper;
import org.jeecg.modules.demo3.difference.mapper.DifferenceBuhuoMapper;
import org.jeecg.modules.demo3.difference.service.IDifferenceBuhuoService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 补货差异主表
 * @Author: jeecg-boot
 * @Date:   2021-04-15
 * @Version: V1.0
 */
@Service
public class DifferenceBuhuoServiceImpl extends ServiceImpl<DifferenceBuhuoMapper, DifferenceBuhuo> implements IDifferenceBuhuoService {

	@Autowired
	private DifferenceBuhuoMapper differenceBuhuoMapper;
	@Autowired
	private DifferenceBuhuo2Mapper differenceBuhuo2Mapper;
	
	@Override
	@Transactional
	public void saveMain(DifferenceBuhuo differenceBuhuo, List<DifferenceBuhuo2> differenceBuhuo2List) {
		differenceBuhuoMapper.insert(differenceBuhuo);
		if(differenceBuhuo2List!=null && differenceBuhuo2List.size()>0) {
			for(DifferenceBuhuo2 entity:differenceBuhuo2List) {
				//外键设置
				entity.setShopid(differenceBuhuo.getId());
				differenceBuhuo2Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(DifferenceBuhuo differenceBuhuo,List<DifferenceBuhuo2> differenceBuhuo2List) {
		differenceBuhuoMapper.updateById(differenceBuhuo);
		
		//1.先删除子表数据
		differenceBuhuo2Mapper.deleteByMainId(differenceBuhuo.getId());
		
		//2.子表数据重新插入
		if(differenceBuhuo2List!=null && differenceBuhuo2List.size()>0) {
			for(DifferenceBuhuo2 entity:differenceBuhuo2List) {
				//外键设置
				entity.setShopid(differenceBuhuo.getId());
				differenceBuhuo2Mapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		differenceBuhuo2Mapper.deleteByMainId(id);
		differenceBuhuoMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			differenceBuhuo2Mapper.deleteByMainId(id.toString());
			differenceBuhuoMapper.deleteById(id);
		}
	}
	
}
