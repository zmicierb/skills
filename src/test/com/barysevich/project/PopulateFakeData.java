package com.barysevich.project;

import com.barysevich.project.model.*;
import com.barysevich.project.service.*;
import com.github.javafaker.Faker;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by BarysevichD on 2017-06-15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
public class PopulateFakeData {

    @Autowired
    protected PersonService personService;

    @Autowired
    protected PositionService positionService;

    @Autowired
    protected DepartmentService departmentService;

    @Autowired
    protected SkillService skillService;

    @Autowired
    protected RowService rowService;

    @Autowired
    protected SkillSumService skillSumService;

    @Autowired
    protected CompanyInfoService companyInfoService;

    @Autowired
    protected ProjectService projectService;

    @Test
    public void populateDB() {

        Position position = positionService.findOne(1L);
        Department department = departmentService.findOne(1L);

        for (int i = 0; i < 5000; i++) {

            Faker faker = new Faker();

            String name = faker.name().firstName() + " " + faker.name().lastName();
            Date date = faker.date().between(new Date(0L), new Date());

            Person person = personService.save(new Person(name,
                    name.replace(" ", "") + "@test.com",
                    position,
                    department,
                    convertDate(date)));

            Set<Skill> skillSet = new HashSet<>();

            for (int j = 1; j <= (ThreadLocalRandom.current().nextInt(10, 100 + 1)); j++) {
                skillSet.add(skillService.findOne(Long.valueOf(ThreadLocalRandom.current().nextInt(1, 50 + 1))));
            }

            AtomicInteger weightIterator = new AtomicInteger(1);
            skillSet.forEach(skill -> {
                skillSumService.save(new SkillSum(person.getId(),
                        skill,
                        rowService.findOne(Long.valueOf(ThreadLocalRandom.current().nextInt(1, 6 + 1))),
                        weightIterator.getAndIncrement(),
                        skillSet.size()));
            });

            Set<Project> projectSet = new HashSet<>();

            for (int j = 1; j <= (ThreadLocalRandom.current().nextInt(1, 7 + 1)); j++) {
                Project project = new Project();
                project.setPersonId(person.getId());
                project.setDescription("desc");
                project.setResult("result");
                project.setResponsibility("responsibility");
                project.setPosition(position);
                projectSet.add(projectService.save(project));
            }

            Set<CompanyInfo> companyInfoSet = new HashSet<>();

            Date startDate = new Date();

            for (int j = 1; j <= (ThreadLocalRandom.current().nextInt(1, projectSet.size() + 1)); j++) {
                Date endDate = j == 1 ? null : startDate;
                startDate = faker.date().between((new Date(date.getTime())), startDate);

                String companyName = faker.company().name();

                companyInfoSet.add(companyInfoService.save(new CompanyInfo(companyName, convertDate(startDate), convertDate(endDate))));
            }

            Object[] companyArray = companyInfoSet.toArray();
            int projectPerCompany = projectSet.size() / companyInfoSet.size() == 0 ? 1 : projectSet.size() / companyInfoSet.size();
            AtomicInteger iterator1 = new AtomicInteger(0);
            projectSet.forEach(project -> {
                project.setCompanyInfo((CompanyInfo) companyArray[
                        iterator1.get() / projectPerCompany >= companyArray.length ?
                                companyArray.length - 1 :
                                iterator1.get() / projectPerCompany]);
                iterator1.incrementAndGet();
                projectService.update(project.getId(), project);
            });

            Object[] projectArray = projectSet.toArray();
            int skillPerProject = skillSet.size() / projectSet.size() == 0 ? 1 : skillSet.size() / projectSet.size();
            AtomicInteger iterator2 = new AtomicInteger(0);
            Map<Long, List<EnvironmentSkill>> envSkillMap = new HashMap<>();
            skillSet.forEach(skill -> {
                Project project = (Project) projectArray[
                        iterator2.get() / skillPerProject >= projectArray.length ?
                                projectArray.length - 1 :
                                iterator2.get() / skillPerProject];
                envSkillMap.computeIfAbsent(project.getId(), (k) ->
                        envSkillMap.put(k, new ArrayList<>())
                );

                envSkillMap.get(project.getId()).add(new EnvironmentSkill(skill, iterator2.get() + 1));
                iterator2.incrementAndGet();
            });

            envSkillMap.forEach((k, v) -> {
                Project project = projectService.findOne(k);
                project.setEnvironmentSkills(v);
                projectService.update(k, project);
            });
        }
    }

    private LocalDate convertDate(Date date) {
        if (date != null)
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        else
            return null;
    }

}
