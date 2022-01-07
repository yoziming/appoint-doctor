package yoziming.ad.model.hosp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import yoziming.ad.model.base.BaseEntity;
import yoziming.ad.model.base.BaseMongoEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * RegisterRule
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "預約規則")
@Document("BookingRule")
public class BookingRule {

	@ApiModelProperty(value = "預約周期")
	private Integer cycle;

	@ApiModelProperty(value = "放號時間")
	private String releaseTime;

	@ApiModelProperty(value = "停掛時間")
	private String stopTime;

	@ApiModelProperty(value = "退號截止天數（如：就診前一天為-1，當天為0）")
	private Integer quitDay;

	@ApiModelProperty(value = "退號時間")
	private String quitTime;

	@ApiModelProperty(value = "預約規則")
	private List<String> rule;

	/**
	 *
	 * @param rule
	 */
	public void setRule(String rule) {
		if(!StringUtils.isEmpty(rule)) {
			this.rule = JSONArray.parseArray(rule, String.class);
		}
	}

}

