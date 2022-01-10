package yoziming.ad.servicehosp.service;

import org.springframework.data.domain.Page;
import yoziming.ad.model.hosp.Schedule;
import yoziming.ad.vo.hosp.ScheduleQueryVo;

import java.util.Map;

public interface ScheduleService {
    void save(Map<String, Object> paramMap);

    Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    void remove(String hoscode, String hosScheduleId);
}
