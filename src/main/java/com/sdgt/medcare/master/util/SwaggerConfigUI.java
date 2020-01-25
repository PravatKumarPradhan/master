package com.sdgt.medcare.master.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Pravat Kumar Pradhan
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigUI {

@Bean
public Docket  MasterAPI(){
return new Docket(DocumentationType.SWAGGER_2)
        .select()
       .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
      // .apis(RequestHandlerSelectors.basePackage("com.sdgt.medcare.master.controller"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(metaData());
    }
    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Master Service APIs")
                .description("REST END Points for Master Service")
                .version("2.0.0")
                .license("SD Global Technologies ")
                .licenseUrl("https://www.sdglobaltech.com")
                .contact(new Contact("SD GlobalTechnologies", "https://www.sdglobaltech.com/", "info@sdglobaltech.com"))
                .build();
    }

    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
