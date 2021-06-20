package org.jeecg.modules.demo3.intel.service.impl;

import org.jeecg.modules.demo3.intel.entity.ZnHolidayGoods;
import org.jeecg.modules.demo3.intel.mapper.ZnHolidayGoodsMapper;
import org.jeecg.modules.demo3.intel.service.IZnHolidayGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 节日专属商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-19
 * @Version: V1.0
 */
@Service
public class ZnHolidayGoodsServiceImpl extends ServiceImpl<ZnHolidayGoodsMapper, ZnHolidayGoods> implements IZnHolidayGoodsService {

    @Autowired
    private ZnHolidayGoodsMapper znHolidayGoodsMapper;

    @Override
    public List<String> getGoodsNameList(String xlid) {
        return znHolidayGoodsMapper.getGoodsNameList(xlid);
    }
}
