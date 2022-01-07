package yoziming.ad.vo.hosp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "Schedule")
public class ScheduleQueryVo {

	@ApiModelProperty(value = "醫院編號")
	private String hoscode;

	@ApiModelProperty(value = "科室編號")
	private String depcode;

	@ApiModelProperty(value = "醫生編號")
	private String doccode;

	@ApiModelProperty(value = "安排日期")
	private Date workDate;

	@ApiModelProperty(value = "安排時間（0：上午 1：下午）")
	private Integer workTime;

}

