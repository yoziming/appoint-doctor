package yoziming.ad.model.acl;

import yoziming.ad.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 權限
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "權限")
@TableName("acl_permission")
public class Permission extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "所屬上級")
	@TableField("pid")
	private Long pid;

	@ApiModelProperty(value = "名稱")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "類型(1:菜單,2:按鈕)")
	@TableField("type")
	private Integer type;

	@ApiModelProperty(value = "權限值")
	@TableField("permission_value")
	private String permissionValue;

	@ApiModelProperty(value = "路徑")
	@TableField("path")
	private String path;

	@ApiModelProperty(value = "component")
	@TableField("component")
	private String component;

	@ApiModelProperty(value = "圖標")
	@TableField("icon")
	private String icon;

	@ApiModelProperty(value = "狀態(0:禁止,1:正常)")
	@TableField("status")
	private Integer status;

	@ApiModelProperty(value = "層級")
	@TableField(exist = false)
	private Integer level;

	@ApiModelProperty(value = "下級")
	@TableField(exist = false)
	private List<Permission> children;

	@ApiModelProperty(value = "是否選中")
	@TableField(exist = false)
	private boolean isSelect;

}

