package com.barysevich.project;

import com.barysevich.project.model.*;
import com.barysevich.project.service.*;
import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by BarysevichD on 2017-06-15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

        Random generator = new Random();

        Position position = positionService.findOne(1L);
        Department department = departmentService.findOne(1L);

        for (int i = 0; i < 5000; i++) {

            Faker faker = new Faker();

            String name = faker.name().firstName() + " " + faker.name().lastName();
            Date date = faker.date().between(new Date(0L), new Date());

            Person person = personService.save(new Person(name,
                    name + "@test.com",
                    position,
                    department,
                    convertDate(date)));

            Set<Skill> skillSet = new HashSet<>();

            for (int j = 1; j <= (generator.nextInt(30) + 1); j++) {
                skillSet.add(skillService.findOne(Long.valueOf(generator.nextInt(10) + 1)));
            }

            skillSet.forEach(skill -> {
                int k = 1;
                skillSumService.save(new SkillSum(person.getId(), skill, rowService.findOne(Long.valueOf(generator.nextInt(6) + 1)), k++));
            });

            Set<Project> projectSet = new HashSet<>();

            for (int j = 1; j <= (generator.nextInt(7) + 1); j++) {
                Project project = new Project();
                project.setPersonId(person.getId());
                project.setDescription("desc");
                project.setResult("result");
                project.setResponsibility("responsibility");
                project.setPosition(position);
                projectSet.add(projectService.save(project));
            }

            Set<CompanyInfo> companyInfoSet = new HashSet<>();

            for (int j = 1; j <= (generator.nextInt(projectSet.size()) + 1); j++) {

                Date startDate = faker.date().between((new Date(date.getTime())), new Date());
                Date endDate = (j == 1 ? null : faker.date().between((new Date(startDate.getTime())), new Date()));
                String companyName = faker.company().name();

                companyInfoSet.add(companyInfoService.save(new CompanyInfo(companyName, convertDate(startDate), convertDate(endDate))));
            }

            Object[] companyArray = companyInfoSet.toArray();
            int projectPerCompany = projectSet.size() / companyInfoSet.size() + 1;
            AtomicInteger iterator1 = new AtomicInteger(0);
            projectSet.forEach(project -> {
                project.setCompanyInfo((CompanyInfo) companyArray[iterator1.getAndIncrement() / projectPerCompany]);
                projectService.update(project.getId(), project);
            });

            Object[] projectArray = projectSet.toArray();
            int skillPerProject = skillSet.size() / projectSet.size() + 1;
            AtomicInteger iterator2 = new AtomicInteger(0);
            Map<Long, List<EnvironmentSkill>> envSkillMap = new HashMap<>();
            skillSet.forEach(skill -> {
                Project project = (Project) projectArray[iterator2.getAndIncrement() / skillPerProject];
                envSkillMap.computeIfAbsent(project.getId(), (k) -> {
                    List<EnvironmentSkill> environmentSkills = new ArrayList<>();
                    environmentSkills.add(new EnvironmentSkill(skill, 1));
                    return envSkillMap.put(k, environmentSkills);
                });

                envSkillMap.computeIfPresent(project.getId(), (k, v) -> {
                    v.add(new EnvironmentSkill(skill, 1));
                    return v;
                });
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
