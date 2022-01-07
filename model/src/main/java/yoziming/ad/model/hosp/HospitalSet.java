package yoziming.ad.model.hosp;

import yoziming.ad.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * HospitalSet
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "醫院設置")
@TableName("hospital_set")
public class HospitalSet extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "醫院名稱")
	@TableField("hosname")
	private String hosname;

	@ApiModelProperty(value = "醫院編號")
	@TableField("hoscode")
	private String hoscode;

	@ApiModelProperty(value = "api基礎路徑")
	@TableField("api_url")
	private String apiUrl;

	@ApiModelProperty(value = "簽名秘鑰")
	@TableField("sign_key")
	private String signKey;

	@ApiModelProperty(value = "聯繫人姓名")
	@TableField("contacts_name")
	private String contactsName;

	@ApiModelProperty(value = "聯繫人手機")
	@TableField("contacts_phone")
	private String contactsPhone;

	@ApiModelProperty(value = "狀態")
	@TableField("status")
	private Integer status;

}

