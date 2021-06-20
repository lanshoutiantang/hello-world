package org.jeecg.modules.demo3.intel.service.impl;

import org.jeecg.modules.demo3.intel.entity.ZnSeasonXl;
import org.jeecg.modules.demo3.intel.mapper.ZnSeasonXlMapper;
import org.jeecg.modules.demo3.intel.service.IZnSeasonXlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 季节性商品表
 * @Author: jeecg-boot
 * @Date:   2020-11-22
 * @Version: V1.0
 */
@Service
public class ZnSeasonXlServiceImpl extends ServiceImpl<ZnSeasonXlMapper, ZnSeasonXl> implements IZnSeasonXlService {


    @Autowired
    private ZnSeasonXlMapper znSeasonXlMapper;
    @Override
    public void updateByBegindate(String begindate, String xlid) {
        znSeasonXlMapper.updateByBegindate(begindate,xlid);
        return;
    }
    @Override
    public void updateByEnddate(String enddate, String xlid) {
        znSeasonXlMapper.updateByEnddate(enddate,xlid);
        return;
    }
    @Override
    public void updateByUpdateBy(String username, String xlid) {
        znSeasonXlMapper.updateByUpdateBy(username,xlid);
        return;
    }
/*    @Override
    public String getSysDataSource() {
//        znSeasonXlMapper.getSysDataSource();
        return znSeasonXlMapper.getSysDataSource();
    }*/
}
