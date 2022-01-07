package yoziming.ad.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="註冊對象")
public class RegisterVo {

    @ApiModelProperty(value = "手機號")
    private String mobile;

    @ApiModelProperty(value = "密碼")
    private String password;

    @ApiModelProperty(value = "驗證碼")
    private String code;
}
