package com.saude.sksaude.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

//    @Bean
//    public GroupedOpenApi adminApi() {
//        return new GroupedOpenApi(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("org.github.springshop.web.admin"))
//                .paths(PathSelectors.regex("/admin.*"))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(Admin.class))
//                .build()
//                .groupName("springshop-admin")
//                .apiInfo(apiInfo());
//    }
}
