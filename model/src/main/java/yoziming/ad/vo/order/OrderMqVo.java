package yoziming.ad.vo.order;

import yoziming.ad.vo.msm.MsmVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "OrderMqVo")
public class OrderMqVo {

	@ApiModelProperty(value = "可預約數")
	private Integer reservedNumber;

	@ApiModelProperty(value = "剩餘預約數")
	private Integer availableNumber;

	@ApiModelProperty(value = "排班id")
	private String scheduleId;

	@ApiModelProperty(value = "短信實體")
	private MsmVo msmVo;

}

