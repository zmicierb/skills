package com.barysevich.project.search;

import com.barysevich.project.model.PersonSkill;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface PersonSkillElasticRepository extends ElasticsearchRepository<PersonSkill, Long> {
}
