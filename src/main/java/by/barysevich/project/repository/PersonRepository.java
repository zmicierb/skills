package by.barysevich.project.repository;

import by.barysevich.project.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by BarysevichD on 2017-03-15.
 */
//@RepositoryRestResource(collectionResourceRel = "person", path = "persons")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    List<Person> findByName(String name);

}
