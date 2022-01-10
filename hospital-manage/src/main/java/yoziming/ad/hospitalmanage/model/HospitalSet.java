package yoziming.ad.hospitalmanage.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * HospitalSet
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "HospitalSet")
@TableName("hospital_set")
public class HospitalSet extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "醫院編號")
    private String hoscode;

    @ApiModelProperty(value = "簽名秘鑰")
    private String signKey;

    @ApiModelProperty(value = "api基礎路徑")
    private String apiUrl;

}

