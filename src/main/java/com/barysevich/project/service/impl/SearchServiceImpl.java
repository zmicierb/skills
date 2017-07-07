package com.barysevich.project.service.impl;

import com.barysevich.project.model.Person;
import com.barysevich.project.model.SkillSum;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.repository.SkillSumRepository;
import com.barysevich.project.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by BarysevichD on 2017-07-06.
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SkillSumRepository skillSumRepository;

    @Override
    public Page<Person> extendedSearch(String query, Pageable pageable) {

        List<SkillSum> skillSums = (List) skillSumRepository.findBySkillNameContainingIgnoreCase(query);

        List<Person> persons = skillSums
                .stream()
                .map(SkillSum::getPersonId)
                .distinct()
                .map(i -> personRepository.findOne(i))
                .collect(Collectors.toList());

        Page<Person> personPage = new PageImpl<>(persons, pageable, persons.size());

        return personPage;
    }
}
