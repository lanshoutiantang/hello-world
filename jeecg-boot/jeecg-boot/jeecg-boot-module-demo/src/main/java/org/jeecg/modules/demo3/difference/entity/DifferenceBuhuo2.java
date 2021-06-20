package org.jeecg.modules.demo3.difference.entity;

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
 * @Description: 存在补货差异的商品如下
 * @Author: jeecg-boot
 * @Date:   2021-04-15
 * @Version: V1.0
 */
@ApiModel(value="differencebuhuo对象", description="补货差异主表")
@Data
@TableName("differencebuhuo2")
public class DifferenceBuhuo2 implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
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
	/**外键*/
	@ApiModelProperty(value = "外键")
	private String shopid;
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
	@ApiModelProperty(value = "商品名称")
	private String goodsname;
	/**建议补货数量*/
	@Excel(name = "建议补货数量", width = 15)
	@ApiModelProperty(value = "建议补货数量")
	private Double qty;
	/**手动补货数量*/
	@Excel(name = "手动补货数量", width = 15)
	@ApiModelProperty(value = "手动补货数量")
	private Double manualQty;
	/**建议补货商品金额*/
	@Excel(name = "建议补货商品金额", width = 15)
	@ApiModelProperty(value = "建议补货商品金额")
	private java.math.BigDecimal qtyPrice;
	/**手动补货商品金额*/
	@Excel(name = "手动补货商品金额", width = 15)
	@ApiModelProperty(value = "手动补货商品金额")
	private java.math.BigDecimal manualQtyPrice;
}
