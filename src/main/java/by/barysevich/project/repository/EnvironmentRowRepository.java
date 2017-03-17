package by.barysevich.project.repository;

import by.barysevich.project.model.EnvironmentRow;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by BarysevichD on 2017-03-15.
 */
//@RepositoryRestResource(collectionResourceRel = "environment", path = "environments")
public interface EnvironmentRowRepository extends PagingAndSortingRepository<EnvironmentRow, Long> {
}
