package com.barysevich.project.service.impl;

import com.barysevich.project.controller.dto.ContainerAction;
import com.barysevich.project.controller.dto.EnvironmentRowContainer;
import com.barysevich.project.model.EnvironmentSkill;
import com.barysevich.project.model.Project;
import com.barysevich.project.repository.EnvironmentSkillRepository;
import com.barysevich.project.repository.ProjectRepository;
import com.barysevich.project.service.PositionService;
import com.barysevich.project.service.ProjectService;
import com.barysevich.project.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PositionService positionService;

    @Autowired
    private EnvironmentSkillRepository environmentSkillRepository;

    @Autowired
    private SkillService skillService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository) {
        super(repository);
        this.projectRepository = repository;
    }

    @Transactional
    @Override
    public void remove(Long id) {
        projectRepository.remove(id);
    }

    @Override
    @Transactional
    public Project update(Long id, Project project) {

        Project update = projectRepository.findOne(id);
        update.setDescription(project.getDescription());
        update.setResult(project.getResult());
        update.setResponsibility(project.getResponsibility());
        update.setPosition(positionService.save(project.getPosition()));
        update.setCompanyInfo(project.getCompanyInfo());

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
                if (!vNew.getPosition().equals(vOld.getPosition())) {
                    vOld.setPosition(vNew.getPosition());
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
        project.setPosition(positionService.save(project.getPosition()));

        project.getEnvironmentSkills().forEach(i ->
                i.setSkill(skillService.save(i.getSkill()))
        );

        return projectRepository.save(project);
    }

    @Override
    public Iterable<Project> findByDescriptionContainingIgnoreCaseForTest(String description) {
        return projectRepository.findByDescriptionContainingIgnoreCaseForTest(description);
    }

    private EnvironmentSkill insertEnvSkill(EnvironmentSkill envSkill) {
        envSkill.setSkill(skillService.save(envSkill.getSkill()));
        return envSkill;
    }

    @Override
    public Iterable<Project> findByCompanyId(Long id) {
        return projectRepository.findByCompanyId(id);
    }
}
