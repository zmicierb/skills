package com.barysevich.project;


import com.barysevich.project.model.*;
import com.barysevich.project.service.*;
import com.github.javafaker.Faker;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
public class PopulateFakeData
{
    @Autowired
    private CompanyService companyService;

    @Autowired
    private PersonService personService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillsService skillsService;


    @Test
    public void populateDB()
    {
        final List<Skill> skills = new ArrayList<>();
        skillService.findAll(Pageable.unpaged()).forEach(skill -> skills.add(skill));

        for (int i = 0; i < 500; i++)
        {
            final Faker faker = new Faker();

            final String name = faker.name().firstName() + " " + faker.name().lastName();
            final String email = faker.internet().emailAddress();
            final String position = faker.educator().course();
            final String department = faker.team().creature();
            final Date date = faker.date().between(new Date(0L), new Date());

            final Person person = personService.update(new Person(
                    null,
                    name,
                    email,
                    position,
                    department,
                    convertDate(date)));


            final List<String> langs = fillCategory(skills);
            final List<String> techs = fillCategory(skills);
            final List<String> servers = fillCategory(skills);
            final List<String> dbs = fillCategory(skills);
            final List<String> systems = fillCategory(skills);
            final List<String> others = fillCategory(skills);

            skillsService.save(new Skills(
                    null,
                    person.getId(),
                    langs,
                    techs,
                    servers,
                    dbs,
                    systems,
                    others
            ));

            for (int j = 1; j <= (ThreadLocalRandom.current().nextInt(1, 6)); j++)
            {
                final List<Project> projects = new ArrayList<>();

                for (int k = 1; k <= (ThreadLocalRandom.current().nextInt(1, 4)); k++)
                {
                    projects.add(new Project(
                            fillCategory(skills),
                            faker.educator().course(),
                            faker.lorem().sentence(),
                            faker.lorem().sentence(),
                            faker.lorem().sentence()
                    ));
                }

                companyService.save(new Company(
                        null,
                        person.getId(),
                        faker.company().name(),
                        convertDate(faker.date().between((new Date(date.getTime())), new Date())),
                        convertDate(j == 1 ? null : new Date()),
                        projects
                ));
            }
        }
    }


    private LocalDate convertDate(final Date date)
    {
        if (date != null)
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        else
            return null;
    }


    private List<String> fillCategory(final List<Skill> skills)
    {
        final Set<String> category = new HashSet<>();

        for (int j = 1; j <= (ThreadLocalRandom.current().nextInt(2,  30)); j++)
        {
            category.add(skills.get(ThreadLocalRandom.current().nextInt(0, skills.size())).getName());
        }

        return new ArrayList<>(category);
    }
}