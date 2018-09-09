package com.barysevich.project.service.impl;

import com.barysevich.project.model.Skill;
import com.barysevich.project.repository.SkillRepository;
import com.barysevich.project.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl extends GenericServiceImpl<Skill, String> implements SkillService
{

    final private SkillRepository skillRepository;


    @Autowired
    public SkillServiceImpl(final SkillRepository repository)
    {
        super(repository);
        this.skillRepository = repository;
    }


    @Override
    public Iterable<Skill> findByNameRegEx(final String name, final Pageable pageable)
    {
        return skillRepository.findByNameRegEx(name, pageable);
    }

    //    @Override
//    public Iterable<Skill> findByNameContainingIgnoreCase(String name, Pageable pageable)
//    {
//        return skillRepository.findByNameContainingIgnoreCase(name, pageable);
//    }
//
//
//    @Override
//    @Transactional
//    public Skill save(Skill skillNew)
//    {
//        //additional checks due to editable typeahead
//        if (skillNew.isNew())
//        {
//            Skill skillOld = skillRepository.findByName(skillNew.getName());
//            if (skillOld == null)
//                return skillRepository.save(new Skill(skillNew.getName()));
//            else
//                return skillOld;
//        } else
//        {
//            return skillRepository.findOne(skillNew.getId());
//        }
//    }
}
