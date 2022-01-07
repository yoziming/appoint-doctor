package yoziming.ad.vo.hosp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Department")
public class DepartmentQueryVo {

	@ApiModelProperty(value = "醫院編號")
	private String hoscode;

	@ApiModelProperty(value = "科室編號")
	private String depcode;

	@ApiModelProperty(value = "科室名稱")
	private String depname;

	@ApiModelProperty(value = "大科室編號")
	private String bigcode;

	@ApiModelProperty(value = "大科室名稱")
	private String bigname;

}

