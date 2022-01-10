package yoziming.ad.hospitalmanage.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import yoziming.ad.hospitalmanage.service.ApiService;
import yoziming.ad.hospitalmanage.service.HospitalService;
import yoziming.ad.hospitalmanage.utils.HospitalException;
import yoziming.ad.hospitalmanage.utils.HttpRequestHelper;
import yoziming.ad.hospitalmanage.utils.Result;
import yoziming.ad.hospitalmanage.utils.ResultCodeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author qy
 */
@Api(tags = "醫院管理接口")
@RestController
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private ApiService apiService;

    /**
     * 預約下單
     *
     * @param request
     * @return
     */
    @PostMapping("/order/submitOrder")
    public Result AgreeAccountLendProject(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
            //			if(!HttpRequestHelper.isSignEquals(paramMap, apiService.getSignKey())) {
            //				throw new YyghException(ResultCodeEnum.SIGN_ERROR);
            //			}

            Map<String, Object> resultMap = hospitalService.submitOrder(paramMap);
            return Result.ok(resultMap);
        } catch (HospitalException e) {
            return Result.fail().message(e.getMessage());
        }
    }

    /**
     * 更新支付狀態
     *
     * @param request
     * @return
     */
    @PostMapping("/order/updatePayStatus")
    public Result updatePayStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
            if (!HttpRequestHelper.isSignEquals(paramMap, apiService.getSignKey())) {
                throw new HospitalException(ResultCodeEnum.SIGN_ERROR);
            }

            hospitalService.updatePayStatus(paramMap);
            return Result.ok();
        } catch (HospitalException e) {
            return Result.fail().message(e.getMessage());
        }
    }

    /**
     * 更新取消預約狀態
     *
     * @param request
     * @return
     */
    @PostMapping("/order/updateCancelStatus")
    public Result updateCancelStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
            //			if(!HttpRequestHelper.isSignEquals(paramMap, apiService.getSignKey())) {
            //				throw new YyghException(ResultCodeEnum.SIGN_ERROR);
            //			}

            hospitalService.updateCancelStatus(paramMap);
            return Result.ok();
        } catch (HospitalException e) {
            return Result.fail().message(e.getMessage());
        }
    }
}

