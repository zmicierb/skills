package by.barysevich.project.repository;

import by.barysevich.project.model.Position;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by BarysevichD on 2017-03-15.
 */
//@RepositoryRestResource(collectionResourceRel = "position", path = "positions")
public interface PositionRepository extends PagingAndSortingRepository<Position, Long> {

    List<Position> findByName(String name);

}
