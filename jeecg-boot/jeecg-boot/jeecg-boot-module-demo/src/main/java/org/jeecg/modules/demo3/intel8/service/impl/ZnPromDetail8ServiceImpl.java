package org.jeecg.modules.demo3.intel8.service.impl;

import org.jeecg.modules.demo3.intel8.entity.ZnPromDetail8;
import org.jeecg.modules.demo3.intel8.mapper.ZnPromDetail8Mapper;
import org.jeecg.modules.demo3.intel8.service.IZnPromDetail8Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: zn_prom_detail8
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
@Service
public class ZnPromDetail8ServiceImpl extends ServiceImpl<ZnPromDetail8Mapper, ZnPromDetail8> implements IZnPromDetail8Service {
	
	@Autowired
	private ZnPromDetail8Mapper znPromDetail8Mapper;
	
	@Override
	public List<ZnPromDetail8> selectByMainId(String mainId) {
		return znPromDetail8Mapper.selectByMainId(mainId);
	}
}
