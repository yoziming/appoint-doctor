package yoziming.ad.vo.hosp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "Hospital")
public class HospitalQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "醫院編號")
	private String hoscode;

	@ApiModelProperty(value = "醫院名稱")
	private String hosname;

	@ApiModelProperty(value = "醫院類型")
	private String hostype;

	@ApiModelProperty(value = "省code")
	private String provinceCode;

	@ApiModelProperty(value = "市code")
	private String cityCode;

	@ApiModelProperty(value = "區code")
	private String districtCode;

	@ApiModelProperty(value = "狀態")
	private Integer status;
}

