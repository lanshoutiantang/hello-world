package org.jeecg.modules.demo3.dashboard.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
public class ResponseData {

    //门店名称
    private String shopname;
    //商品总数
    private Integer shopGoodsCount;
    //系统建议补货商品数
    private Integer qty;
    //需要补货的总价格预算
    private Double priceCount;
    //补货数量从大到小排列的商品名称
    private List<String> goodsNameList;
    //补货数量商从大到小排列的商品所需补货数量
    private List<Double> numList;


}
