package org.jeecg.modules.demo3.config.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
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
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 特殊商品补货参数
 * @Author: jeecg-boot
 * @Date:   2021-04-14
 * @Version: V1.0
 */
@Data
@TableName("zn_config_d")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="zn_config_d对象", description="特殊商品补货参数")
public class ZnConfigD implements Serializable {
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
	/**补货类别*/
	@Excel(name = "补货类别", width = 15)
    @ApiModelProperty(value = "补货类别")
    @Dict(dicCode = "classtype1")
    private String classtype;
	/**课别或采购线*/
	@Excel(name = "课别或采购线", width = 15)
    @Dict(dictTable = "klcode_name", dicText = "klname", dicCode = "klid")
    @ApiModelProperty(value = "课别或采购线")
    private String cgid;
	/**补货日*/
	@Excel(name = "补货日", width = 15)
    @ApiModelProperty(value = "补货日")
    private String orderdate;
	/**安全库存天数*/
	@Excel(name = "安全库存天数", width = 15)
    @ApiModelProperty(value = "安全库存天数")
    private Integer safedays;
	/**到货周期*/
	@Excel(name = "到货周期", width = 15)
    @ApiModelProperty(value = "到货周期")
    private Integer arrivaldays;
	/**每次补几天的货*/
	@Excel(name = "每次补几天的货", width = 15)
    @ApiModelProperty(value = "每次补几天的货")
    private Integer repldays;
	/**周转规则*/
	@Excel(name = "周转规则", width = 15)
    @ApiModelProperty(value = "周转规则")
    private String roundRule;
}
