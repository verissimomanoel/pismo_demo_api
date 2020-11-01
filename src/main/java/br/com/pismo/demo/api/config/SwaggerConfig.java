/*
 * AuthSwaggerConfig.java
 *
 * This is a free software.
 */
package br.com.pismo.demo.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Configuration class of Swagger - Documentation API.
 *
 * @author Manoel Veríssimo dos Santos Neto
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String SWAGGER_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0";

    public static final String SWAGGER_LICENSE = "Apache License 2.0";

    @Value("${app.api.swagger.title}")
    private String title;

    @Value("${app.api.base}")
    private String path;

    @Value("${app.api.version}")
    private String version;

    @Value("${app.api.swagger.base-package}")
    private String basePackage;

    /**
     * Create a instance of {@link Docket} to generate the documentation with SWAGGER 2.
     *
     * @return
     */
    @Bean
    public Docket pismoDemoAPI() {
        ApiInfo apiInfo = new ApiInfoBuilder().title(title).version(version).license(SWAGGER_LICENSE).licenseUrl(SWAGGER_LICENSE_URL).build();

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage)).paths(PathSelectors.ant("/" + path + "/**"))
                .build();
    }
}
