//package com.barysevich.project.service.impl;
//
//
//import com.barysevich.project.controller.dto.SkillSearchContainer;
//import com.barysevich.project.model.Person;
//import com.barysevich.project.model.SkillSum;
//import com.barysevich.project.repository.PersonRepository;
//import com.barysevich.project.repository.SkillSumRepository;
//import com.barysevich.project.service.SearchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.TreeSet;
//import java.util.stream.Collectors;
//
//
///**
// * Created by BarysevichD on 2017-07-06.
// */
//@Service
//public class SearchServiceImpl implements SearchService
//{
//
//    @Autowired
//    private PersonRepository personRepository;
//
//    @Autowired
//    private SkillSumRepository skillSumRepository;
//
//
//    @Override
//    public Page<Person> extendedSearch(String query, Pageable pageable)
//    {
//
//        List<SkillSum> skillSums = (List) skillSumRepository.findBySkillNameContainingIgnoreCase(query);
//
//        TreeSet<SkillSearchContainer> searchContainers = skillSums
//            .stream()
//            .collect(Collectors.groupingBy(
//                SkillSum::getPersonId,
//                Collectors.summingDouble(SkillSum::getWeight)
//            ))
//            .entrySet()
//            .stream()
//            .map(entry -> new SkillSearchContainer(entry.getValue(), entry.getKey()))
//            .collect(Collectors.toCollection(TreeSet::new));
//
//        int start = pageable.getOffset() > searchContainers.size() ? 0 : pageable.getOffset();
//
//        List<Person> persons = new ArrayList<>();
//        persons.addAll(searchContainers
//            .stream()
//            .skip(start)
//            .limit(pageable.getPageSize())
//            .map(skillSum -> personRepository.findOne(skillSum.getPersonId()))
//            .collect(Collectors.toList()));
//
//        return new PageImpl<>(persons, new PageRequest((start / pageable.getPageSize()), pageable.getPageSize()), searchContainers.size());
//    }
//}
