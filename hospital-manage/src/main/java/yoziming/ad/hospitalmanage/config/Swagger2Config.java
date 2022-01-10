package yoziming.ad.hospitalmanage.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置信息
 *
 * @author qy
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                //過濾掉admin路徑下的所有頁面
                .paths(Predicates.and(PathSelectors.regex("/P2P/.*")))
                //過濾掉所有error或error.*頁面
                //.paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder()
                .title("網站-API文檔")
                .description("本文檔描述了網站微服務接口定義")
                .version("1.0")
                .contact(new Contact("yoziming", "http://yoziming.ad", "xxx@xxx.com"))
                .build();
    }

}
