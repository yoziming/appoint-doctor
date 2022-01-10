package yoziming.ad.hospitalmanage.service;

import java.util.Map;

public interface HospitalService {

    /**
     * 預約下單
     *
     * @param paramMap
     * @return
     */
    Map<String, Object> submitOrder(Map<String, Object> paramMap);

    /**
     * 更新支付狀態
     *
     * @param paramMap
     */
    void updatePayStatus(Map<String, Object> paramMap);

    /**
     * 更新取消預約狀態
     *
     * @param paramMap
     */
    void updateCancelStatus(Map<String, Object> paramMap);

}
