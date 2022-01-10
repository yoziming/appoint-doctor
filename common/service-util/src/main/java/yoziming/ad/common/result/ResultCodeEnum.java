package yoziming.ad.common.result;

import lombok.Getter;

/**
 * 統一返回結果狀態信息類
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),
    FAIL(201, "失敗"),
    PARAM_ERROR(202, "參數不正確"),
    SERVICE_ERROR(203, "服務異常"),
    DATA_ERROR(204, "數據異常"),
    DATA_UPDATE_ERROR(205, "數據版本異常"),

    LOGIN_AUTH(208, "未登陸"),
    PERMISSION(209, "沒有權限"),

    CODE_ERROR(210, "驗證碼錯誤"),
    //    LOGIN_MOBLE_ERROR(211, "賬號不正確"),
    LOGIN_DISABLED_ERROR(212, "改用戶已被禁用"),
    REGISTER_MOBLE_ERROR(213, "手機號已被使用"),
    LOGIN_AURH(214, "需要登錄"),
    LOGIN_ACL(215, "沒有權限"),

    URL_ENCODE_ERROR(216, "URL編碼失敗"),
    ILLEGAL_CALLBACK_REQUEST_ERROR(217, "非法回調請求"),
    FETCH_ACCESSTOKEN_FAILD(218, "獲取accessToken失敗"),
    FETCH_USERINFO_ERROR(219, "獲取用戶信息失敗"),
    //LOGIN_ERROR( 23005, "登錄失敗"),

    PAY_RUN(220, "支付中"),
    CANCEL_ORDER_FAIL(225, "取消訂單失敗"),
    CANCEL_ORDER_NO(225, "不能取消預約"),

    HOSCODE_EXIST(230, "醫院編號已經存在"),
    NUMBER_NO(240, "可預約號不足"),
    TIME_NO(250, "當前時間不可以預約"),

    SIGN_ERROR(300, "簽名錯誤"),
    HOSPITAL_OPEN(310, "醫院未開通，暫時不能訪問"),
    HOSPITAL_LOCK(320, "醫院被鎖定，暫時不能訪問"),
    ;

    private Integer code;
    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
