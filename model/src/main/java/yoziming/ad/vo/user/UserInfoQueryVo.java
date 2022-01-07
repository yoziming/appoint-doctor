package yoziming.ad.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="會員搜索對象")
public class UserInfoQueryVo {

    @ApiModelProperty(value = "關鍵字")
    private String keyword;

    @ApiModelProperty(value = "狀態")
    private Integer status;

    @ApiModelProperty(value = "認證狀態")
    private Integer authStatus;

    @ApiModelProperty(value = "創建時間")
    private String createTimeBegin;

    @ApiModelProperty(value = "創建時間")
    private String createTimeEnd;

}
