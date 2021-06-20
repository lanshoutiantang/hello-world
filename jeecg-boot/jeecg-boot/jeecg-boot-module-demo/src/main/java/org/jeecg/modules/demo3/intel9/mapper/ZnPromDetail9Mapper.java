package org.jeecg.modules.demo3.intel9.mapper;

import java.util.List;
import org.jeecg.modules.demo3.intel9.entity.ZnPromDetail9;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: zn_prom_detail9
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
public interface ZnPromDetail9Mapper extends BaseMapper<ZnPromDetail9> {

	public boolean deleteByMainId(@Param("mainId") String mainId);
    
	public List<ZnPromDetail9> selectByMainId(@Param("mainId") String mainId);
}
