package yoziming.ad.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Order")
public class OrderQueryVo {


	@ApiModelProperty(value = "會員id")
	private Long userId;

	@ApiModelProperty(value = "訂單交易號")
	private String outTradeNo;

	@ApiModelProperty(value = "就診人id")
	private Long patientId;

	@ApiModelProperty(value = "就診人")
	private String patientName;

	@ApiModelProperty(value = "醫院名稱")
	private String keyword;

	@ApiModelProperty(value = "訂單狀態")
	private String orderStatus;

	@ApiModelProperty(value = "安排日期")
	private String reserveDate;

	@ApiModelProperty(value = "創建時間")
	private String createTimeBegin;
	private String createTimeEnd;

}

