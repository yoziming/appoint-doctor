package yoziming.ad.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseMongoEntity implements Serializable {

    @ApiModelProperty(value = "id")
    @Id
    private String id;

    @ApiModelProperty(value = "創建時間")
    private Date createTime;

    @ApiModelProperty(value = "更新時間")
    private Date updateTime;

    @ApiModelProperty(value = "邏輯刪除(1:已刪除，0:未刪除)")
    private Integer isDeleted;

    @ApiModelProperty(value = "其他參數")
    @Transient //被該註解標註的，將不會被錄入到數據庫中。只作為普通的javaBean屬性
    private Map<String,Object> param = new HashMap<>();
}
