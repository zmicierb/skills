package com.barysevich.project;

import com.barysevich.project.model.Department;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.Position;
import com.barysevich.project.repository.DepartmentRepository;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by dima on 4/23/17.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private PersonRepository personRepository;

    private PositionRepository positionRepository;

    private DepartmentRepository departmentRepository;

    @Autowired
    public DataLoader(PersonRepository personRepository, PositionRepository positionRepository, DepartmentRepository departmentRepository) {
        this.personRepository = personRepository;
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
    }

    public void run(ApplicationArguments args) {
        personRepository.deleteAll();
        positionRepository.deleteAll();
        departmentRepository.deleteAll();
        Department department = departmentRepository.save(new Department("Application Development department"));
        Position position = positionRepository.save(new Position("Java Developer"));
        personRepository.save(new Person("Dzmitry Barysevich",
                position,
                department,
                LocalDate.of(1987, Month.JUNE, 20)));
    }
}
