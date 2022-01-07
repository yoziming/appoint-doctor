package yoziming.ad.model.order;

import yoziming.ad.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Order
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Order")
@TableName("order_info")
public class OrderInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "userId")
	@TableField("user_id")
	private Long userId;

	@ApiModelProperty(value = "訂單交易號")
	@TableField("out_trade_no")
	private String outTradeNo;

	@ApiModelProperty(value = "醫院編號")
	@TableField("hoscode")
	private String hoscode;

	@ApiModelProperty(value = "醫院名稱")
	@TableField("hosname")
	private String hosname;

	@ApiModelProperty(value = "科室編號")
	@TableField("depcode")
	private String depcode;

	@ApiModelProperty(value = "科室名稱")
	@TableField("depname")
	private String depname;

	@ApiModelProperty(value = "排班id")
	@TableField("schedule_id")
	private String scheduleId;

	@ApiModelProperty(value = "醫生職稱")
	@TableField("title")
	private String title;

	@ApiModelProperty(value = "安排日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("reserve_date")
	private Date reserveDate;

	@ApiModelProperty(value = "安排時間（0：上午 1：下午）")
	@TableField("reserve_time")
	private Integer reserveTime;

	@ApiModelProperty(value = "就診人id")
	@TableField("patient_id")
	private Long patientId;

	@ApiModelProperty(value = "就診人名稱")
	@TableField("patient_name")
	private String patientName;

	@ApiModelProperty(value = "就診人手機")
	@TableField("patient_phone")
	private String patientPhone;

	@ApiModelProperty(value = "預約記錄唯一標識（醫院預約記錄主鍵）")
	@TableField("hos_record_id")
	private String hosRecordId;

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

	@ApiModelProperty(value = "退號時間")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@TableField("quit_time")
	private Date quitTime;

	@ApiModelProperty(value = "訂單狀態")
	@TableField("order_status")
	private Integer orderStatus;

}

