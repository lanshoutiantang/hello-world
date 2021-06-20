package org.jeecg.modules.demo3.minorder.service;

import org.jeecg.modules.demo3.minorder.entity.BbMinorder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * @Description: 商品最小订货量
 * @Author: jeecg-boot
 * @Date:   2021-04-12
 * @Version: V1.0
 */
public interface IBbMinorderService extends IService<BbMinorder> {

    public void updateByMinorderqty(BigDecimal minorderqty,String shopid, String goodsid);
    public void updateByMinstockqty(BigDecimal minstockqty, String shopid, String goodsid);
    public void saveLie(String shopid, String goodsid ,BigDecimal minorderqty, BigDecimal minstockqty);

}
