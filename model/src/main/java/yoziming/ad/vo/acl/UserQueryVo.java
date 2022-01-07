package yoziming.ad.vo.acl;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用戶查詢實體
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "用戶查詢實體")
public class UserQueryVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用戶名")
	private String username;

	@ApiModelProperty(value = "昵稱")
	private String nickName;

}

