package yoziming.ad.hospitalmanage.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoziming.ad.hospitalmanage.mapper.OrderInfoMapper;
import yoziming.ad.hospitalmanage.mapper.ScheduleMapper;
import yoziming.ad.hospitalmanage.model.OrderInfo;
import yoziming.ad.hospitalmanage.model.Patient;
import yoziming.ad.hospitalmanage.model.Schedule;
import yoziming.ad.hospitalmanage.service.HospitalService;
import yoziming.ad.hospitalmanage.utils.HospitalException;
import yoziming.ad.hospitalmanage.utils.ResultCodeEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private ScheduleMapper hospitalMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> submitOrder(Map<String, Object> paramMap) {
        log.info(JSONObject.toJSONString(paramMap));
        String hoscode = (String) paramMap.get("hoscode");
        String depcode = (String) paramMap.get("depcode");
        String hosScheduleId = (String) paramMap.get("hosScheduleId");
        String reserveDate = (String) paramMap.get("reserveDate");
        String reserveTime = (String) paramMap.get("reserveTime");
        String amount = (String) paramMap.get("amount");

        Schedule schedule = this.getSchedule("1L");
        if (null == schedule) {
            throw new HospitalException(ResultCodeEnum.DATA_ERROR);
        }

        if (!schedule.getHoscode().equals(hoscode)
                || !schedule.getDepcode().equals(depcode)
                || !schedule.getAmount().toString().equals(amount)) {
            throw new HospitalException(ResultCodeEnum.DATA_ERROR);
        }

        //就診人信息
        Patient patient = JSONObject.parseObject(JSONObject.toJSONString(paramMap), Patient.class);
        log.info(JSONObject.toJSONString(patient));
        //處理就診人業務
        Long patientId = this.savePatient(patient);

        Map<String, Object> resultMap = new HashMap<>();
        int availableNumber = schedule.getAvailableNumber().intValue() - 1;
        if (availableNumber > 0) {
            schedule.setAvailableNumber(availableNumber);
            hospitalMapper.updateById(schedule);

            //記錄預約記錄
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setPatientId(patientId);
            orderInfo.setScheduleId(1L);
            int number = schedule.getReservedNumber().intValue() - schedule.getAvailableNumber().intValue();
            orderInfo.setNumber(number);
            orderInfo.setAmount(new BigDecimal(amount));
            String fetchTime = "0".equals(reserveDate) ? " 09:30前" : " 14:00前";
            orderInfo.setFetchTime(reserveTime + fetchTime);
            orderInfo.setFetchAddress("一樓9號窗口");
            //默認 未支付
            orderInfo.setOrderStatus(0);
            orderInfoMapper.insert(orderInfo);

            resultMap.put("resultCode", "0000");
            resultMap.put("resultMsg", "預約成功");
            //預約記錄唯一標識（醫院預約記錄主鍵）
            resultMap.put("hosRecordId", orderInfo.getId());
            //預約號序
            resultMap.put("number", number);
            //取號時間
            resultMap.put("fetchTime", reserveDate + "09:00前");
            ;
            //取號地址
            resultMap.put("fetchAddress", "一層114窗口");
            ;
            //排班可預約數
            resultMap.put("reservedNumber", schedule.getReservedNumber());
            //排班剩餘預約數
            resultMap.put("availableNumber", schedule.getAvailableNumber());
        } else {
            throw new HospitalException(ResultCodeEnum.DATA_ERROR);
        }
        return resultMap;
    }

    @Override
    public void updatePayStatus(Map<String, Object> paramMap) {
        String hoscode = (String) paramMap.get("hoscode");
        String hosRecordId = (String) paramMap.get("hosRecordId");

        OrderInfo orderInfo = orderInfoMapper.selectById(hosRecordId);
        if (null == orderInfo) {
            throw new HospitalException(ResultCodeEnum.DATA_ERROR);
        }
        //已支付
        orderInfo.setOrderStatus(1);
        orderInfo.setPayTime(new Date());
        orderInfoMapper.updateById(orderInfo);
    }

    @Override
    public void updateCancelStatus(Map<String, Object> paramMap) {
        String hoscode = (String) paramMap.get("hoscode");
        String hosRecordId = (String) paramMap.get("hosRecordId");

        OrderInfo orderInfo = orderInfoMapper.selectById(hosRecordId);
        if (null == orderInfo) {
            throw new HospitalException(ResultCodeEnum.DATA_ERROR);
        }
        //已取消
        orderInfo.setOrderStatus(-1);
        orderInfo.setQuitTime(new Date());
        orderInfoMapper.updateById(orderInfo);
    }

    private Schedule getSchedule(String frontSchId) {
        return hospitalMapper.selectById(frontSchId);
    }

    /**
     * 醫院處理就診人信息
     *
     * @param patient
     */
    private Long savePatient(Patient patient) {
        // 業務：略
        return 1L;
    }

}
