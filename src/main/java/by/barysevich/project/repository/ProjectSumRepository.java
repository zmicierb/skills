package by.barysevich.project.repository;

import by.barysevich.project.model.ProjectSum;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by BarysevichD on 2017-03-15.
 */
//@RepositoryRestResource(collectionResourceRel = "project/info", path = "projects/info")
public interface ProjectSumRepository extends PagingAndSortingRepository<ProjectSum, Long> {

}
