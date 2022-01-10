package yoziming.ad.servicehosp.service;

import org.springframework.data.domain.Page;
import yoziming.ad.model.hosp.Department;
import yoziming.ad.vo.hosp.DepartmentQueryVo;

import java.util.Map;

public interface DepartmentService {
    boolean save(Map<String, Object> parameterMap);

    Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

    void remove(String hoscode, String depcode);
}
