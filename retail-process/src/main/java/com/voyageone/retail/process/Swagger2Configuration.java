package com.voyageone.retail.process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Swagger2Configuration
 * @Description Swagger2配置类
 **/
@EnableSwagger2
@Configuration
public class Swagger2Configuration {

    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo("API文档", "API文档说明", "1.0"))
            .select()
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false);
    }


    private ApiInfo apiInfo(String name, String description, String version) {
        ApiInfo apiInfo = new ApiInfoBuilder().title(name).description(description).version(version).build();
        return apiInfo;
    }



}
