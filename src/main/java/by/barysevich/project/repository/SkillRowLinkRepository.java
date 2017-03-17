package by.barysevich.project.repository;

import by.barysevich.project.model.SkillRowLink;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by BarysevichD on 2017-03-15.
 */
//@RepositoryRestResource(collectionResourceRel = "skill/row", path = "skills/row")
public interface SkillRowLinkRepository extends PagingAndSortingRepository<SkillRowLink, Long> {

}
