package org.jeecg.modules.demo3.dashboard.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.jeecg.modules.demo3.dashboard.mapper.IndexChartMapper;
import org.jeecg.modules.demo3.dashboard.service.IndexChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexChartServiceImpl implements IndexChartService {

    @Autowired
    private IndexChartMapper indexChartMapper;


    @Override
    public String getShopName(String shopId) {
        return indexChartMapper.getShopName(shopId);
    }

    @Override
    public Integer getShopGoodsCount(String shopId) {
        return indexChartMapper.getShopGoodsCount(shopId);
    }


    @Override
    public Integer getQty(String shopId) {
        return indexChartMapper.getQty(shopId);
    }

    @Override
    public List<String> getGoodsNameList(String shopId) {
        return indexChartMapper.getGoodsNameList(shopId);
    }

    @Override
    public List<Double> getNumList(String shopId) {
        return indexChartMapper.getNumList(shopId);
    }

    @Override
    public List<Double> getPriceList(String shopId) {
        return indexChartMapper.getPriceList(shopId);
    }

    @Override
    public Double priceCount(String shopId) {
        List<Double> list1 = getNumList(shopId);
        List<Double> list2 = getPriceList(shopId);
        double sum = 0.0;
        double count = 0.0;
        for (int i = 0; i < list1.size(); i++) {
            if (list1 != null && list2 != null){

                count = (list1.get(i)) * (list2.get(i));
                sum += count;
            }
        }
        return sum;
    }

    @Override
    public String getShopIdByUsername(String username) {
        return indexChartMapper.getShopIdByUsername(username);
    }

    @Override
    public int getShopCount() {
        return indexChartMapper.getShopCount();
    }


}
