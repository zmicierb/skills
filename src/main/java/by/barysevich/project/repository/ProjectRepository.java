package by.barysevich.project.repository;

import by.barysevich.project.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by BarysevichD on 2017-03-15.
 */
//@RepositoryRestResource(collectionResourceRel = "project", path = "projects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

}
