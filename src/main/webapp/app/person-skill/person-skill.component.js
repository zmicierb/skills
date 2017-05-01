'use strict';

angular.module('skillsApp').component('personSkill', {
    templateUrl: 'person-skill/person-skill.template.html',
    controller: ['$routeParams', 'PersonSkillSrv',
        function PersonDetailController($routeParams, PersonSkillSrv) {
            var self = this;
            var indexedRows = [];
            var personSkills = PersonSkillSrv.query({personId: $routeParams.personId}, function () {
                indexedRows = []
                self.personSkills = personSkills.data;
            });

            self.filterRows = function (personSkill) {
                var rowIsNew = indexedRows.indexOf(personSkill.row.name) == -1;
                if (rowIsNew) {
                    indexedRows.push(personSkill.row.name);
                }
                return rowIsNew;
            }
        }]
});