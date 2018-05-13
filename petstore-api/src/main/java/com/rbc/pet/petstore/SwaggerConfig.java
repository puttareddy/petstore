package com.rbc.pet.petstore;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("pets").apiInfo(apiInfo()).select().paths(regex("/api/pet.*"))
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring REST Petstore").description("This is a sample Petstore server.")
            .contact(new Contact("Puttaiah Arugunta", "https://github.com/puttareddy/angular-boot", "puttareddy.mca@gmail.com"))
            .license("Apache License Version 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("2.0")
            .build();
    }
}
