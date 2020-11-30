package com.flashtract;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static final Contact DEFAULT_CONTACT = new Contact("Daniel Almanza",
            "https://github.com/dalmanza", "dalmanza9119@gmail.com");

    @SuppressWarnings("rawtypes")
    public static final ApiInfo DEFAULT = new ApiInfo("Api Documentation",
            "Api description", "2.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<VendorExtension>());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT);
    }

}
