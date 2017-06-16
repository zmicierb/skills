package com.barysevich.project.service.impl;

import com.barysevich.project.controller.dto.ContainerAction;
import com.barysevich.project.controller.dto.EnvironmentRowContainer;
import com.barysevich.project.model.EnvironmentSkill;
import com.barysevich.project.model.Position;
import com.barysevich.project.model.Project;
import com.barysevich.project.model.Skill;
import com.barysevich.project.repository.EnvironmentSkillRepository;
import com.barysevich.project.repository.PositionRepository;
import com.barysevich.project.repository.ProjectRepository;
import com.barysevich.project.repository.SkillRepository;
import com.barysevich.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class ProjectServiceImpl extends GenericServiceImpl<Project, Long> implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private EnvironmentSkillRepository environmentSkillRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository) {
        super(repository);
        this.projectRepository = repository;
    }

    @Override
    public void remove(Long id) {
        projectRepository.remove(id);
    }

    @Override
    public Page<Project> findByResponsibilityContainingIgnoreCase(String name, Pageable pageable) {
        return projectRepository.findByResponsibilityContainingIgnoreCase(name, pageable);
    }

    @Override
    @Transactional
    public Project update(Long id, Project project) {

        Project update = projectRepository.findOne(id);
        update.setDescription(project.getDescription());
        update.setResult(project.getResult());
        update.setResponsibility(project.getResponsibility());
        //additional checks due to editable typehead
        if (project.getPosition().isNew()) {
            Position position = positionRepository.findByName(project.getPosition().getName());
            if (position == null)
                update.setPosition(positionRepository.save(new Position(project.getPosition().getName())));
            else
                update.setPosition(position);
        } else
            update.setPosition(project.getPosition());

        HashMap<String, EnvironmentSkill> envSkillMapOld = new HashMap<>();
        HashMap<String, EnvironmentSkill> envSkillMapNew = new HashMap<>();

        update.getEnvironmentSkills().forEach(a ->
                envSkillMapOld.put(a.getSkill().getName(), a)
        );

        project.getEnvironmentSkills().forEach(a ->
                envSkillMapNew.put(a.getSkill().getName(), a)
        );

        ArrayList<EnvironmentRowContainer> envSkillContainers = new ArrayList<>();

        //compare old and new maps and fill in skillContainers
        envSkillMapNew.forEach((kNew, vNew) -> {
            envSkillMapOld.computeIfPresent(kNew, (kOld, vOld) -> {
                if (!vNew.getWeight().equals(vOld.getWeight())) {
                    vOld.setWeight(vNew.getWeight());
                    envSkillContainers.add(new EnvironmentRowContainer(vOld, ContainerAction.UPDATE));
                }
                return envSkillMapOld.get(kOld);
            });
            envSkillMapOld.computeIfAbsent(kNew, kOld -> {
                envSkillContainers.add(new EnvironmentRowContainer(vNew, ContainerAction.INSERT));
                return null;
            });
        });

        envSkillMapOld.forEach((kOld, vOld) ->
                envSkillMapNew.computeIfAbsent(kOld, kNew -> {
                    envSkillContainers.add(new EnvironmentRowContainer(vOld, ContainerAction.DELETE));
                    return null;
                })
        );

        //process envSkillContainers
        envSkillContainers.forEach(envSkillContainer -> {
            switch (envSkillContainer.getAction()) {
                case DELETE:
                    update.getEnvironmentSkills().removeIf(i -> i.equals(envSkillContainer.getEnvironmentSkill()));
                    environmentSkillRepository.delete(envSkillContainer.getEnvironmentSkill());
                    break;
                case INSERT:
                    update.getEnvironmentSkills().add(insertEnvSkill(envSkillContainer.getEnvironmentSkill()));
                    break;
                case UPDATE:
                    environmentSkillRepository.save(envSkillContainer.getEnvironmentSkill());
                    break;
                default:
            }
        });

        return projectRepository.save(update);
    }

    @Override
    public Iterable<Project> findByPersonId(Long personId) {
        return projectRepository.findByPersonId(personId);
    }

    @Override
    @Transactional
    public Project save(Project project) {

        Project newProject = new Project();
        newProject.setPersonId(project.getPersonId());
        newProject.setDescription(project.getDescription());
        newProject.setResult(project.getResult());
        newProject.setResponsibility(project.getResponsibility());
        newProject.setCompanyInfo(project.getCompanyInfo());
        //additional checks due to editable typehead
        if (project.getPosition().isNew()) {
            Position position = positionRepository.findByName(project.getPosition().getName());
            if (position == null)
                newProject.setPosition(positionRepository.save(new Position(project.getPosition().getName())));
            else
                newProject.setPosition(position);
        } else
            newProject.setPosition(project.getPosition());

        project.getEnvironmentSkills().forEach(i -> {
            //additional checks due to editable typeahead
            if (i.getSkill().isNew()) {
                Skill skill = skillRepository.findByName(i.getSkill().getName());
                if (skill == null)
                    i.setSkill(skillRepository.save(new Skill(i.getSkill().getName())));
                else
                    i.setSkill(skill);
            }
        });

        newProject.setEnvironmentSkills(project.getEnvironmentSkills());

        return projectRepository.save(newProject);
    }

    @Override
    public Iterable<Project> findByDescriptionContainingIgnoreCaseForTest(String description) {
        return projectRepository.findByDescriptionContainingIgnoreCaseForTest(description);
    }

    private EnvironmentSkill insertEnvSkill(EnvironmentSkill envSkill) {
        //additional checks due to editable typeahead
        if (envSkill.getSkill().isNew()) {
            Skill skill = skillRepository.findByName(envSkill.getSkill().getName());
            if (skill == null)
                envSkill.setSkill(skillRepository.save(new Skill(envSkill.getSkill().getName())));
            else
                envSkill.setSkill(skill);
        } else {
            envSkill.setSkill(skillRepository.findOne(envSkill.getSkill().getId()));
        }
        return envSkill;
    }

    @Override
    public Iterable<Project> findByCompanyId(Long id) {
        return projectRepository.findByCompanyId(id);
    }
}
