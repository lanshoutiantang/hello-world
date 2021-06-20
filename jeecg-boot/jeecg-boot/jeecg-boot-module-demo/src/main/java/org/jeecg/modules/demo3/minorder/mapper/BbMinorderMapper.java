package org.jeecg.modules.demo3.minorder.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo3.minorder.entity.BbMinorder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 商品最小订货量
 * @Author: jeecg-boot
 * @Date:   2021-04-12
 * @Version: V1.0
 */
public interface BbMinorderMapper extends BaseMapper<BbMinorder> {
    public  void updateByMinorderqty(@Param("minorderqty") BigDecimal minorderqty,
                                     @Param("shopid") String shopid,
                                   @Param("goodsid") String goodsid);

    public  void updateByMinstockqty(@Param("minstockqty") BigDecimal minstockqty,
                                     @Param("shopid") String shopid,
                                     @Param("goodsid") String goodsid);

    public  void saveLie(@Param("shopid") String shopid,
                                     @Param("goodsid") String goodsid,
                                     @Param("minorderqty") BigDecimal minorderqty,
                                     @Param("minstockqty") BigDecimal minstockqty);


}
