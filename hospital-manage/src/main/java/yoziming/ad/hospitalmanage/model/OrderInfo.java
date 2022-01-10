package yoziming.ad.hospitalmanage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * OrderInfo
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "OrderInfo")
@TableName("order_info")
public class OrderInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "排班id")
    @TableField("schedule_id")
    private Long scheduleId;

    @ApiModelProperty(value = "就診人id")
    @TableField("patient_id")
    private Long patientId;

    @ApiModelProperty(value = "預約號序")
    @TableField("number")
    private Integer number;

    @ApiModelProperty(value = "建議取號時間")
    @TableField("fetch_time")
    private String fetchTime;

    @ApiModelProperty(value = "取號地點")
    @TableField("fetch_address")
    private String fetchAddress;

    @ApiModelProperty(value = "醫事服務費")
    @TableField("amount")
    private BigDecimal amount;

    @ApiModelProperty(value = "支付時間")
    @TableField("pay_time")
    private Date payTime;

    @ApiModelProperty(value = "退號時間")
    @TableField("quit_time")
    private Date quitTime;

    @ApiModelProperty(value = "訂單狀態")
    @TableField("order_status")
    private Integer orderStatus;

}

