package br.com.brasilprev.api.config.swagger;

import br.com.brasilprev.api.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket resourceValidationapi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("resources")
                .ignoredParameterTypes(
                        Customer.class,
                        CustomerAddress.class,
                        Order.class,
                        Product.class,
                        LineItem.class,
                        ResponseEntity.class)
                .select()
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.regex("/api/.*"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    @Bean
    public Docket tokenValidationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("token")
                .select()
                .apis(RequestHandlerSelectors.any())
                //.paths(PathSelectors.regex("/api/.*"))
                .paths(PathSelectors.ant("/oauth/token"))
                .build();
    }

    @Bean
    public UiConfiguration tryItOutConfig() {
        final String[] methodsWithTryItOutButton = {};
        return UiConfigurationBuilder.builder().supportedSubmitMethods(methodsWithTryItOutButton).build();
    }
}
