package org.jeecg.modules.demo3.dashboard.controller;


import com.baomidou.dynamic.datasource.annotation.DS;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.demo3.dashboard.entity.ResponseData;
import org.jeecg.modules.demo3.dashboard.mapper.IndexChartMapper;
import org.jeecg.modules.demo3.dashboard.service.IndexChartService;
import org.jeecg.modules.demo3.intel.entity.ZnGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Api(tags="首页")
@Slf4j
@RestController
@RequestMapping("/dashboard")
public class IndexChartController {

    @Autowired
    IndexChartService indexChartService;

    @RequestMapping(value = "/indexChart")
    public  ResponseData indexChart(){
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        ResponseData respData = new ResponseData();
        String username = sysUser.getUsername();
        System.out.println(username);
        String shopid = null;
        if("admin".equals(username) || "xtadministrator".equals(username)){
            shopid = null;
            respData.setShopname("系统管理员(门店数："+indexChartService.getShopCount()+")");
        }else {
            shopid = indexChartService.getShopIdByUsername(username);
            respData.setShopname(indexChartService.getShopName(shopid));
        }
        respData.setShopGoodsCount(indexChartService.getShopGoodsCount(shopid));
        respData.setQty(indexChartService.getQty(shopid));
        respData.setGoodsNameList(indexChartService.getGoodsNameList(shopid));
        respData.setNumList(indexChartService.getNumList(shopid));
        respData.setPriceCount(Math.ceil(indexChartService.priceCount(shopid)));
        return respData;
    }


}

