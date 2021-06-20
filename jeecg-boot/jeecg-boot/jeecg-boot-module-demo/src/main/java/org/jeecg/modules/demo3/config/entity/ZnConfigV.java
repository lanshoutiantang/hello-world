package org.jeecg.modules.demo3.config.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 特殊供应商补货参数
 * @Author: jeecg-boot
 * @Date:   2021-04-14
 * @Version: V1.0
 */
@Data
@TableName("zn_config_v")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_config_v对象", description="特殊供应商补货参数")
public class ZnConfigV implements Serializable {
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
    @Dict(dictTable = "v_shopname1", dicText = "shopname", dicCode = "shopid")
    @ApiModelProperty(value = "门店编码")
    private String shopid;
	/**供应商编码*/
    @Dict(dictTable = "v_vender", dicText = "vendername", dicCode = "venderid")
	@Excel(name = "供应商编码", width = 15)
    @ApiModelProperty(value = "供应商编码")
    private String venderid;
    /**供应商名称*/
    @TableField(exist = false)
    @Excel(name = "供应商名称", width = 15)
    @ApiModelProperty(value = "供应商名称")
    private String vendername;
    /**补货类别*/
    @Excel(name = "补货类别", width = 15)
    @Dict(dicCode = "classtype")
    @ApiModelProperty(value = "补货类别")
    private String classtype;
	/**补货日*/
	@Excel(name = "补货日", width = 15)
    @ApiModelProperty(value = "补货日")
    private String orderdate;
    /**补货周期标识*/
    @Excel(name = "补货周期标识", width = 15)
    @Dict(dicCode = "weekflag")
    @ApiModelProperty(value = "补货周期标识")
    private String weekflag;
	/**安全库存天数*/
	@Excel(name = "安全库存天数", width = 15)
    @ApiModelProperty(value = "安全库存天数")
    private Integer safedays;
	/**到货周期*/
	@Excel(name = "到货周期", width = 15)
    @ApiModelProperty(value = "到货周期")
    private Integer arrivaldays;
	/**开始周*/
	@Excel(name = "开始周", width = 15)
    @ApiModelProperty(value = "开始周")
    private Integer startweek;
	/**roundRule*/
	@Excel(name = "roundRule", width = 15)
    @ApiModelProperty(value = "roundRule")
    private String roundRule;
}
