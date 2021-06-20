package org.jeecg.modules.demo3.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.demo3.log.entity.ZnProclog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 执行补货流程
 * @Author: jeecg-boot
 * @Date:   2021-05-12
 * @Version: V1.0
 */
public interface ZnProclogMapper extends BaseMapper<ZnProclog> {

    public void produce(@Param("date") String  date,@Param("shop") String  shop);
}
