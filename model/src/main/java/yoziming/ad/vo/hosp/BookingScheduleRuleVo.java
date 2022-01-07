package yoziming.ad.vo.hosp;

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
 * RegisterRule
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "可預約排班規則數據")
public class BookingScheduleRuleVo {

	@ApiModelProperty(value = "可預約日期")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date workDate;

	@ApiModelProperty(value = "可預約日期")
	@JsonFormat(pattern = "MM月dd日")
	private Date workDateMd; //方便頁面使用

	@ApiModelProperty(value = "周幾")
	private String dayOfWeek;

	@ApiModelProperty(value = "就診醫生人數")
	private Integer docCount;

	@ApiModelProperty(value = "科室可預約數")
	private Integer reservedNumber;

	@ApiModelProperty(value = "科室剩餘預約數")
	private Integer availableNumber;

	@ApiModelProperty(value = "狀態 0：正常 1：即將放號 -1：當天已停止挂號")
	private Integer status;
}

