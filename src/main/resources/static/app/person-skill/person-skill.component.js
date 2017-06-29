'use strict';

angular.module('skillsApp').component('personSkill', {
    templateUrl: 'person-skill/person-skill.template.html',
    controller: ['$routeParams', 'PersonSkillSrv', 'SkillSrv', 'SkillFindSrv', 'RowSrv',
        function PersonDetailController($routeParams, PersonSkillSrv, SkillSrv, SkillFindSrv, RowSrv) {
            var self = this;
            var queryPersonSkills = function () {
                var personSkills = PersonSkillSrv.query({personId: $routeParams.personId}, function () {
                    self.models.personId = $routeParams.personId;
                    //fill up models for drag-and-drop list
                    angular.forEach(personSkills.data, function (row) {
                        self.models.lists[row.rowName] = {};
                        self.models.lists[row.rowName].newSkill = "";
                        self.models.lists[row.rowName].rowId = row.rowId;
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

                    var rows = RowSrv.get({}, function () {
                        self.rows = [];
                        rows.data.forEach(function (item) {
                            var presentFlag = false;
                            angular.forEach(self.models.lists, function (row) {
                                if (item.name == row.type) {
                                    presentFlag = true
                                }
                            });
                            if (!presentFlag)
                                self.rows.push(item);
                        });
                    });

                });
            };

            var checkSkillInArray = function (skillName, rowName) {
                var check = false;
                check = self.models.lists[rowName].skills.some(function (item) {
                    return item.skillName == skillName;
                });
                return check;
            };

            var skillForUser = function (arr, rowName) {
                var output = [];
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
                if (skill.id == undefined) {
                    if (!checkSkillInArray(skill, rowName)) {
                        form.$setDirty();
                        self.models.lists[rowName].skills.push({skillName: skill});
                    }
                } else {
                    if (!checkSkillInArray(skill.name, rowName)) {
                        form.$setDirty();
                        self.models.lists[rowName].skills.push({skillId: skill.id, skillName: skill.name});
                    }
                }
                self.models.lists[rowName].addSkillFlag = false;
                self.models.lists[rowName].newSkill = "";
            };

            self.removeSkill = function (name, rowName, form) {
                self.models.lists[rowName].skills.some(function (skill, i, arr) {
                    if (skill.skillName == name) {
                        form.$setDirty();
                        arr.splice(i, 1);
                        return true;
                    }
                });
            };

            self.submit = function (form) {
                var updateArr = [];
                angular.forEach(self.models.lists, function (row) {
                    row.skills.forEach(function (skill, i) {
                        updateArr.push({
                            personId: self.models.personId,
                            skill: {
                                id: skill.skillId,
                                name: skill.skillName
                            },
                            row: {
                                id: row.rowId,
                                name: row.type
                            },
                            weight: i + 1
                        })
                    })
                });
                PersonSkillSrv.update({personId: self.models.personId}, updateArr).$promise.then(function () {
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

            self.addRow = function (row) {
                self.models.lists[row.name] = {};
                self.models.lists[row.name].newSkill = "";
                self.models.lists[row.name].addSkillFlag = false;
                self.models.lists[row.name].skills = [];
                self.models.lists[row.name].type = row.name;
                self.models.lists[row.name].rowId = row.id;
            };
        }
    ]
});