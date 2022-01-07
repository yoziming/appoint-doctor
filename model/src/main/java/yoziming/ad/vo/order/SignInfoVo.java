package yoziming.ad.vo.order;

import yoziming.ad.model.base.BaseMongoEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <p>
 * HospitalSet
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "簽名信息")
public class SignInfoVo  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "api基礎路徑")
	private String apiUrl;

	@ApiModelProperty(value = "簽名秘鑰")
	private String signKey;

}

