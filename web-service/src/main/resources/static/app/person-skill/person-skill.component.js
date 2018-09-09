'use strict';

angular.module('skillsApp').component('personSkill', {
    templateUrl: 'person-skill/person-skill.template.html',
    controller: ['$routeParams', 'PersonSkillSrv', 'SkillSrv', 'SkillFindSrv',
        function PersonDetailController($routeParams, PersonSkillSrv, SkillSrv, SkillFindSrv) {
            let self = this;
            const queryPersonSkills = function () {
                const personSkills = PersonSkillSrv.query({personId: $routeParams.personId}, function () {
                    self.personSkills = personSkills.data;
                    //fill up models for drag-and-drop list
                    angular.forEach([
                        {id: 'langs', name: 'Programming Languages'},
                        {id: 'techs', name: 'Web Technologies'},
                        {id: 'servers', name: 'Application Servers'},
                        {id: 'dbs', name: 'Databases'},
                        {id: 'systems', name: 'Operation Systems'},
                        {id: 'others', name: 'Other Skills'}
                    ], function (row) {
                        self.models.lists[row.id] = {};
                        self.models.lists[row.id].newSkill = "";
                        self.models.lists[row.id].rowId = row.id;
                        self.models.lists[row.id].name = row.name;
                        self.models.lists[row.id].addSkillFlag = false;
                        self.models.lists[row.id].skills = (self.personSkills && self.personSkills[row.id]) || [];
                        self.models.lists[row.id].type = row.id;
                    });
                });
            };

            const checkSkillInArray = function (skillName, rowName) {
                let check;
                check = self.models.lists[rowName].skills.some(function (item) {
                    return item === skillName;
                });
                return check;
            };

            const skillForUser = function (arr, rowName) {
                let output = [];
                arr.forEach(function (skill) {
                    if (!checkSkillInArray(skill.name, rowName))
                        output.push(skill);
                });
                return output;
            };

            self.models = {
                lists: {}
            };
            self.editFlag = false;

            queryPersonSkills();

            self.toggleEditFlag = function () {
                self.editFlag = true;
            };

            self.toggleNewSkill = function (rowName) {
                self.models.lists[rowName].addSkillFlag = !self.models.lists[rowName].addSkillFlag;
                self.models.lists[rowName].newSkill = "";
            };

            self.getSkill = function (val, rowName) {
                if (val) {
                    return SkillFindSrv.get({query: val, page: 0, size: 10}).$promise.then(function (response) {
                        return skillForUser(response.data, rowName)
                    });
                } else {
                    return SkillSrv.get({page: 0, size: 10}).$promise.then(function (response) {
                        return skillForUser(response.data, rowName)
                    });
                }
            };

            self.addNewSkill = function (skill, rowName, form) {
                if (skill.id === undefined) {
                    if (!checkSkillInArray(skill, rowName)) {
                        form.$setDirty();
                        self.models.lists[rowName].skills.push(skill);
                    }
                } else {
                    if (!checkSkillInArray(skill.name, rowName)) {
                        form.$setDirty();
                        self.models.lists[rowName].skills.push(skill.name);
                    }
                }
                self.models.lists[rowName].addSkillFlag = false;
                self.models.lists[rowName].newSkill = "";
            };

            self.removeSkill = function (name, rowName, form) {
                self.models.lists[rowName].skills.some(function (skill, i, arr) {
                    if (skill === name) {
                        form.$setDirty();
                        arr.splice(i, 1);
                        return true;
                    }
                });
            };

            self.submit = function (form) {
                let person = {
                    id: self.personSkills && self.personSkills.id,
                    personId: $routeParams.personId,
                };

                angular.forEach([
                    {id: 'langs', name: 'Programming Languages'},
                    {id: 'techs', name: 'Web Technologies'},
                    {id: 'servers', name: 'Application Servers'},
                    {id: 'dbs', name: 'Databases'},
                    {id: 'systems', name: 'Operation Systems'},
                    {id: 'others', name: 'Other Skills'}
                ], function (row) {
                    person[row.id] = self.models.lists[row.id].skills;
                });

                PersonSkillSrv.save(person).$promise.then(function () {
                    form.$setPristine();
                    queryPersonSkills();
                });
            };

            self.cancel = function () {
                self.editFlag = false;
            };

            self.reset = function (form) {
                queryPersonSkills();
                form.$setPristine();
            };
        }
    ]
});