package yoziming.ad.servicehosp.service;

import org.springframework.data.domain.Page;
import yoziming.ad.model.hosp.Hospital;
import yoziming.ad.vo.hosp.HospitalQueryVo;

import java.util.Map;

public interface HospitalService {
    boolean save(Map<String, Object> stringObjectMap);

    Hospital showHospital(String hoscode);

    Page<Hospital> selectHospPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    Map<String, Object> getHospById(String id);

    void updateStatus(String id, Integer status);
}
