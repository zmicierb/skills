'use strict';

angular.module('skillsApp').component('personSkill', {
    templateUrl: 'person-skill/person-skill.template.html',
    controller: ['$routeParams', 'PersonSkillSrv', 'SkillSrv', 'SkillFindSrv',
        function PersonDetailController($routeParams, PersonSkillSrv, SkillSrv, SkillFindSrv) {
            var self = this;
            self.models = {
                lists: {},
            };
            self.editFlag = false;
            var personSkills = PersonSkillSrv.query({personId: $routeParams.personId}, function () {
                    self.models.personId = $routeParams.personId;
                    //fill up models for drag-and-drop list
                    angular.forEach(personSkills.data, function (row) {
                        self.models.lists[row.rowName] = {};
                        self.models.lists[row.rowName].newSkill = "";
                        self.models.lists[row.rowName].addSkillFlag = false;
                        self.models.lists[row.rowName].skills = [];
                        self.models.lists[row.rowName].type = row.rowName;
                        row.skills.forEach(function (skill) {
                            self.models.lists[row.rowName].skills.push(skill);
                        })
                    });
                    //sort skills in lists by weight
                    angular.forEach(personSkills.data, function (row) {
                        self.models.lists[row.rowName].skills.sort(function (a, b) {
                            return a.weight - b.weight;
                        });
                    });
                }
            );

            var checkSkillsArray = function (arr, val) {
                var check = false;
                check = arr.some(function (skill) {
                    return skill.skillName == val
                });
                return check;
            };

            self.toggleEditFlag = function () {
                self.editFlag = true;
            };

            self.toggleNewSkill = function (rowName) {
                self.models.lists[rowName].addSkillFlag = true;
            };

            self.getSkill = function (val, rowName) {
                if (val) {
                    return SkillFindSrv.get({query: val, page: 0, size: 10}).$promise.then(function (response) {
                        return response.data
                    });
                } else {
                    return SkillSrv.get({page: 0, size: 10}).$promise.then(function (response) {
                        return response.data
                    });
                }
            };

            self.addNewSkill = function (skill, rowName) {
                if (skill.id == undefined) {
                    self.models.lists[rowName].skills.push({skillName: skill, new: true});
                } else {
                    self.models.lists[rowName].skills.push({skillId: skill.id, skillName: skill.name});
                }
                self.models.lists[rowName].addSkillFlag = false;
                self.models.lists[rowName].newSkill = "";
            };
            }
    ]
});