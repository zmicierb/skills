package by.barysevich.project.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@SpringBootApplication
@EntityScan(basePackages = {"by.barysevich.project.model"})
@EnableJpaRepositories(basePackages = {"by.barysevich.project.repository"})
@ComponentScan(basePackages = {"by.barysevich.project.controller"})
public class Application {

//    @Bean
//    CommandLineRunner init(PersonRepository personRepository) {
//        return (evt) -> Arrays.asList(
//                "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
//                .forEach(
//                        a -> {
//                            Person person = personRepository.save(new Person(a));
//                        });
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
