package org.jeecg.modules.demo3.intel.service.impl;

import org.jeecg.modules.demo3.intel.entity.ZnGoods;
import org.jeecg.modules.demo3.intel.mapper.ZnGoodsMapper;
import org.jeecg.modules.demo3.intel.service.IZnGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;

/**
 * @Description: 门店补货商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Service
public class ZnGoodsServiceImpl extends ServiceImpl<ZnGoodsMapper, ZnGoods> implements IZnGoodsService {

    @Autowired
    private ZnGoodsMapper znGoodsMapper;
    @Override
    public void updateByManualQty(BigDecimal manualQty, String goodsname) {
        znGoodsMapper.updateByManualQty(manualQty,goodsname);
        return;
    }

    @Override
    public void updateByComment(String comment,String id){
        znGoodsMapper.updateByComment(comment,id);
        return;
    }
    @Override
    public String getDbUrl(String orgCode) {
        return znGoodsMapper.getDbUrl(orgCode);
    }


    @Override
    public String getDbPassword(String orgCode) {
        return znGoodsMapper.getDbPassword(orgCode);
    }

    @Override
    public String getDbUserName(String orgCode) {
        return znGoodsMapper.getDbUserName(orgCode);
    }

    @Override
    public int getSendNum(String goodsid) {
        return znGoodsMapper.getSendNum(goodsid);
    }

    @Override
    public void updateSendNum(String goodsid) {
        znGoodsMapper.updateSendNum(goodsid);
        return;
    }

    @Override
    public String getSpec(String goodsid) {
        return znGoodsMapper.getSpec(goodsid);
    }

    @Override
    public void insertSendNum(String shopid, int countSend, int countSuccess, String operator, String sendtime) {
        znGoodsMapper.insertSendNum(shopid,countSend,countSuccess,operator,sendtime);
    }

    @Override
    public void changeSendNum() {
        znGoodsMapper.changeSendNum();
    }

    @Override
    public String getShopid(String orgCode) {
        String shopid = znGoodsMapper.getShopid(orgCode);
        return shopid;
    }
}
