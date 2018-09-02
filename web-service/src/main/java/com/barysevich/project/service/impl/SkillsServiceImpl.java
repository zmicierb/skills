package com.barysevich.project.service.impl;

import com.barysevich.project.model.Skills;
import com.barysevich.project.repository.SkillsRepository;
import com.barysevich.project.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillsServiceImpl extends GenericServiceImpl<Skills, String> implements SkillsService
{

    private SkillsRepository skillsRepository;

    @Autowired
    public SkillsServiceImpl(final SkillsRepository repository)
    {
        super(repository);
        this.skillsRepository = repository;
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
