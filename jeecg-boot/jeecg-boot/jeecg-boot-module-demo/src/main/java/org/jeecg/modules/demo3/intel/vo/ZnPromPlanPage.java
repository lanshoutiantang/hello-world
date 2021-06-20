package org.jeecg.modules.demo3.intel.vo;

import java.util.List;
import org.jeecg.modules.demo3.intel.entity.ZnPromPlan;
import org.jeecg.modules.demo3.intel.entity.ZnPromShop;
import org.jeecg.modules.demo3.intel.entity.ZnPromGoods;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 促销计划表
 * @Author: jeecg-boot
 * @Date:   2020-11-18
 * @Version: V1.0
 */
@Data
@ApiModel(value="zn_prom_planPage对象", description="促销计划表")
public class ZnPromPlanPage {

	/**主键*/
	@ApiModelProperty(value = "主键")
	private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
	/**促销计划编号*/
	@Excel(name = "促销计划编号", width = 15)
	@ApiModelProperty(value = "促销计划编号")
	private String planid;
	/**促销计划名称*/
	@Excel(name = "促销计划名称", width = 15)
	@ApiModelProperty(value = "促销计划名称")
	private String planname;
	/**促销开始日期时间*/
	@Excel(name = "促销开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "促销开始日期时间")
	private Date begindate;
	/**促销结束日期时间*/
	@Excel(name = "促销结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "促销结束日期时间")
	private Date enddate;
	/**状态*/
//	@Excel(name = "状态", width = 15)
	@ApiModelProperty(value = "状态")
	private String status;

	@ExcelCollection(name="促销门店表")
	@ApiModelProperty(value = "促销门店表")
	private List<ZnPromShop> znPromShopList;
	@ExcelCollection(name="促销商品表")
	@ApiModelProperty(value = "促销商品表")
	private List<ZnPromGoods> znPromGoodsList;


}
