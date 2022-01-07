package yoziming.ad.vo.hosp;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HospitalSetQueryVo {

    @ApiModelProperty(value = "醫院名稱")
    private String hosname;

    @ApiModelProperty(value = "醫院編號")
    private String hoscode;
}
