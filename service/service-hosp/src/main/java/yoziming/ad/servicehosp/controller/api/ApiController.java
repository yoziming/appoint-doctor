package yoziming.ad.servicehosp.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yoziming.ad.common.helper.HttpRequestHelper;
import yoziming.ad.common.result.Result;
import yoziming.ad.model.hosp.Department;
import yoziming.ad.model.hosp.Schedule;
import yoziming.ad.servicehosp.service.DepartmentService;
import yoziming.ad.servicehosp.service.HospitalService;
import yoziming.ad.servicehosp.service.ScheduleService;
import yoziming.ad.vo.hosp.DepartmentQueryVo;
import yoziming.ad.vo.hosp.ScheduleQueryVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "醫院api")
@RestController
@RequestMapping("/api/hosp")
@CrossOrigin
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ScheduleService scheduleService;

    // 上傳醫院
    @ApiOperation("上傳醫院")
    @PostMapping("saveHospital")
    public Result saveHospital(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 調用工具類把map中的string[]轉成obj
        Map<String, Object> stringObjectMap = HttpRequestHelper.switchMap(parameterMap);
        // base64編碼傳送過程中把"+"變成了" "空白，要轉換回來
        String logoData = (String) stringObjectMap.get("logoData");
        logoData = logoData.replaceAll(" ", "+");
        stringObjectMap.put("logoData", logoData);
        return hospitalService.save(stringObjectMap) ? Result.ok() : Result.build(230, "醫院編號已存在，修改請聯絡工程師");
    }

    @ApiOperation("查詢醫院")
    @PostMapping("hospital/show")
    public Result showHospital(HttpServletRequest request) {
        Map<String, String[]> parameterMap_0 = request.getParameterMap();
        Map<String, Object> parameterMap = HttpRequestHelper.switchMap(parameterMap_0);
        String hoscode = (String) parameterMap.get("hoscode");
        return Result.ok(hospitalService.showHospital(hoscode));
    }

    @ApiOperation("上傳科室")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        Map<String, String[]> parameterMap_0 = request.getParameterMap();
        Map<String, Object> parameterMap = HttpRequestHelper.switchMap(parameterMap_0);
        return departmentService.save(parameterMap) ? Result.ok() : Result.build(230, "醫院科室編號已存在，修改請聯絡工程師");
    }

    @ApiOperation("查詢科室")
    @PostMapping("department/list")
    public Result findDepartment(HttpServletRequest request) {
        Map<String, String[]> parameterMap_0 = request.getParameterMap();
        Map<String, Object> parameterMap = HttpRequestHelper.switchMap(parameterMap_0);
        // 獲取醫院編號
        String hoscode = (String) parameterMap.get("hoscode");
        // 獲取當前頁 和 每頁記錄數，沒指定(為空)就是1跟10
        int page = StringUtils.isEmpty(parameterMap.get("page")) ? 1 : Integer.parseInt((String) parameterMap.get(
                "page"));
        int limit = StringUtils.isEmpty(parameterMap.get("limit")) ? 10 : Integer.parseInt((String) parameterMap.get(
                "limit"));

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHoscode(hoscode);
        // 調用service方法
        Page<Department> pageModel = departmentService.findPageDepartment(page, limit, departmentQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("刪除科室")
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request) {
        Map<String, String[]> parameterMap_0 = request.getParameterMap();
        Map<String, Object> parameterMap = HttpRequestHelper.switchMap(parameterMap_0);
        // 醫院編號 和 科室編號
        String hoscode = (String) parameterMap.get("hoscode");
        String depcode = (String) parameterMap.get("depcode");
        if (depcode == null || hoscode == null) {
            return Result.build(230, "醫院或科室編號不存在");
        }
        departmentService.remove(hoscode, depcode);
        return Result.ok();
    }

    // 上傳排班接口
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        // 獲取傳遞過來科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        scheduleService.save(paramMap);
        return Result.ok();
    }

    // 查詢排班
    @PostMapping("schedule/list")
    public Result findSchedule(HttpServletRequest request) {
        //獲取傳遞過來科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //醫院編號
        String hoscode = (String) paramMap.get("hoscode");
        //科室編號
        String depcode = (String) paramMap.get("depcode");
        //當前頁 和 每頁記錄數
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String) paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : Integer.parseInt((String) paramMap.get("limit"));

        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHoscode(hoscode);
        scheduleQueryVo.setDepcode(depcode);
        // 調用service方法
        Page<Schedule> pageModel = scheduleService.findPageSchedule(page, limit, scheduleQueryVo);
        return Result.ok(pageModel);
    }

    //  刪除排班
    @PostMapping("schedule/remove")
    public Result remove(HttpServletRequest request) {
        //獲取傳遞過來科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //獲取醫院編號和排班編號
        String hoscode = (String) paramMap.get("hoscode");
        String hosScheduleId = (String) paramMap.get("hosScheduleId");
        scheduleService.remove(hoscode, hosScheduleId);
        return Result.ok();
    }

}