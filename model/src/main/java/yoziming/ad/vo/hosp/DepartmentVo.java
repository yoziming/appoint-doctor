package yoziming.ad.vo.hosp;

import yoziming.ad.model.hosp.Department;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "Department")
public class DepartmentVo {

	@ApiModelProperty(value = "科室編號")
	private String depcode;

	@ApiModelProperty(value = "科室名稱")
	private String depname;

	@ApiModelProperty(value = "下級節點")
	private List<DepartmentVo> children;

}

