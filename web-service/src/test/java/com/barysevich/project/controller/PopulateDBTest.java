//package com.barysevich.project.controller;
//
//import com.barysevich.project.model.*;
//import com.barysevich.project.service.*;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by BarysevichD on 2017-06-15.
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public abstract class PopulateDBTest {
//
//    @Autowired
//    protected TestRestTemplate restTemplate;
//
//    @Autowired
//    protected PersonService personService;
//
//    @Autowired
//    protected PositionService positionService;
//
//    @Autowired
//    protected DepartmentService departmentService;
//
//    @Autowired
//    protected SkillService skillService;
//
//    @Autowired
//    protected RowService rowService;
//
//    @Autowired
//    protected SkillSumService skillSumService;
//
//    @Autowired
//    protected CompanyInfoService companyInfoService;
//
//    @Autowired
//    protected ProjectService projectService;
//
//    @Autowired
//    protected ObjectMapper mapper;
//
//    protected String test = "test";
//
//    protected String[] testUserArray = new String[]{"Test1", "Test2", "Test3"};
//
//    @Before
//    public void populateDB() {
//
//        Department department = departmentService.save(new Department(test));
//        Position position = positionService.save(new Position(test));
//
//        Skill skill = skillService.save(new Skill(test));
//        Row row = rowService.save(new Row(test));
//
//        CompanyInfo companyInfo = companyInfoService.save(new CompanyInfo(test, LocalDate.now(), LocalDate.now()));
//
//        Arrays.asList(testUserArray).forEach(
//                a -> {
//                    Person person = personService.save(new Person(a,
//                            a + "@test.com",
//                            position,
//                            department,
//                            LocalDate.of(1970, Month.JANUARY, 1)));
//                    skillSumService.save(new SkillSum(person.getId(), skill, row, 1));
//                    EnvironmentSkill envSkill = new EnvironmentSkill(skill, 1);
//                    List<EnvironmentSkill> environmentSkills = new ArrayList<EnvironmentSkill>();
//                    environmentSkills.add(envSkill);
//                    Project project = new Project(person.getId(), position, test, test, test, environmentSkills, companyInfo);
//                    projectService.save(project);
//                }
//        );
//    }
//
//    @After
//    public void cleanDB() {
//
//        Iterable<Person> persons = personService.findByNameContainingIgnoreCaseForTest(test);
//
//        persons.forEach(
//                a -> {
//                    personService.delete(a.getId());
//                }
//        );
//
//        Iterable<Project> projects = projectService.findByDescriptionContainingIgnoreCaseForTest(test);
//
//        projects.forEach(
//                a -> {
//                    projectService.delete(a.getId());
//                }
//        );
//
//        Iterable<CompanyInfo> companyInfos = companyInfoService.findByNameContainingIgnoreCase(test, new PageRequest(0, 100));
//
//        companyInfos.forEach(
//                a -> {
//                    companyInfoService.delete(a.getId());
//                }
//        );
//
//        Iterable<Department> departments = departmentService.findByNameContainingIgnoreCase(test, new PageRequest(0, 100));
//
//        departments.forEach(
//                a -> {
//                    departmentService.delete(a.getId());
//                }
//        );
//
//        Iterable<Position> positions = positionService.findByNameContainingIgnoreCase(test, new PageRequest(0, 100));
//
//        positions.forEach(
//                a -> {
//                    positionService.delete(a.getId());
//                }
//        );
//
//        Iterable<Skill> skills = skillService.findByNameContainingIgnoreCase(test, new PageRequest(0, 100));
//
//        skills.forEach(
//                a -> {
//                    skillService.delete(a.getId());
//                }
//        );
//
//        Iterable<Row> rows = rowService.findByNameContainingIgnoreCase(test, new PageRequest(0, 100));
//
//        rows.forEach(
//                a -> {
//                    rowService.delete(a.getId());
//                }
//        );
//    }
//}
