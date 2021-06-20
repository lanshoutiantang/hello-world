package org.jeecg.modules.demo3.difference.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 补货差异主表
 * @Author: jeecg-boot
 * @Date:   2021-04-15
 * @Version: V1.0
 */
@ApiModel(value="differencebuhuo对象", description="补货差异主表")
@Data
@TableName("differencebuhuo")
public class DifferenceBuhuo implements Serializable {
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
	/**门店编码*/
	@Excel(name = "门店编码", width = 15)
    @ApiModelProperty(value = "门店编码")
    private String shopid;
	/**门店名称*/
	@Excel(name = "门店名称", width = 15)
    @ApiModelProperty(value = "门店名称")
    private String shopname;
	/**差异商品数*/
	@Excel(name = "差异商品数", width = 15)
    @ApiModelProperty(value = "差异商品数")
    private Integer goodsSku;
	/**差异商品总金额*/
	@Excel(name = "差异商品总金额", width = 15)
    @ApiModelProperty(value = "差异商品总金额")
    private Double priceCount;
}
