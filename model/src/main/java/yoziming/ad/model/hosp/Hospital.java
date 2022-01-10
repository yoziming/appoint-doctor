package yoziming.ad.model.hosp;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import yoziming.ad.model.base.BaseMongoEntity;

/**
 * <p>
 * Hospital
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Hospital")
@Document("Hospital")
public class Hospital extends BaseMongoEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "醫院編號")
    @Indexed(unique = true) //唯一索引
    private String hoscode;

    @ApiModelProperty(value = "醫院名稱")
    @Indexed //普通索引
    private String hosname;

    @ApiModelProperty(value = "醫院類型")
    private String hostype;

    @ApiModelProperty(value = "省code")
    private String provinceCode;

    @ApiModelProperty(value = "市code")
    private String cityCode;

    @ApiModelProperty(value = "區code")
    private String districtCode;

    @ApiModelProperty(value = "詳情地址")
    private String address;

    @ApiModelProperty(value = "醫院logo")
    private String logoData;

    @ApiModelProperty(value = "醫院簡介")
    private String intro;

    @ApiModelProperty(value = "坐車路線")
    private String route;

    @ApiModelProperty(value = "狀態 0：未上線 1：已上線")
    private Integer status;

    //預約規則
    @ApiModelProperty(value = "預約規則")
    private BookingRule bookingRule;

    public void setBookingRule(String bookingRule) {
        this.bookingRule = JSONObject.parseObject(bookingRule, BookingRule.class);
    }

}

