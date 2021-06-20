package org.jeecg.modules.demo3.difference.service.impl;

import org.jeecg.modules.demo3.difference.entity.DifferenceBuhuo2;
import org.jeecg.modules.demo3.difference.mapper.DifferenceBuhuo2Mapper;
import org.jeecg.modules.demo3.difference.service.IDifferenceBuhuo2Service;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 存在补货差异的商品如下
 * @Author: jeecg-boot
 * @Date:   2021-04-15
 * @Version: V1.0
 */
@Service
public class DifferenceBuhuo2ServiceImpl extends ServiceImpl<DifferenceBuhuo2Mapper, DifferenceBuhuo2> implements IDifferenceBuhuo2Service {

	@Autowired
	private DifferenceBuhuo2Mapper differenceBuhuo2Mapper;

	@Override
	public List<DifferenceBuhuo2> selectByMainId(String mainId) {
		return differenceBuhuo2Mapper.selectByMainId(mainId);
	}

	@Override
	public String getShopid(String id) {
		return differenceBuhuo2Mapper.getShopid(id);
	}
}
