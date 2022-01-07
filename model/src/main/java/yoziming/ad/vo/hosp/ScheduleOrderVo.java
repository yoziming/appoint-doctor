package yoziming.ad.vo.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(description = "Schedule")
public class ScheduleOrderVo {

	@ApiModelProperty(value = "醫院編號")
	private String hoscode;

	@ApiModelProperty(value = "醫院名稱")
	private String hosname;

	@ApiModelProperty(value = "科室編號")
	private String depcode;

	@ApiModelProperty(value = "科室名稱")
	private String depname;

	@ApiModelProperty(value = "排班編號（醫院自己的排班主鍵）")
	private String hosScheduleId;

	@ApiModelProperty(value = "醫生職稱")
	private String title;

	@ApiModelProperty(value = "安排日期")
	private Date reserveDate;

	@ApiModelProperty(value = "剩餘預約數")
	private Integer availableNumber;

	@ApiModelProperty(value = "安排時間（0：上午 1：下午）")
	private Integer reserveTime;

	@ApiModelProperty(value = "醫事服務費")
	private BigDecimal amount;

	@ApiModelProperty(value = "退號時間")
	private Date quitTime;

	@ApiModelProperty(value = "挂號開始時間")
	private Date startTime;

	@ApiModelProperty(value = "挂號結束時間")
	private Date endTime;

	@ApiModelProperty(value = "當天停止挂號時間")
	private Date stopTime;
}

