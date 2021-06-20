package org.jeecg.modules.demo3.intel9.vo;

import java.util.List;
import org.jeecg.modules.demo3.intel9.entity.ZnPromGoods9;
import org.jeecg.modules.demo3.intel9.entity.ZnPromDetail9;
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
 * @Description: zn_prom_goods9
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
@Data
@ApiModel(value="zn_prom_goodsPage对象", description="zn_prom_goods9")
public class ZnPromGoods9Page {

	/**id*/
	@ApiModelProperty(value = "id")
	private String id;
	/**创建人*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建日期")
	private Date createTime;
	/**更新人*/
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新日期")
	private Date updateTime;
	/**所属部门*/
	@ApiModelProperty(value = "所属部门")
	private String sysOrgCode;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String serialid;
	/**促销计划编号*/
	@Excel(name = "促销计划编号", width = 15)
	@ApiModelProperty(value = "促销计划编号")
	private String planid;
	/**促销计划名称*/
	@Excel(name = "促销计划名称", width = 15)
	@ApiModelProperty(value = "促销计划名称")
	private String planname;
	/**促销开始日期时间*/
	@Excel(name = "促销开始日期时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "促销开始日期时间")
	private Date begindate;
	/**促销结束日期时间*/
	@Excel(name = "促销结束日期时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "促销结束日期时间")
	private Date enddate;
	/**补货标识*/
	@Excel(name = "补货标识", width = 15)
	@ApiModelProperty(value = "补货标识")
	private String flag;
	/**课类编码*/
	@Excel(name = "课类编码", width = 15)
	@ApiModelProperty(value = "课类编码")
	private String cgid;
	/**部类编码*/
	@Excel(name = "部类编码", width = 15)
	@ApiModelProperty(value = "部类编码")
	private String blid;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
	@ApiModelProperty(value = "商品编码")
	private String goodsid;
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
	@ApiModelProperty(value = "商品名称")
	private String goodsname;
	/**促销售价*/
	@Excel(name = "促销售价", width = 15)
	@ApiModelProperty(value = "促销售价")
	private java.math.BigDecimal price;
	/**采购分货标识*/
	@Excel(name = "采购分货标识", width = 15)
//	@Dict(dicCode = "flag3")
	@ApiModelProperty(value = "采购分货标识")
	private String manualFlag;
	/**门店机构编码*/
	@Excel(name = "门店机构编码", width = 15)
	@ApiModelProperty(value = "门店机构编码")
	private String shopOrgan;
	/**部类全部机构编码*/
	@Excel(name = "部类全部机构编码", width = 15)
	@ApiModelProperty(value = "部类全部机构编码")
	private String blOrganAll;
	/**课类全部机构编码*/
	@Excel(name = "课类全部机构编码", width = 15)
	@ApiModelProperty(value = "课类全部机构编码")
	private String klOrganAll;
	/**部类机构编码*/
	@Excel(name = "部类机构编码", width = 15)
	@ApiModelProperty(value = "部类机构编码")
	private String blOrgan;
	/**课类机构编码*/
	@Excel(name = "课类机构编码", width = 15)
	@ApiModelProperty(value = "课类机构编码")
	private String klOrgan;
	/**autoId*/
	@Excel(name = "autoId", width = 15)
	@ApiModelProperty(value = "autoId")
	private Integer autoId;

	@ExcelCollection(name="zn_prom_detail9")
	@ApiModelProperty(value = "zn_prom_detail9")
	private List<ZnPromDetail9> znPromDetail9List;

}
