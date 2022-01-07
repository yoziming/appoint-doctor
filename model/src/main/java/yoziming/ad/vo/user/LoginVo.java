package yoziming.ad.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="登錄對象")
public class LoginVo {

    @ApiModelProperty(value = "openid")
    private String openid;

    @ApiModelProperty(value = "手機號")
    private String phone;

    @ApiModelProperty(value = "密碼")
    private String code;

    @ApiModelProperty(value = "IP")
    private String ip;
}
