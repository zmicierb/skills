package com.barysevich.project.repository.impl;


import com.barysevich.project.model.Category;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.Skills;
import com.barysevich.project.model.result.PagedResult;
import com.barysevich.project.model.result.PersonIdBySkillsSearchResult;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.repository.SkillsAggregationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public Page<Person> findPersonIdsBySkills(final List<String> skills, final int page, final int size)
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
                        .and(ArrayOperators.ConcatArrays.arrayOf(Category.LANGS.getName())
                                .concat(Category.TECHS.getName())
                                .concat(Category.SERVERS.getName())
                                .concat(Category.DBS.getName())
                                .concat(Category.SYSTEMS.getName())
                                .concat(Category.OTHERS.getName()))
                        .as("skills"),
                unwind("skills"),
                group("skills.personId")
                        .first("skills.personId")
                        .as(PERSON_ID)
                        .sum("skills.weight")
                        .as(WEIGHT),
                facet()
                        .and(
                                sort(new Sort(Sort.Direction.DESC, WEIGHT)),
                                skip(page * size),
                                limit(size)
                        )
                        .as("pagedResult")
                        .and(
                                group().count().as("total")
                        )
                        .as("total")
        );

        final PagedResult result = template.aggregate(
                aggregation, Skills.class, PagedResult.class).getUniqueMappedResult();

        logger.info("Search result={}", result);

        if (result.getPagedResult().isEmpty())
        {
            return new PageImpl<>(new ArrayList<>(), PageRequest.of(page, size), 0);
        }

        final List<Long> personIds = result.getPagedResult().stream()
                .map(PersonIdBySkillsSearchResult::getPersonId)
                .collect(Collectors.toList());

        final List<Person> persons = personIds.stream()
                .map(personId -> personRepository.findByPersonId(personId).orElse(null))
                .collect(Collectors.toList());

        return new PageImpl<>(persons, PageRequest.of(page, size), result.getTotal().get(0).getTotal());
    }
}
