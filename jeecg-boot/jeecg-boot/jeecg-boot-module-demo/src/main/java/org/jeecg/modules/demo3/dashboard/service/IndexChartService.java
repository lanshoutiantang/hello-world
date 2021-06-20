package org.jeecg.modules.demo3.dashboard.service;


import org.jeecg.modules.demo3.dashboard.mapper.IndexChartMapper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


public interface IndexChartService {

    public String getShopName(String shopId);

    public Integer getShopGoodsCount(String shopId);

    public Integer getQty(String shopId);

    public List<String> getGoodsNameList(String shopId);

    public List<Double> getNumList(String shopId);

    public List<Double> getPriceList(String shopId);

    public Double priceCount(String shopId);

    public String getShopIdByUsername(String username);

    public int getShopCount();

}
