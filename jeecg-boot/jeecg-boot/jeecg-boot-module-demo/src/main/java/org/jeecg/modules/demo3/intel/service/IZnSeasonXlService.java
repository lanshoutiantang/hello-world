package org.jeecg.modules.demo3.intel.service;

import org.jeecg.modules.demo3.intel.entity.ZnSeasonXl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 季节性商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-22
 * @Version: V1.0
 */
public interface IZnSeasonXlService extends IService<ZnSeasonXl> {

    public void updateByBegindate(String begindate, String xlid);
    public void updateByEnddate(String enddate, String xlid);
    public void updateByUpdateBy(String username, String xlid);

//    public String getSysDataSource();
}
