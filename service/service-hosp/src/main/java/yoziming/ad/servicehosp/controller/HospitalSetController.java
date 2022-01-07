package yoziming.ad.servicehosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import yoziming.ad.common.result.Result;
import yoziming.ad.common.utils.MD5;
import yoziming.ad.model.hosp.HospitalSet;
import yoziming.ad.servicehosp.service.HospitalSetService;
import yoziming.ad.vo.hosp.HospitalQueryVo;

import java.util.List;
import java.util.Random;

@Api(tags = "醫院設定管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@CrossOrigin
public class HospitalSetController {

    // 注入service
    @Autowired
    private HospitalSetService hospitalSetService;

    // 查詢醫院表所有訊息
    @ApiOperation("查詢所有醫院設定訊息")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        return Result.ok(hospitalSetService.list());
    }

    // 邏輯刪除指定醫院
    @ApiOperation("邏輯刪除指定醫院設定")
    @ApiParam(name = "id", value = "醫院設定id", required = true)
    @DeleteMapping("{id}")
    public Result removeHospitalSet(@PathVariable Long id) {
        return hospitalSetService.removeById(id) ? Result.ok() : Result.fail();
    }

    // 條件查詢帶分頁
    @ApiOperation("條件查詢帶分頁")
    @PostMapping("findPageHospitalSet/{current}/{limit}")
    public Result findPageHospitalSet(@PathVariable long current,
                                      @PathVariable long limit,
                                      @RequestBody(required = false) HospitalQueryVo hospitalQueryVo) {
        // 建造Page物件，引用是mp的
        Page<HospitalSet> page = new Page<>(current, limit);
        // 設定查詢條件
        QueryWrapper<HospitalSet> qw = new QueryWrapper<>();
        // 先判斷傳來的資料是不是空
        String hosname = hospitalQueryVo.getHosname(); // 醫院名
        String hoscode = hospitalQueryVo.getHoscode(); // 醫院編號
        if (!StringUtils.isEmpty(hosname)) {
            // 第一個參數是資料庫中欄位名稱
            qw.like("hosname", hosname);
        }
        if (!StringUtils.isEmpty(hoscode)) {
            qw.like("hoscode", hoscode);
        }

        // 查詢並返回
        Page<HospitalSet> resPage = hospitalSetService.page(page, qw);
        return Result.ok(resPage);
    }

    // 添加醫院設定
    @ApiOperation("添加醫院設定")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        // 設定醫院狀態，1可用，0不可用
        hospitalSet.setStatus(1);
        // 隨機生成32位簽名密鑰
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        // 返回
        return hospitalSetService.save(hospitalSet) ? Result.ok() : Result.fail();
    }

    // 根據id查醫院
    @ApiOperation("根據id查醫院設定訊息")
    @GetMapping("getHospitalSet/{id}")
    public Result getHospitalSet(@PathVariable Long id) {
        return Result.ok(hospitalSetService.getById(id));
    }

    // 修改醫院設定
    @ApiOperation("修改醫院設定")
    @PostMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        return hospitalSetService.updateById(hospitalSet) ? Result.ok() : Result.fail();
    }

    // 批量刪除醫院設定
    @ApiOperation("批量刪除醫院設定")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        return hospitalSetService.removeByIds(idList) ? Result.ok() : Result.fail();
    }

// 修改醫院鎖定狀態
@ApiOperation("修改醫院鎖定狀態")
@PutMapping("lockHospitalSet/{id}/{status}")
public Result lockHospitalSet(@PathVariable Long id,
                              @PathVariable Integer status) {
    HospitalSet hospitalSet = hospitalSetService.getById(id);
    hospitalSet.setStatus(status);
    return hospitalSetService.updateById(hospitalSet) ? Result.ok() : Result.fail();
}

// 發送密鑰
@ApiOperation("發送密鑰")
@PostMapping("sendKey/{id}")
public Result sendKey(@PathVariable Long id) {
    HospitalSet hospitalSet = hospitalSetService.getById(id);
    String signKey = hospitalSet.getSignKey();
    String hoscode = hospitalSet.getHoscode();
    // TODO 簡訊驗證
    return Result.ok();
}

}
