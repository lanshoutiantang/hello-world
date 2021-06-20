package org.jeecg.modules.demo3.intel8.mapper;

import java.util.List;
import org.jeecg.modules.demo3.intel8.entity.ZnPromDetail8;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: zn_prom_detail8
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
public interface ZnPromDetail8Mapper extends BaseMapper<ZnPromDetail8> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ZnPromDetail8> selectByMainId(@Param("mainId") String mainId);
}
