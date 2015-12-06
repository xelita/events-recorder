package com.xelita.recorder;

import com.google.common.base.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * Main class of the application.
 *
 * @author xelita
 */
@Slf4j
@EnableSwagger2
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Value("${info.env}")
    private String env;

    @Value("${info.env.description}")
    private String envInfo;

    @Value("${info.build.version}")
    private String buildVersion;

    // *************************************************************************
    //
    // Swagger configuration
    //
    // *************************************************************************

    /**
     * Return swagger Dockert.
     *
     * @return Docket
     */
    @Bean
    public Docket fullApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("full-api")
                .apiInfo(apiInfo())
                .select()
                .apis(withClassAnnotation(RestController.class))
                .paths(apiPaths())
                .build();
    }

    /**
     * Define paths used for swagger documentation,
     *
     * @return Predicate
     */
    private Predicate<String> apiPaths() {
        return PathSelectors.any();
    }

    /**
     * Return API information.
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Exception Recorder API")
                .version(this.buildVersion)
                .build();
    }

    // *************************************************************************
    //
    // Bootstrap
    //
    // *************************************************************************

    /**
     * Application launcher.
     *
     * @param args args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder().sources(Application.class).run(args);
    }

    // *************************************************************************
    //
    // Methods from CommandLineRunner interface
    //
    // *************************************************************************

    @Override
    public void run(String... strings) throws Exception {
        log.info("{}", this.envInfo);
    }
}

