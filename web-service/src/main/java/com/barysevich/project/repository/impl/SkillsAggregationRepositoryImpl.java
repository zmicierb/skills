package com.barysevich.project.repository.impl;


import com.barysevich.project.model.Category;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.Skills;
import com.barysevich.project.model.result.PersonIdBySkillsSearchResult;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.repository.SkillsAggregationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(SkillsAggregationRepositoryImpl.class);

    private static final String INDEX = "index";

    private static final String WEIGHT = "weight";

    private static final String PERSON_ID = "personId";

    private final MongoOperations template;

    private final PersonRepository personRepository;


    @Autowired
    public SkillsAggregationRepositoryImpl(final MongoOperations template,
                                           final PersonRepository personRepository)
    {
        this.template = template;
        this.personRepository = personRepository;
    }


    @Override
    public List<Person> findPersonIdsBySkills(final List<String> skills, final long offset, final long limit)
    {
        logger.info("Searched skills={}", skills);

        final Criteria langsCriteria = Criteria.where(Category.LANGS.getName()).in(skills);
        final Criteria techsCriteria = Criteria.where(Category.TECHS.getName()).in(skills);
        final Criteria serversCriteria = Criteria.where(Category.SERVERS.getName()).in(skills);
        final Criteria dbsCriteria = Criteria.where(Category.DBS.getName()).in(skills);
        final Criteria systemsCriteria = Criteria.where(Category.SYSTEMS.getName()).in(skills);
        final Criteria othersCriteria = Criteria.where(Category.OTHERS.getName()).in(skills);

        final Criteria indexCriteria = Criteria.where(INDEX).gte(0).lt(20);

        final Criteria criterias = new Criteria()
                .orOperator(
                        langsCriteria,
                        techsCriteria,
                        serversCriteria,
                        dbsCriteria,
                        systemsCriteria,
                        othersCriteria);

        final Aggregation aggregation = newAggregation(
                match(criterias),

                facet()
                        .and(
                                unwind(Category.LANGS.getName(), INDEX),
                                match(langsCriteria),
                                match(indexCriteria),
                                group(PERSON_ID)
                                        .first(PERSON_ID)
                                        .as(PERSON_ID)
                                        .sum(ArithmeticOperators.Subtract
                                                .valueOf(20)
                                                .subtract(INDEX))
                                        .as(WEIGHT)
                        )
                        .as(Category.LANGS.getName())
                        .and(
                                unwind(Category.TECHS.getName(), INDEX),
                                match(techsCriteria),
                                match(indexCriteria),
                                group(PERSON_ID)
                                        .first(PERSON_ID)
                                        .as(PERSON_ID)
                                        .sum(ArithmeticOperators.Subtract
                                                .valueOf(20)
                                                .subtract(INDEX))
                                        .as(WEIGHT)
                        )
                        .as(Category.TECHS.getName())
                        .and(
                                unwind(Category.SERVERS.getName(), INDEX),
                                match(serversCriteria),
                                match(indexCriteria),
                                group(PERSON_ID)
                                        .first(PERSON_ID)
                                        .as(PERSON_ID)
                                        .sum(ArithmeticOperators.Subtract
                                                .valueOf(20)
                                                .subtract(INDEX))
                                        .as(WEIGHT)
                        )
                        .as(Category.SERVERS.getName())
                        .and(
                                unwind(Category.DBS.getName(), INDEX),
                                match(dbsCriteria),
                                match(indexCriteria),
                                group(PERSON_ID)
                                        .first(PERSON_ID)
                                        .as(PERSON_ID)
                                        .sum(ArithmeticOperators.Subtract
                                                .valueOf(20)
                                                .subtract(INDEX))
                                        .as(WEIGHT)
                        )
                        .as(Category.DBS.getName())
                        .and(
                                unwind(Category.SYSTEMS.getName(), INDEX),
                                match(systemsCriteria),
                                match(indexCriteria),
                                group(PERSON_ID)
                                        .first(PERSON_ID)
                                        .as(PERSON_ID)
                                        .sum(ArithmeticOperators.Subtract
                                                .valueOf(20)
                                                .subtract(INDEX))
                                        .as(WEIGHT)
                        )
                        .as(Category.SYSTEMS.getName())
                        .and(
                                unwind(Category.OTHERS.getName(), INDEX),
                                match(othersCriteria),
                                match(indexCriteria),
                                group(PERSON_ID)
                                        .first(PERSON_ID)
                                        .as(PERSON_ID)
                                        .sum(ArithmeticOperators.Subtract
                                                .valueOf(20)
                                                .subtract(INDEX))
                                        .as(WEIGHT)
                        )
                        .as(Category.OTHERS.getName()),

                project()
                        .and(
                                SetOperators.SetUnion.arrayAsSet(Category.LANGS.getName())
                                        .union(Category.TECHS.getName(),
                                                Category.SERVERS.getName(),
                                                Category.DBS.getName(),
                                                Category.SYSTEMS.getName(),
                                                Category.OTHERS.getName())
                        )
                        .as("skills"),
                unwind("skills"),
                group("skills.personId")
                        .first("skills.personId")
                        .as(PERSON_ID)
                        .sum("skills.weight")
                        .as(WEIGHT),
                sort(new Sort(Sort.Direction.DESC, WEIGHT)),
                skip(0L),
                limit(5L)
        );

        final AggregationResults<PersonIdBySkillsSearchResult> result = template.aggregate(
                aggregation, Skills.class, PersonIdBySkillsSearchResult.class);

        logger.info("Search result={}", result.getMappedResults());

        final List<String> personIds = result.getMappedResults().stream()
                .map(PersonIdBySkillsSearchResult::getPersonId)
                .collect(Collectors.toList());

        return personIds.stream()
                .map(personId -> personRepository.findById(personId).orElse(null))
                .collect(Collectors.toList());
    }
}
