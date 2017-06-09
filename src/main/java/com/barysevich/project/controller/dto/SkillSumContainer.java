package com.barysevich.project.controller.dto;

import com.barysevich.project.model.Person;
import com.barysevich.project.model.SkillSum;

/**
 * Created by BarysevichD on 2017-06-09.
 */
public class SkillSumContainer {

    private SkillSum skillSum;

    private ContainerAction action;

    public SkillSumContainer() {
    }

    public SkillSumContainer(Person person, SkillDto skillDto, ContainerAction action) {
        this.action = action;
        this.skillSum = new SkillSum(person, skillDto);
    }

    public SkillSumContainer(SkillSum skillSum, ContainerAction action) {
        this.skillSum = skillSum;
        this.action = action;
    }

    public SkillSum getSkillSum() {
        return skillSum;
    }

    public void setSkillSum(SkillSum skillSum) {
        this.skillSum = skillSum;
    }

    public ContainerAction getAction() {
        return action;
    }

    public void setAction(ContainerAction action) {
        this.action = action;
    }
}
