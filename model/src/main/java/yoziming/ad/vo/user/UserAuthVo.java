package yoziming.ad.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="會員認證對象")
public class UserAuthVo {

    @ApiModelProperty(value = "用戶姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "證件類型")
    @TableField("certificates_type")
    private String certificatesType;

    @ApiModelProperty(value = "證件編號")
    @TableField("certificates_no")
    private String certificatesNo;

    @ApiModelProperty(value = "證件路徑")
    @TableField("certificates_url")
    private String certificatesUrl;

}
