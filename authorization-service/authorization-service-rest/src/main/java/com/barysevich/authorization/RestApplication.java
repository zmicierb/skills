package com.barysevich.authorization;


import com.google.common.base.Predicates;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@ComponentScan("com.barysevich")
@EnableAutoConfiguration
@EnableSwagger2
public class RestApplication extends SpringBootServletInitializer
{
    public static void main(final String[] args) throws Exception
    {
        SpringApplication.run(RestApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application)
    {
        return application.sources(RestApplication.class).bannerMode(Banner.Mode.OFF);
    }


    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }


    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
                .title("Authorization service API")
                .description("Contains methods for external clients")
                .build();
    }
}