package org.jeecg.modules.demo3.intel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo3.intel.entity.ZnSeasonXl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 季节性商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-22
 * @Version: V1.0
 */
public interface ZnSeasonXlMapper extends BaseMapper<ZnSeasonXl> {
    public  void updateByBegindate(@Param("begindate") String begindate,
                                         @Param("xlid") String xlid);

    public  void updateByEnddate(@Param("enddate") String enddate,
                                         @Param("xlid") String xlid);

    public  void updateByUpdateBy(@Param("username") String username,
                                 @Param("xlid") String xlid);

//    public  String getSysDataSource();
}
