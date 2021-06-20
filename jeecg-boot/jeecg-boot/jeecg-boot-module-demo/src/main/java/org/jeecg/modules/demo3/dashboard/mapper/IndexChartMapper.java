package org.jeecg.modules.demo3.dashboard.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.print.DocFlavor;
import java.util.List;
import java.util.function.DoubleConsumer;

@Repository
public interface IndexChartMapper{

    public String getShopName(String shopId);

    public Integer getShopGoodsCount(String shopId);

    public Integer getQty(String shopId);

    public List<String> getGoodsNameList(String shopId);

    public List<Double> getNumList(String shopId);

    public List<Double> getPriceList(String shopId);

    public String getShopIdByUsername(String username);

    //获取门店总数量
    int getShopCount();



}
