package yoziming.ad.servicecmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import yoziming.ad.model.cmn.Dict;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {
    List<Dict> getAllChildDictById(Long id);

    void exportData(HttpServletResponse response);

    void importDict(MultipartFile file);
}
