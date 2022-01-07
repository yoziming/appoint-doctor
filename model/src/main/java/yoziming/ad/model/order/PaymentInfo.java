package yoziming.ad.model.order;

import yoziming.ad.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * PaymentInfo
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "PaymentInfo")
@TableName("payment_info")
public class PaymentInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "對外業務編號")
	@TableField("out_trade_no")
	private String outTradeNo;

	@ApiModelProperty(value = "訂單編號")
	@TableField("order_id")
	private Long orderId;

	@ApiModelProperty(value = "支付類型（微信 支付寶）")
	@TableField("payment_type")
	private Integer paymentType;

	@ApiModelProperty(value = "交易編號")
	@TableField("trade_no")
	private String tradeNo;

	@ApiModelProperty(value = "支付金額")
	@TableField("total_amount")
	private BigDecimal totalAmount;

	@ApiModelProperty(value = "交易內容")
	@TableField("subject")
	private String subject;

	@ApiModelProperty(value = "支付狀態")
	@TableField("payment_status")
	private Integer paymentStatus;

	@ApiModelProperty(value = "回調時間")
	@TableField("callback_time")
	private Date callbackTime;

	@ApiModelProperty(value = "回調信息")
	@TableField("callback_content")
	private String callbackContent;

}

