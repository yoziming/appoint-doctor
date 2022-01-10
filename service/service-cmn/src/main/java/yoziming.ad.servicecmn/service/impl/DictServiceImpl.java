package yoziming.ad.servicecmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.joda.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import yoziming.ad.model.cmn.Dict;
import yoziming.ad.servicecmn.listener.DictListener;
import yoziming.ad.servicecmn.mapper.DictMapper;
import yoziming.ad.servicecmn.service.DictService;
import yoziming.ad.vo.cmn.DictEeVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    // 判斷id下面是否還有子節點
    private boolean hasChildren(long id) {
        QueryWrapper<Dict> qw = new QueryWrapper<>();
        // 以自己為爸爸查兒子數量
        qw.eq("parent_id", id);
        Integer count = baseMapper.selectCount(qw);
        // 有就是true
        return count > 0;
    }

    // 根據id查詢子數據列表
    @Cacheable(value = "dict", keyGenerator = "keyGenerator")
    @Override
    public List<Dict> getAllChildDictById(Long id) {
        QueryWrapper<Dict> qw = new QueryWrapper<>();
        // 根據id查表中子id，所以找parent_id是傳入的id的
        qw.eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(qw);
        // 給bean賦值，因為資料庫中沒記載是否有子節點
        // 對應的是@TableField(exist = false)
        for (Dict dict : dictList) {
            Long dictId = dict.getId();
            boolean b = this.hasChildren(dictId);
            // 這個this是當前類DictServiceImpl，調用自己的方法查
            // 某個dictId是否有子節點，我用dict.has找半天有點昏頭
            dict.setHasChildren(b);
        }
        return dictList;
    }

    /**
     * 導出字典資料
     *
     * @param response
     */
    @Override
    public void exportData(HttpServletResponse response) {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 檔案名想取中文用URLEncoder.encode
        String fileName = ("dict-export-" + new LocalDateTime());
        // 設定回應是"下載檔案"
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 查資料庫
        List<Dict> dictList = baseMapper.selectList(null);
        // 把資料塞到對應的excel表格vo實體類
        List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
        for (Dict dict : dictList) {
            DictEeVo dictVo = new DictEeVo();
            BeanUtils.copyProperties(dict, dictVo, DictEeVo.class);
            dictVoList.add(dictVo);
        }
        try {
            // 輸出到回應流讓用戶下載
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("dict").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 導入數據
     *
     * @param file
     */
    @CacheEvict(value = "dict", allEntries = true)
    @Override
    public void importDict(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), DictEeVo.class, new DictListener(baseMapper)).sheet().doRead();
        /*
        DictServiceImpl繼承了ServiceImpl<DictMapper, Dict>
        DictMapper又繼承了 BaseMapper<Dict>
        所以在DictServiceImpl的方法中baseMapper其實就是DictMapper
        */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
