'use strict';

angular.module('skillsApp').component('personSkill', {
    templateUrl: 'person-skill/person-skill.template.html',
    controller: ['$routeParams', 'PersonSkillSrv',
        function PersonDetailController($routeParams, PersonSkillSrv) {
            var self = this;
            var personSkills = PersonSkillSrv.query({personId: $routeParams.personId}, function () {
                self.personSkills = personSkills.data;
            });
        }]
});