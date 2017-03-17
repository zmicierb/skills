package by.barysevich.project.repository;

import by.barysevich.project.model.Row;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by BarysevichD on 2017-03-15.
 */
//@RepositoryRestResource(collectionResourceRel = "row", path = "rows")
public interface RowRepository extends PagingAndSortingRepository<Row, Long> {

    List<Row> findByName(String name);

}
