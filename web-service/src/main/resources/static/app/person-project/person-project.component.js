'use strict';

angular.module('skillsApp').component('personProject', {
    templateUrl: 'person-project/person-project.template.html',
    controller: ['$routeParams', '$filter', 'PersonCompanySrv', 'SkillSrv', 'SkillFindSrv',
        function PersonDetailController($routeParams, $filter, PersonCompanySrv, SkillSrv, SkillFindSrv) {
            let self = this;
            const queryPersonProjects = function () {
                const personCompanies = PersonCompanySrv.query({personId: $routeParams.personId}, function () {
                    personCompanies.data.forEach(function (company) {
                        //convert for datepicker
                        company.startDt = Date.parse(company.startDate);
                        company.endDt = Date.parse(company.endDate);
                        company.startOpened = false;
                        company.endOpened = false;
                    });
                    self.personCompanies = personCompanies.data;
                });
            };

            self.dateOptions = {
                formatYear: 'yyyy',
                startingDay: 1
            };

            self.open = function (companyId, type) {
                self.personCompanies.some(function (item) {
                    if (item.id === companyId) {
                        if (type === "start")
                            item.startOpened = true;
                        if (type === "end")
                            item.endOpened = true;
                        return true;
                    }
                })
            };

            const checkSkillInArray = function (skillName, companyKey, projectKey) {
                let check;
                const project = self.personCompanies[companyKey].projects[projectKey];
                check = project.environment.some(function (item) {
                    return item === skillName;
                });
                return check;
            };

            const skillForProject = function (arr, companyKey, projectKey) {
                let output = [];
                arr.forEach(function (skill) {
                    if (!checkSkillInArray(skill.name, companyKey, projectKey))
                        output.push(skill);
                });
                return output;
            };

            queryPersonProjects();

            self.toggleEditCompany = function (companyKey) {
                self.personCompanies[companyKey].editCompanyFlag = !self.personCompanies[companyKey].editCompanyFlag;
                self.edit = self.personCompanies.some(function (company) {
                    if (company.editCompanyFlag) {
                        return true;
                    }
                });
            };

            self.getSkill = function (val, companyKey, projectKey) {
                if (val) {
                    return SkillFindSrv.get({query: val, page: 0, size: 10}).$promise.then(function (response) {
                        return skillForProject(response.data, companyKey, projectKey)
                    });
                } else {
                    return SkillSrv.get({page: 0, size: 10}).$promise.then(function (response) {
                        return skillForProject(response.data, companyKey, projectKey)
                    });
                }
            };

            self.submit = function () {
                self.personCompanies.forEach(function (company) {
                    //     company.personId = $routeParams.personId;
                    company.startDate = $filter('date')(company.startDt, "yyyy/MM/dd");
                    company.endDate = $filter('date')(company.endDt, "yyyy/MM/dd");
                });

                PersonCompanySrv.save(self.personCompanies).$promise.then(function () {
                    queryPersonProjects();
                    self.edit = false;
                });
            };

            self.resetCompany = function () {
                queryPersonProjects();
                self.edit = false;
            };

            self.toggleNewSkill = function (companyKey, projectKey) {
                self.personCompanies[companyKey].projects[projectKey].addSkillFlag = !self.personCompanies[companyKey].projects[projectKey].addSkillFlag;
                self.personCompanies[companyKey].projects[projectKey].newSkill = "";

            };

            self.removeSkill = function (skill, companyKey, projectKey, form) {
                const project = self.personCompanies[companyKey].projects[projectKey];
                project.environment.some(function (skillName, i, arr) {
                    if (skillName === skill) {
                        form.$setDirty();
                        arr.splice(i, 1);
                        return true;
                    }
                });
            };

            self.addNewSkill = function (skill, companyKey, projectKey, form) {
                if (skill.id === undefined) {
                    if (!checkSkillInArray(skill, companyKey, projectKey)) {
                        form.$setDirty();
                        self.personCompanies[companyKey].projects[projectKey].environment.push(skill);
                    }
                } else {
                    if (!checkSkillInArray(skill.name, companyKey, projectKey)) {
                        form.$setDirty();
                        self.personCompanies[companyKey].projects[projectKey].environment.push(skill.name);
                    }
                }
                self.personCompanies[companyKey].projects[projectKey].addSkillFlag = false;
                self.personCompanies[companyKey].projects[projectKey].newSkill = "";
            };

            self.deleteProject = function (companyKey, projectKey, form) {
                self.personCompanies[companyKey].projects.splice(projectKey, 1);
                form.$setDirty();
            };

            self.deleteCompany = function (companyKey) {
                if (self.personCompanies[companyKey].id) {
                    PersonCompanySrv.delete({personId: self.personCompanies[companyKey].id}).$promise.then(function () {
                        queryPersonProjects();
                    });
                } else {
                    self.personCompanies.splice(companyKey, 1);
                }
            };

            self.addCompany = function () {
                self.edit = true;
                self.personCompanies.push({
                    personId: $routeParams.personId,
                    editCompanyFlag: true
                })
            };

            self.addProject = function (companyKey) {
                self.edit = true;
                self.personCompanies[companyKey].editCompanyFlag = true;
                if (self.personCompanies[companyKey].projects) {
                    self.personCompanies[companyKey].projects.push({
                        environment: []
                    })
                } else {
                    self.personCompanies[companyKey].projects = [{
                        environment: []
                    }]
                }
            };
        }]
});