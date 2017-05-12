'use strict';

angular.module('skillsApp').component('personSkill', {
    templateUrl: 'person-skill/person-skill.template.html',
    controller: ['$routeParams', 'PersonSkillSrv',
        function PersonDetailController($routeParams, PersonSkillSrv) {
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

            self.toggleEditFlag = function () {
                self.editFlag = true;
            }
        }]
});