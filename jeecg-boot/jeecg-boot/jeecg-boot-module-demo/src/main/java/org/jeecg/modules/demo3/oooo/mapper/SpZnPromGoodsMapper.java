package org.jeecg.modules.demo3.oooo.mapper;

import java.util.List;
import org.jeecg.modules.demo3.oooo.entity.SpZnPromGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 促销商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-20
 * @Version: V1.0
 */
public interface SpZnPromGoodsMapper extends BaseMapper<SpZnPromGoods> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<SpZnPromGoods> selectByMainId(@Param("mainId") String mainId);
}
