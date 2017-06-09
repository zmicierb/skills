package com.barysevich.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@SpringBootApplication
//@EntityScan(basePackages = {"com.barysevich.project.model"})
//@EnableJpaRepositories(basePackages = {"com.barysevich.project.repository"})
//@ComponentScan(basePackages = {"com.barysevich.project.controller", "com.barysevich.project.service"})
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        System.setProperty("org.apache.tomcat.util.buf.UDecoder.ALLOW_ENCODED_SLASH", "true");
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setUrlDecode(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
