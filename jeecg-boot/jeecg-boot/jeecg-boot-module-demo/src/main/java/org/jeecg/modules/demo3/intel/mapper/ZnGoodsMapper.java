package org.jeecg.modules.demo3.intel.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo3.intel.entity.ZnGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 门店补货商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
public interface ZnGoodsMapper extends BaseMapper<ZnGoods> {

    public void updateByManualQty(@Param("manualQty") BigDecimal manualQty,
                                  @Param("goodsname") String goodsname);

    public void updateByComment(@Param("comment") String comment,
                                @Param("id") String id);
    public String getDbUrl(@Param("orgCode") String orgCode);
    public String getDbUserName(@Param("orgCode") String orgCode);
    public String getDbPassword(@Param("orgCode") String orgCode);

    public int getSendNum(@Param("goodsid") String goodsid);
    public void updateSendNum(@Param("goodsid") String goodsid);

    public String  getSpec(@Param("goodsid") String goodsid);

    public void insertSendNum(@Param("shopid") String shopid,@Param("countSend") int countSend,
                              @Param("countSuccess") int countSuccess,@Param("operator") String operator,
                              @Param("sendtime")String sendtime);

    public void changeSendNum();

    public String getShopid(@Param("orgCode") String orgCode);

}
