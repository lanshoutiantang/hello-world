package org.jeecg.modules.demo3.intel.service;

import org.jeecg.modules.demo3.intel.entity.ZnGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * @Description: 门店补货商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
public interface IZnGoodsService extends IService<ZnGoods> {

    public  void updateByManualQty(BigDecimal manualQty, String goodsname);
    public void updateByComment(String comment,String id);

    public String getDbUrl(String orgCode);
    public String getDbPassword(String orgCode);
    public String getDbUserName(String orgCode);

    public int getSendNum(String goodsid);
    public void updateSendNum(String goodsid);

    public String getSpec(String goodsid);

    public void insertSendNum(String shopid,int countSend,int countSuccess,String operator,String sendtime);

    public void changeSendNum();

    public String getShopid(String orgCode);
}
