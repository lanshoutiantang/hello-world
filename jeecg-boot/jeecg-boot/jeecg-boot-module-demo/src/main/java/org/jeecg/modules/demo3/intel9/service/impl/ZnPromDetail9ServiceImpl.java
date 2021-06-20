package org.jeecg.modules.demo3.intel9.service.impl;

import org.jeecg.modules.demo3.intel9.entity.ZnPromDetail9;
import org.jeecg.modules.demo3.intel9.mapper.ZnPromDetail9Mapper;
import org.jeecg.modules.demo3.intel9.service.IZnPromDetail9Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: zn_prom_detail9
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
@Service
public class ZnPromDetail9ServiceImpl extends ServiceImpl<ZnPromDetail9Mapper, ZnPromDetail9> implements IZnPromDetail9Service {
	
	@Autowired
	private ZnPromDetail9Mapper znPromDetail9Mapper;
	
	@Override
	public List<ZnPromDetail9> selectByMainId(String mainId) {
		return znPromDetail9Mapper.selectByMainId(mainId);
	}
}
