package com.porn.web.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Value("${swagger.enable:false}")
    private boolean swaggerEnable;

    @Bean
    public Docket createApi() {
        Docket docket = (new Docket(DocumentationType.SWAGGER_2)).apiInfo((new ApiInfoBuilder()).version("1.0.0").build()).groupName("1.0.0").enable(this.swaggerEnable).select().apis(RequestHandlerSelectors.basePackage("com.porn")).paths(PathSelectors.any()).build();
        return docket;
    }
}

