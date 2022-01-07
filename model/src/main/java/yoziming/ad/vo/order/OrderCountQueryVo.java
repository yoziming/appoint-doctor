package yoziming.ad.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "OrderCountQueryVo")
public class OrderCountQueryVo {

	@ApiModelProperty(value = "醫院編號")
	private String hoscode;

	@ApiModelProperty(value = "醫院名稱")
	private String hosname;

	@ApiModelProperty(value = "安排日期")
	private String reserveDateBegin;
	private String reserveDateEnd;

}

