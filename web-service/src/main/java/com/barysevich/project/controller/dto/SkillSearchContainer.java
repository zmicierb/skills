//package com.barysevich.project.controller.dto;
//
///**
// * Created by BarysevichD on 2017-07-11.
// */
//public class SkillSearchContainer implements Comparable<SkillSearchContainer> {
//
//    private Double weight;
//
//    private Long personId;
//
//    public SkillSearchContainer() {
//        //default constructor
//    }
//
//    public SkillSearchContainer(Double weight, Long personId) {
//        this.weight = weight;
//        this.personId = personId;
//    }
//
//    public Double getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Double weight) {
//        this.weight = weight;
//    }
//
//    public Long getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(Long personId) {
//        this.personId = personId;
//    }
//
//    @Override
//    public int compareTo(SkillSearchContainer o) {
//        return this.getWeight().compareTo(o.getWeight()) != 0 ?
//                //descending order for weight and ascending order for personId
//                o.getWeight().compareTo(this.getWeight()) :
//                this.getPersonId().compareTo(o.getPersonId());
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        SkillSearchContainer that = (SkillSearchContainer) o;
//
//        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
//        return personId != null ? personId.equals(that.personId) : that.personId == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = weight != null ? weight.hashCode() : 0;
//        result = 31 * result + (personId != null ? personId.hashCode() : 0);
//        return result;
//    }
//}
