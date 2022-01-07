package yoziming.ad.model.user;

import yoziming.ad.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * UserInfo
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "UserInfo")
@TableName("user_info")
public class UserInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "微信openid")
	@TableField("openid")
	private String openid;

	@ApiModelProperty(value = "昵稱")
	@TableField("nick_name")
	private String nickName;

	@ApiModelProperty(value = "手機號")
	@TableField("phone")
	private String phone;

	@ApiModelProperty(value = "用戶姓名")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "證件類型")
	@TableField("certificates_type")
	private String certificatesType;

	@ApiModelProperty(value = "證件編號")
	@TableField("certificates_no")
	private String certificatesNo;

	@ApiModelProperty(value = "證件路徑")
	@TableField("certificates_url")
	private String certificatesUrl;

	@ApiModelProperty(value = "認證狀態（0：未認證 1：認證中 2：認證成功 -1：認證失敗）")
	@TableField("auth_status")
	private Integer authStatus;

	@ApiModelProperty(value = "狀態（0：鎖定 1：正常）")
	@TableField("status")
	private Integer status;

}

