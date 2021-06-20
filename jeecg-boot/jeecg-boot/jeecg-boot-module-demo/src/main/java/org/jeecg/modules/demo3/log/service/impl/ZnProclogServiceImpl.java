package org.jeecg.modules.demo3.log.service.impl;

import org.jeecg.modules.demo3.log.entity.ZnProclog;
import org.jeecg.modules.demo3.log.mapper.ZnProclogMapper;
import org.jeecg.modules.demo3.log.service.IZnProclogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 执行补货流程
 * @Author: jeecg-boot
 * @Date:   2021-05-12
 * @Version: V1.0
 */
@Service
public class ZnProclogServiceImpl extends ServiceImpl<ZnProclogMapper, ZnProclog> implements IZnProclogService {
    @Autowired
    private  ZnProclogMapper znProclogMapper;

    @Override
    public void produce(String date,String shop){
        znProclogMapper.produce(date,shop);
    }

}
