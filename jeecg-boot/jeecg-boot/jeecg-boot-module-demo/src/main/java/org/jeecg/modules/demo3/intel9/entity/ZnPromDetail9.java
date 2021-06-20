package org.jeecg.modules.demo3.intel9.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: zn_prom_detail9
 * @Author: jeecg-boot
 * @Date:   2020-12-29
 * @Version: V1.0
 */
@ApiModel(value="zn_prom_goods对象", description="zn_prom_goods9")
@Data
@TableName("zn_prom_detail")
public class ZnPromDetail9 implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键")
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
	/**促销计划编号*/
//	@Excel(name = "促销计划编号", width = 15)
	@ApiModelProperty(value = "促销计划编号")
	private String planid;
	/**促销计划名称*/
//	@Excel(name = "促销计划名称", width = 15)
	@ApiModelProperty(value = "促销计划名称")
	private String planname;
	/**促销开始日期时间*/
//	@Excel(name = "促销开始日期时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "促销开始日期时间")
	private Date begindate;
	/**促销结束日期时间*/
//	@Excel(name = "促销结束日期时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "促销结束日期时间")
	private Date enddate;
	/**门店编码*/
	@Excel(name = "门店编码", width = 15)
	@ApiModelProperty(value = "门店编码")
	private String shopid;
	/**小类编码*/
//	@Excel(name = "小类编码", width = 15)
	@ApiModelProperty(value = "小类编码")
	private String xlid;
	/**课类编码*/
//	@Excel(name = "课类编码", width = 15)
	@ApiModelProperty(value = "课类编码")
	private String cgid;
	/**部类编码*/
//	@Excel(name = "部类编码", width = 15)
	@ApiModelProperty(value = "部类编码")
	private String blid;
	/**商品编码*/
//	@Excel(name = "商品编码", width = 15)
	@ApiModelProperty(value = "商品编码")
	private String goodsid;
	/**商品名称*/
//	@Excel(name = "商品名称", width = 15)
	@ApiModelProperty(value = "商品名称")
	private String goodsname;
	/**促销售价*/
//	@Excel(name = "促销售价", width = 15)
	@ApiModelProperty(value = "促销售价")
	private java.math.BigDecimal price;
	/**采购分货数量*/
	@Excel(name = "采购分货数量", width = 15)
	@ApiModelProperty(value = "采购分货数量")
	private java.math.BigDecimal planqty;
	/**manualFlag*/
//	@Excel(name = "manualFlag", width = 15)
	@ApiModelProperty(value = "manualFlag")
	private String manualFlag;
	/**补货标识*/
//	@Excel(name = "补货标识", width = 15)
	@ApiModelProperty(value = "补货标识")
	private String flag;
	/**门店机构编码*/
//	@Excel(name = "门店机构编码", width = 15)
	@ApiModelProperty(value = "门店机构编码")
	private String shopOrgan;
	/**部类全部机构编码*/
//	@Excel(name = "部类全部机构编码", width = 15)
	@ApiModelProperty(value = "部类全部机构编码")
	private String blOrganAll;
	/**课类全部机构编码*/
//	@Excel(name = "课类全部机构编码", width = 15)
	@ApiModelProperty(value = "课类全部机构编码")
	private String klOrganAll;
	/**部类机构编码*/
//	@Excel(name = "部类机构编码", width = 15)
	@ApiModelProperty(value = "部类机构编码")
	private String blOrgan;
	/**课类机构编码*/
//	@Excel(name = "课类机构编码", width = 15)
	@ApiModelProperty(value = "课类机构编码")
	private String klOrgan;
	/**autoId*/
//	@Excel(name = "autoId", width = 15)
	@ApiModelProperty(value = "autoId")
	private Integer autoId;
	/**sdate*/
//	@Excel(name = "sdate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "sdate")
	private Date sdate;
	/**idGoods*/
//	@ApiModelProperty(value = "idGoods")
	private String idGoods;
}
