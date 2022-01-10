package yoziming.ad.hospitalmanage.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPlus配置類
 *
 * @author qy
 */
@EnableTransactionManagement
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

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * SQL執行效率插件
     */
    //    @Bean
    //    @Profile({"dev","test"})// 設置 dev test 環境開啟
    //    public PerformanceInterceptor performanceInterceptor() {
    //        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    //        performanceInterceptor.setMaxTime(2000);
    //        performanceInterceptor.setFormat(true);
    //        return performanceInterceptor;
    //    }
}
