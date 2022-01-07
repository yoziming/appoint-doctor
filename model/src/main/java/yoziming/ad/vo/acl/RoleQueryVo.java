package yoziming.ad.vo.acl;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色查詢實體
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "角色查詢實體")
public class RoleQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "角色名稱")
	private String roleName;

}

