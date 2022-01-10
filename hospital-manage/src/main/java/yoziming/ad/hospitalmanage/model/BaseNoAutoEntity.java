package yoziming.ad.hospitalmanage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseNoAutoEntity implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "創建時間")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新時間")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "邏輯刪除(1:已刪除，0:未刪除)")
    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "其他參數")
    @TableField(exist = false)
    private Map<String, Object> param = new HashMap<>();
}
