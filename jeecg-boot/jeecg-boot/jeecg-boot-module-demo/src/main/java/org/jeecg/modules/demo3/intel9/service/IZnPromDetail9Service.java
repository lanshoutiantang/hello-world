package org.jeecg.modules.demo3.intel9.service;

import org.jeecg.modules.demo3.intel9.entity.ZnPromDetail9;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: zn_prom_detail9
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
public interface IZnPromDetail9Service extends IService<ZnPromDetail9> {

	public List<ZnPromDetail9> selectByMainId(String mainId);
}
