package yoziming.ad.hospitalmanage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * Schedule
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Schedule")
@TableName("schedule")
public class Schedule extends BaseNoAutoEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "醫院編號")
    @TableField("hoscode")
    private String hoscode;

    @ApiModelProperty(value = "科室編號")
    @TableField("depcode")
    private String depcode;

    @ApiModelProperty(value = "職稱")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "醫生名稱")
    @TableField("docname")
    private String docname;

    @ApiModelProperty(value = "擅長技能")
    @TableField("skill")
    private String skill;

    @ApiModelProperty(value = "安排日期")
    @TableField("work_date")
    private String workDate;

    @ApiModelProperty(value = "安排時間（0：上午 1：下午）")
    @TableField("work_time")
    private Integer workTime;

    @ApiModelProperty(value = "可預約數")
    @TableField("reserved_number")
    private Integer reservedNumber;

    @ApiModelProperty(value = "剩餘預約數")
    @TableField("available_number")
    private Integer availableNumber;

    @ApiModelProperty(value = "挂號費")
    @TableField("amount")
    private String amount;

    @ApiModelProperty(value = "排班狀態（-1：停診 0：停約 1：可約）")
    @TableField("status")
    private Integer status;
}

