package yoziming.ad.servicehosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import yoziming.ad.model.hosp.HospitalSet;
import yoziming.ad.servicehosp.mapper.HospitalSetMapper;
import yoziming.ad.servicehosp.service.HospitalSetService;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {
}
