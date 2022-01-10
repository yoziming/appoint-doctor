package yoziming.ad.servicehosp.service.impl;

import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import yoziming.ad.model.hosp.Department;
import yoziming.ad.servicehosp.repository.DepartmentRepository;
import yoziming.ad.servicehosp.service.DepartmentService;
import yoziming.ad.vo.hosp.DepartmentQueryVo;

import java.util.Date;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public boolean save(Map<String, Object> parameterMap) {
        Department department = BeanUtils.mapToBean(parameterMap, Department.class);
        // 根據醫院編號 和 科室編號查詢
        Department departmentExist = departmentRepository.
                getDepartmentByHoscodeAndDepcode(department.getHoscode(), department.getDepcode());
        if (departmentExist == null) {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            departmentRepository.save(department);
            return true;
        }
        return false;
    }

    @Override
    public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {
        // 創建Pageable，設置當前頁和每頁記錄數，0是第一頁
        Pageable pageable = PageRequest.of(page - 1, limit);
        // 查詢用VO，所以要先造一個department去承接屬性
        Department department = new Department();
        org.springframework.beans.BeanUtils.copyProperties(departmentQueryVo, department);
        // 創建Example設定查詢條件
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example<Department> example = Example.of(department, matcher);

        Page<Department> all = departmentRepository.findAll(example, pageable);
        return all;
    }

    @Override
    public void remove(String hoscode, String depcode) {
        // 根據醫院編號 和 科室編號查詢
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department != null) {
            // 調用方法刪除
            departmentRepository.deleteById(department.getId());
        }
    }
}
