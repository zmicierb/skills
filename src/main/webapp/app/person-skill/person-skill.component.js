'use strict';

angular.module('skillsApp').component('personSkill', {
    templateUrl: 'person-skill/person-skill.template.html',
    controller: ['$routeParams', 'PersonSkillSrv',
        function PersonDetailController($routeParams, PersonSkillSrv) {
            var self = this;
            var personSkills = PersonSkillSrv.query({personId: $routeParams.personId}, function () {
                self.personSkills = personSkills.data;
            });

            // self.personSkills = {
            //     "1": {
            //         "personId": 1,
            //         "rowId": 1,
            //         "rowName": "Programming Languages",
            //         "skills": [{"skillId": 1, "skillName": "Java 6", "weight": 1}, {
            //             "skillId": 2,
            //             "skillName": "Java 7",
            //             "weight": 2
            //         }]
            //     },
            //     "2": {
            //         "personId": 1,
            //         "rowId": 2,
            //         "rowName": "Web Technologies",
            //         "skills": [{"skillId": 7, "skillName": "Java EE 6", "weight": 1}, {
            //             "skillId": 8,
            //             "skillName": "EJB 3.0",
            //             "weight": 2
            //         }]
            //     },
            //     "3": {
            //         "personId": 1,
            //         "rowId": 3,
            //         "rowName": "Application Servers",
            //         "skills": [{"skillId": 16, "skillName": "Apache Tomcat", "weight": 1}, {
            //             "skillId": 17,
            //             "skillName": "IBM WebSphere Application Server 8.5",
            //             "weight": 2
            //         }]
            //     },
            //     "4": {
            //         "personId": 1,
            //         "rowId": 4,
            //         "rowName": "Databases",
            //         "skills": [{"skillId": 21, "skillName": "Oracle 11", "weight": 1}, {
            //             "skillId": 23,
            //             "skillName": "MySQL",
            //             "weight": 2
            //         }]
            //     },
            //     "5": {
            //         "personId": 1,
            //         "rowId": 5,
            //         "rowName": "Operating Systems",
            //         "skills": [{"skillId": 29, "skillName": "RHEL", "weight": 1}, {
            //             "skillId": 30,
            //             "skillName": "CentOS",
            //             "weight": 2
            //         }]
            //     },
            //     "6": {
            //         "personId": 1,
            //         "rowId": 6,
            //         "rowName": "Other Skills",
            //         "skills": [{"skillId": 42, "skillName": "JUnit", "weight": 1}, {
            //             "skillId": 45,
            //             "skillName": "JDBC",
            //             "weight": 2
            //         }]
            //     }
            // };

            self.models = {
                selected: null,
                lists: {}
            };

            // angular.forEach(self.personSkills, function (row, key) {
            //     self.models.lists[row.rowId] = [];
            //     row.skills.forEach(function (skill, i, arr) {
            //         self.models.lists[row.rowId].push(skill);
            //     })
            // });
        }]
});