package yoziming.ad.servicecmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.BeanUtils;
import yoziming.ad.model.cmn.Dict;
import yoziming.ad.servicecmn.mapper.DictMapper;
import yoziming.ad.vo.cmn.DictEeVo;

public class DictListener extends AnalysisEventListener<DictEeVo> {

    // 構造器注入，因為easyExcel官方文檔不建議將listener交給spring管理
    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    // 一行一行讀取
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        // 讀取表格內容，轉成dict物件
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo, dict);
        // 寫到資料庫
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
