package by.barysevich.project.repository;

import by.barysevich.project.model.CompanyInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by BarysevichD on 2017-03-15.
 */
//@RepositoryRestResource(collectionResourceRel = "company", path = "companies")
public interface CompanyInfoRepository extends PagingAndSortingRepository<CompanyInfo, Long> {

    List<CompanyInfo> findByName(String name);

}
