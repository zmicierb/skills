package by.barysevich.project.repository;

import by.barysevich.project.model.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by BarysevichD on 2017-03-15.
 */
//@RepositoryRestResource(collectionResourceRel = "skill", path = "skills")
public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {

    List<Skill> findByName(String name);

}
