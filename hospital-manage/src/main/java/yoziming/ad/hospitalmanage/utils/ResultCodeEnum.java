package yoziming.ad.hospitalmanage.utils;

import lombok.Getter;

/**
 * 統一返回結果狀態信息類
 *
 * @author qy
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(201, "失敗"),
    SERVICE_ERROR(202, "服務異常"),
    DATA_ERROR(204, "數據異常"),

    SIGN_ERROR(300, "簽名錯誤"),

    PAY_PASSWORD_ERROR(401, "支付密碼錯誤"),
    REPEAT_ERROR(402, "重複提交"),

    INVEST_AMMOUNT_MORE_ERROR(501, "出借金額已經多餘標的金額"),
    RETURN_AMMOUNT_MORE_ERROR(502, "還款金額不正確"),
    PROJECT_AMMOUNT_ERROR(503, "標的金額不一致");

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
