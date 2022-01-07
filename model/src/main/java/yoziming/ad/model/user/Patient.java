package yoziming.ad.model.user;

import yoziming.ad.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * Patient
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Patient")
@TableName("patient")
public class Patient extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用戶id")
	@TableField("user_id")
	private Long userId;

	@ApiModelProperty(value = "姓名")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "證件類型")
	@TableField("certificates_type")
	private String certificatesType;

	@ApiModelProperty(value = "證件編號")
	@TableField("certificates_no")
	private String certificatesNo;

	@ApiModelProperty(value = "性別")
	@TableField("sex")
	private Integer sex;

	@ApiModelProperty(value = "出生年月")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@TableField("birthdate")
	private Date birthdate;

	@ApiModelProperty(value = "手機")
	@TableField("phone")
	private String phone;

	@ApiModelProperty(value = "是否結婚")
	@TableField("is_marry")
	private Integer isMarry;

	@ApiModelProperty(value = "省code")
	@TableField("province_code")
	private String provinceCode;

	@ApiModelProperty(value = "市code")
	@TableField("city_code")
	private String cityCode;

	@ApiModelProperty(value = "區code")
	@TableField("district_code")
	private String districtCode;

	@ApiModelProperty(value = "詳情地址")
	@TableField("address")
	private String address;

	@ApiModelProperty(value = "聯繫人姓名")
	@TableField("contacts_name")
	private String contactsName;

	@ApiModelProperty(value = "聯繫人證件類型")
	@TableField("contacts_certificates_type")
	private String contactsCertificatesType;

	@ApiModelProperty(value = "聯繫人證件號")
	@TableField("contacts_certificates_no")
	private String contactsCertificatesNo;

	@ApiModelProperty(value = "聯繫人手機")
	@TableField("contacts_phone")
	private String contactsPhone;

	@ApiModelProperty(value = "是否有醫保")
	@TableField("is_insure")
	private Integer isInsure;

	@ApiModelProperty(value = "就診卡")
	@TableField("card_no")
	private String cardNo;

	@ApiModelProperty(value = "狀態（0：默認 1：已認證）")
	@TableField("status")
	private String status;
}

