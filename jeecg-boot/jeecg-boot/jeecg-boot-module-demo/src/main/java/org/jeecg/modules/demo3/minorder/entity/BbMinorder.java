package org.jeecg.modules.demo3.minorder.entity;

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
 * @Description: 商品最小订货量
 * @Author: jeecg-boot
 * @Date:   2021-04-12
 * @Version: V1.0
 */
@Data
@TableName("bb_minorder")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bb_minorder对象", description="商品最小订货量")
public class BbMinorder implements Serializable {
    private static final long serialVersionUID = 1L;

//	/**主键*/
//	@TableId(type = IdType.ASSIGN_ID)
//    @ApiModelProperty(value = "主键")
//    private String id;
//	/**创建人*/
//    @ApiModelProperty(value = "创建人")
//    private String createBy;
//	/**创建日期*/
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty(value = "创建日期")
//    private Date createTime;
//	/**更新人*/
//    @ApiModelProperty(value = "更新人")
//    private String updateBy;
//	/**更新日期*/
//	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty(value = "更新日期")
//    private Date updateTime;
//	/**所属部门*/
//    @ApiModelProperty(value = "所属部门")
//    private String sysOrgCode;
    /**门店编码*/
    @TableField
    @Excel(name = "业态", width = 15)
    @ApiModelProperty(value = "业态")
    private String yt;
	/**门店编码*/
	@Excel(name = "门店编码", width = 15)
    @ApiModelProperty(value = "门店编码")
    private String shopid;
	/**门店名称*/
	@Excel(name = "门店名称", width = 15)
    @ApiModelProperty(value = "门店名称")
    private String shopname;
	/**商品编码*/
	@Excel(name = "商品编码", width = 15)
    @ApiModelProperty(value = "商品编码")
    private String goodsid;
	/**商品名称*/
	@Excel(name = "商品名称", width = 15)
    @ApiModelProperty(value = "商品名称")
    private String name;
	/**件装数*/
	@Excel(name = "件装数", width = 15)
    @ApiModelProperty(value = "件装数")
    private BigDecimal conversion;
	/**最小订单数量*/
	@Excel(name = "最小订单数量", width = 15)
    @ApiModelProperty(value = "最小订单数量")
    private BigDecimal minorderqty;
	/**最小库存数量*/
	@Excel(name = "最小库存数量", width = 15)
    @ApiModelProperty(value = "最小库存数量")
    private BigDecimal minstockqty;
}
