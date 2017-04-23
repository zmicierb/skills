package com.barysevich.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.barysevich.project.model"})
@EnableJpaRepositories(basePackages = {"com.barysevich.project.repository"})
@ComponentScan(basePackages = {"com.barysevich.project.controller", "com.barysevich.project.service"})
public class Application {

//    @Bean
//    CommandLineRunner init(PersonRepository personRepository, PositionRepository positionRepository, DepartmentRepository departmentRepository) {
//        DataLoader dataLoader = new DataLoader(personRepository, positionRepository, departmentRepository);
//        return (evt) -> dataLoader.run(new DefaultApplicationArguments(new String[0]));
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
