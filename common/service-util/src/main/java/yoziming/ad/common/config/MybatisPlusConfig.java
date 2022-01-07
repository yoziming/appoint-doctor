package yoziming.ad.common.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlus配置類
 */
@EnableTransactionManagement //事務處理
@Configuration
@MapperScan("yoziming.ad.*.mapper")
public class MybatisPlusConfig {

    /**
     * 分頁插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // paginationInterceptor.setLimit(你的最大單頁限制數量，默認 500 條，小於 0 如 -1 不受限制);
        return paginationInterceptor;
    }

    /**
     * 樂觀鎖配置
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
