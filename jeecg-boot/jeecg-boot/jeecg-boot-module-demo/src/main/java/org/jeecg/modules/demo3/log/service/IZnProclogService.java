package org.jeecg.modules.demo3.log.service;

import org.jeecg.modules.demo3.log.entity.ZnProclog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 执行补货流程
 * @Author: jeecg-boot
 * @Date:   2021-05-12
 * @Version: V1.0
 */
public interface IZnProclogService extends IService<ZnProclog> {

    public void produce(String date,String shop);

}
