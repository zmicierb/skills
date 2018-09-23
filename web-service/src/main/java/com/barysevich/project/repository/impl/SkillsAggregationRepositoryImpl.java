package com.barysevich.project.repository.impl;


import com.barysevich.project.model.result.PersonIdResult;
import com.barysevich.project.repository.SkillsAggregationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@Repository
public class SkillsAggregationRepositoryImpl implements SkillsAggregationRepository
{
    private final MongoOperations template;


    @Autowired
    public SkillsAggregationRepositoryImpl(final MongoOperations template)
    {
        this.template = template;
    }


//    @Override
//    public List<String> findPersonIdsBySkill(final List<String> skills, final long offset, final long limit) {
//        MatchOperation matchBySkills = match(new Criteria("langs").in(skills));
//
//        SortOperation sortByPersonIdDesc = sort(new Sort(Sort.Direction.DESC, "personId"));
//        ProjectionOperation projectToPersonId = project()
//                .andExpression("personId").as("personId");
//
//        Aggregation aggregation = newAggregation(matchBySkills, sortByPersonIdDesc, projectToPersonId);
//        AggregationResults<PersonIdResult> result = template.aggregate(
//                aggregation, "skills", PersonIdResult.class);
//
//        return result.getMappedResults().stream()
//                .map(PersonIdResult::getPersonId)
//                .collect(Collectors.toList());
//    }


    @Override
    public List<String> findPersonIdsBySkill(final List<String> skills, final long offset, final long limit)
    {
        TypedAggregation<PersonIdResult> aggregation = newAggregation(PersonIdResult.class,
                match(new Criteria("langs").in(skills)),
                project()
                        .and("personId").as("personId"),
//                        .and().as("result"),
                sort(new Sort(Sort.Direction.DESC, "personId")),
                skip(0L),
                limit(5L)
        );

        AggregationResults<PersonIdResult> result = template.aggregate(
                aggregation, "skills", PersonIdResult.class);

        return result.getMappedResults().stream()
            .map(PersonIdResult::getPersonId)
            .collect(Collectors.toList());
    }
}
