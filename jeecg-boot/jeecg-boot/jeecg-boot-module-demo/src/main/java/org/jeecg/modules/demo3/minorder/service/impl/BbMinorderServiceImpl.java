package org.jeecg.modules.demo3.minorder.service.impl;

import org.jeecg.modules.demo3.minorder.entity.BbMinorder;
import org.jeecg.modules.demo3.minorder.mapper.BbMinorderMapper;
import org.jeecg.modules.demo3.minorder.service.IBbMinorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;

/**
 * @Description: 商品最小订货量
 * @Author: jeecg-boot
 * @Date:   2021-04-12
 * @Version: V1.0
 */
@Service
public class BbMinorderServiceImpl extends ServiceImpl<BbMinorderMapper, BbMinorder> implements IBbMinorderService {
    @Autowired
    private BbMinorderMapper bbMinorderMapper;
    @Override
    public void updateByMinorderqty(BigDecimal minorderqty, String shopid, String goodsid) {
        bbMinorderMapper.updateByMinorderqty(minorderqty,shopid,goodsid);
        return;
    }
    @Override
    public void updateByMinstockqty(BigDecimal minstockqty, String shopid, String goodsid) {
        bbMinorderMapper.updateByMinstockqty(minstockqty,shopid,goodsid);
        return;
    }

    @Override
    public void saveLie(String shopid, String goodsid ,BigDecimal minorderqty, BigDecimal minstockqty) {
        bbMinorderMapper.saveLie(shopid,goodsid,minorderqty,minstockqty);
        return;
    }
}
