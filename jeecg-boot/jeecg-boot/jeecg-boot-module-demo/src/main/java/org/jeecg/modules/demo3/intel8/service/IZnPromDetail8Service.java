package org.jeecg.modules.demo3.intel8.service;

import org.jeecg.modules.demo3.intel8.entity.ZnPromDetail8;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: zn_prom_detail8
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
public interface IZnPromDetail8Service extends IService<ZnPromDetail8> {

	public List<ZnPromDetail8> selectByMainId(String mainId);
}
