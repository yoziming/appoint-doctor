package yoziming.ad.model.cms;

import yoziming.ad.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 首頁Banner實體
 * </p>
 *
 * @author qy
 * @since 2019-11-08
 */
@Data
@ApiModel(description = "首頁Banner實體")
@TableName("banner")
public class Banner extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "標題")
	@TableField("title")
	private String title;

	@ApiModelProperty(value = "圖片地址")
	@TableField("image_url")
	private String imageUrl;

	@ApiModelProperty(value = "鏈接地址")
	@TableField("link_url")
	private String linkUrl;

	@ApiModelProperty(value = "排序")
	@TableField("sort")
	private Integer sort;

}

