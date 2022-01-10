package yoziming.ad.servicecmn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yoziming.ad.common.result.Result;
import yoziming.ad.servicecmn.service.DictService;

import javax.servlet.http.HttpServletResponse;

@Api(value = "數據字典", tags = "數據字典")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {
    @Autowired
    private DictService dictService;

    @ApiOperation("根據id查詢子數據列表")
    @GetMapping("getAllChildDictById/{id}")
    public Result getAllChildDictById(@PathVariable Long id) {
        return Result.ok(dictService.getAllChildDictById(id));

    }

    @ApiOperation("導出字典檔")
    @GetMapping("exportDict")
    public void exportDict(HttpServletResponse response) {
        dictService.exportData(response);
    }

    @ApiOperation("導入字典表格檔")
    @PostMapping("importDict")
    public Result importDict(MultipartFile file) {
        dictService.importDict(file);
        return Result.ok();
    }
}