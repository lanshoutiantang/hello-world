package org.jeecg.modules.demo3.difference.mapper;

import java.util.List;
import org.jeecg.modules.demo3.difference.entity.DifferenceBuhuo2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: 存在补货差异的商品如下
 * @Author: jeecg-boot
 * @Date:   2021-04-15
 * @Version: V1.0
 */
public interface DifferenceBuhuo2Mapper extends BaseMapper<DifferenceBuhuo2> {

	public boolean deleteByMainId(@Param("mainId") String mainId);

	public List<DifferenceBuhuo2> selectByMainId(@Param("mainId") String mainId);

	public String getShopid(@Param("id") String id);
}
