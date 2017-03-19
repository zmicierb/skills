package by.barysevich.project.repository;

import by.barysevich.project.model.ProjectSum;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface ProjectSumRepository extends PagingAndSortingRepository<ProjectSum, Long> {

}
