package yoziming.ad.servicehosp.service.impl;

import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import yoziming.ad.model.hosp.Hospital;
import yoziming.ad.servicehosp.repository.HospitalRepository;
import yoziming.ad.servicehosp.service.HospitalService;
import yoziming.ad.vo.hosp.HospitalQueryVo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public boolean save(Map<String, Object> stringObjectMap) {
        // 將map轉成Hospital實體類物件
        // String string = JSONObject.toJSONString(stringObjectMap);
        // Hospital hospital = JSONObject.parseObject(string, Hospital.class);
        Hospital hospital = BeanUtils.mapToBean(stringObjectMap, Hospital.class);
        // 判斷是否已存在
        String hoscode = hospital.getHoscode();
        Hospital hospitalExist = hospitalRepository.getHospitalByHoscode(hoscode);
        /*  TODO 這裡有bug，他設定hoscode是唯一，但是又不標記成mongo的Id，物件沒有mongo的Id作為屬性，
        就沒辦法靠同Id視為更新的save去更新，永遠只會說重複不給存報錯，如果拿掉不重複標記，
        就變成一直存成新物件，修這個要從model層去改
        而MD5加密驗證的部分，覺得這邊他教的太混亂，應該寫在service層
        原理懂就好，就醫院方API把key用MD5加密傳過來，平台方去醫院設定mysql也抓那個key
        然後執行一樣的MD5加密，驗證是否相同，利用MD5不可逆推(暴力哈希碰撞除外)特性
        實現安全驗證
        */
        if (hospitalExist == null) {
            // 新物件
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
            return true;
        } else {
            // 已存在，要修改
            return false;
        }
    }

    @Override
    public Hospital showHospital(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }

    // 醫院列表(條件查詢分頁)
    @Override
    public Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        // 創建pageable對象
        Pageable pageable = PageRequest.of(page - 1, limit);
        // 創建條件匹配器
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        // hospitalSetQueryVo轉換Hospital對象
        Hospital hospital = new Hospital();
        org.springframework.beans.BeanUtils.copyProperties(hospitalQueryVo, hospital);
        // 創建對象
        Example<Hospital> example = Example.of(hospital, matcher);
        // 調用方法實現查詢
        Page<Hospital> pages = hospitalRepository.findAll(example, pageable);

        // 獲取查詢list集合，遍歷進行醫院等級封裝
        pages.getContent().stream().forEach(item -> {
            this.setHospitalHosType(item);
        });

        return pages;
    }

    //更新醫院上線狀態
    @Override
    public void updateStatus(String id, Integer status) {
        //根據id查詢醫院信息
        Hospital hospital = hospitalRepository.findById(id).get();
        //設置修改的值
        hospital.setStatus(status);
        hospital.setUpdateTime(new Date());
        hospitalRepository.save(hospital);
    }

    @Override
    public Map<String, Object> getHospById(String id) {
        Map<String, Object> result = new HashMap<>();
        Hospital hospital = this.setHospitalHosType(hospitalRepository.findById(id).get());
        //醫院基本信息（包含醫院等級）
        result.put("hospital", hospital);
        //單獨處理更直觀
        result.put("bookingRule", hospital.getBookingRule());
        //不需要重複返回
        hospital.setBookingRule(null);
        return result;
    }
    //
    // //獲取醫院名稱
    // @Override
    // public String getHospName(String hoscode) {
    //     Hospital hospital = hospitalRepository.getHospitalByHoscode(hoscode);
    //     if (hospital != null) {
    //         return hospital.getHosname();
    //     }
    //     return null;
    // }
    //
    // //根據醫院名稱查詢
    // @Override
    // public List<Hospital> findByHosname(String hosname) {
    //     return hospitalRepository.findHospitalByHosnameLike(hosname);
    // }
    //
    // //根據醫院編號獲取醫院預約挂號詳情
    // @Override
    // public Map<String, Object> item(String hoscode) {
    //     Map<String, Object> result = new HashMap<>();
    //     //醫院詳情
    //     Hospital hospital = this.setHospitalHosType(this.getByHoscode(hoscode));
    //     result.put("hospital", hospital);
    //     //預約規則
    //     result.put("bookingRule", hospital.getBookingRule());
    //     //不需要重複返回
    //     hospital.setBookingRule(null);
    //     return result;
    // }

    // @Autowired
    // private DictFeignClient dictFeignClient;
    //
    // //獲取查詢list集合，遍歷進行醫院等級封裝
    // private Hospital setHospitalHosType(Hospital hospital) {
    //     //根據dictCode和value獲取醫院等級名稱
    //     String hostypeString = dictFeignClient.getName("Hostype", hospital.getHostype());
    //     //查詢省 市  地區
    //     String provinceString = dictFeignClient.getName(hospital.getProvinceCode());
    //     String cityString = dictFeignClient.getName(hospital.getCityCode());
    //     String districtString = dictFeignClient.getName(hospital.getDistrictCode());
    //
    //     hospital.getParam().put("fullAddress", provinceString + cityString + districtString);
    //     hospital.getParam().put("hostypeString", hostypeString);
    //     return hospital;
    // }
}
