package yoziming.ad.model.hosp;

import yoziming.ad.model.base.BaseEntity;
import yoziming.ad.model.base.BaseMongoEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * Schedule
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Schedule")
@Document("Schedule")
public class Schedule extends BaseMongoEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "醫院編號")
	@Indexed //普通索引
	private String hoscode;

	@ApiModelProperty(value = "科室編號")
	@Indexed //普通索引
	private String depcode;

	@ApiModelProperty(value = "職稱")
	private String title;

	@ApiModelProperty(value = "醫生名稱")
	private String docname;

	@ApiModelProperty(value = "擅長技能")
	private String skill;

	@ApiModelProperty(value = "排班日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date workDate;

	@ApiModelProperty(value = "排班時間（0：上午 1：下午）")
	private Integer workTime;

	@ApiModelProperty(value = "可預約數")
	private Integer reservedNumber;

	@ApiModelProperty(value = "剩餘預約數")
	private Integer availableNumber;

	@ApiModelProperty(value = "挂號費")
	private BigDecimal amount;

	@ApiModelProperty(value = "排班狀態（-1：停診 0：停約 1：可約）")
	private Integer status;

	@ApiModelProperty(value = "排班編號（醫院自己的排班主鍵）")
	@Indexed //普通索引
	private String hosScheduleId;

}

