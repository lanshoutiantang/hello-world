package org.jeecg.modules.demo3.difference.service;

import org.jeecg.modules.demo3.difference.entity.DifferenceBuhuo2;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 存在补货差异的商品如下
 * @Author: jeecg-boot
 * @Date:   2021-04-15
 * @Version: V1.0
 */
public interface IDifferenceBuhuo2Service extends IService<DifferenceBuhuo2> {

	public List<DifferenceBuhuo2> selectByMainId(String mainId);

	public String getShopid(String id);
}
