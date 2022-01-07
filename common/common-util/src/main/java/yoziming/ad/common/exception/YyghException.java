package yoziming.ad.common.exception;

import yoziming.ad.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定義全局異常類
 *
 * @author qy
 */
@Data
@ApiModel(value = "自定義全局異常類")
public class YyghException extends RuntimeException {

    @ApiModelProperty(value = "異常狀態碼")
    private Integer code;

    /**
     * 通過狀態碼和錯誤消息創建異常對象
     * @param message
     * @param code
     */
    public YyghException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚舉類型對象
     * @param resultCodeEnum
     */
    public YyghException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "YyghException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
