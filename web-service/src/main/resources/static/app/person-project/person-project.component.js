'use strict';

angular.module('skillsApp').component('personProject', {
    templateUrl: 'person-project/person-project.template.html',
    controller: ['$routeParams', '$filter', 'PersonProjectSrv', 'SkillSrv', 'SkillFindSrv',
        function PersonDetailController($routeParams, $filter, PersonProjectSrv, SkillSrv, SkillFindSrv) {
            let self = this;
            const queryPersonProjects = function () {
                const personCompanies = PersonProjectSrv.query({personId: $routeParams.personId}, function () {
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

            // var queryPersonProject = function (projectId) {
            //     var personProject = ProjectSrv.get({projectId: projectId}, function () {
            //         //sort skills in environment rows by position
            //         personProject.data.environmentSkills.sort(function (a, b) {
            //             return a.position - b.position;
            //         });
            //         self.personProjects.some(function (item) {
            //             if (item.id == projectId || item.id == null) {
            //                 item.id = personProject.data.id;
            //                 item.environmentSkills = personProject.data.environmentSkills;
            //                 item.position = personProject.data.position;
            //                 item.responsibility = personProject.data.responsibility;
            //                 item.result = personProject.data.result;
            //                 item.description = personProject.data.description;
            //                 item.editProjectFlag = true;
            //                 return true;
            //             }
            //         })
            //     })
            // };

            // var queryPersonCompany = function (companyId) {
            //     var personCompany = CompanySrv.get({companyId: companyId}, function () {
            //         self.personProjects.forEach(function (item) {
            //             if (item.companyInfo.id == companyId || item.id == null) {
            //                 item.companyInfo = personCompany.data;
            //                 item.companyInfo.editCompanyFlag = true;
            //
            //                 //convert for datepicker
            //                 item.companyInfo.startDt = Date.parse(item.companyInfo.startDate);
            //                 item.companyInfo.endDt = Date.parse(item.companyInfo.endDate);
            //                 item.companyInfo.startOpened = false;
            //                 item.companyInfo.endOpened = false;
            //
            //                 //due to issue with grouping by company name
            //                 item.companyInfo.nameTemp = item.companyInfo.name;
            //             }
            //         })
            //     })
            // };

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

            // var getProject = function (projectId) {
            //     var project;
            //     self.personProjects.some(function (item) {
            //         if (item.id === projectId) {
            //             project = item;
            //             return true;
            //         }
            //     });
            //     return project;
            // };
            //
            var checkSkillInArray = function (skillName, companyKey, projectKey) {
                let check = false;
                const project = self.personCompanies[companyKey].projects[projectKey];
                check = project.environment.some(function (item) {
                    return item.skill === skillName;
                });
                return check;
            };

            const skillForProject = function (arr, companyKey, projectKey) {
                let output = [];
                arr.forEach(function (skill) {
                    if (!checkSkillInArray(skill, companyKey, projectKey))
                        output.push(skill);
                });
                return output;
            };

            queryPersonProjects();

            self.toggleEditCompany = function (companyId) {
                self.personCompanies.some(function (item) {
                    if (item.id === companyId) {
                        item.editCompanyFlag = !item.editCompanyFlag;
                        return true;
                    }
                })
            };

            // self.toggleEditProject = function (projectId) {
            //     self.personProjects.some(function (item) {
            //         if (item.id === projectId) {
            //             item.editProjectFlag = !item.editProjectFlag;
            //             return true;
            //         }
            //     })
            // };
            //
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
            //
            // self.addProject = function (companyId) {
            //     var company = getCompany(companyId);
            //
            //     self.personProjects.push({
            //         id: "temp" + Math.floor(Date.now() / 100),
            //         companyInfo: company,
            //         personId: $routeParams.personId,
            //         editProjectFlag: true,
            //         environmentSkills: []
            //     });
            // };

            // self.submitProject = function (projectId, form) {
            //     var project = getProject(projectId);
            //     project.environmentSkills.forEach(function (item, i) {
            //         item.position = i + 1;
            //     });
            //     if (angular.isNumber(projectId)) {
            //         ProjectSrv.update({projectId: projectId}, project).$promise.then(function () {
            //             queryPersonProject(projectId);
            //             form.$setPristine();
            //         });
            //     } else {
            //         if (angular.isNumber(project.companyInfo.id)) {
            //             project.id = null;
            //             ProjectSrv.save(project).$promise.then(function (response) {
            //                 queryPersonProject(response.data.id);
            //                 form.$setPristine();
            //             });
            //         } else {
            //             project.companyInfo.id = null;
            //             project.companyInfo.startDate = $filter('date')(project.companyInfo.startDt, "yyyy/MM/dd");
            //             project.companyInfo.endDate = $filter('date')(project.companyInfo.endDt, "yyyy/MM/dd");
            //             CompanySrv.save(project.companyInfo).$promise.then(function (response) {
            //                 project.companyInfo.id = response.data.id;
            //                 project.id = null;
            //                 ProjectSrv.save(project).$promise.then(function (response) {
            //                     queryPersonProject(response.data.id);
            //                     self.addCompanyFlag = false;
            //                     form.$setPristine();
            //                 });
            //             });
            //         }
            //     }
            // };

            // self.submitCompany = function (companyId, form) {
            //     var company = getCompany(companyId);
            //
            //     company.startDate = $filter('date')(company.startDt, "yyyy/MM/dd");
            //     company.endDate = $filter('date')(company.endDt, "yyyy/MM/dd");
            //
            //     if (angular.isNumber(companyId)) {
            //         CompanySrv.update({companyId: companyId}, company).$promise.then(function () {
            //             queryPersonCompany(companyId);
            //             form.$setPristine();
            //         });
            //     } else {
            //         company.id = null;
            //         CompanySrv.save(company).$promise.then(function (response) {
            //             queryPersonCompany(response.data.id);
            //             form.$setPristine();
            //         });
            //     }
            // };

            // self.resetProject = function (projectId, form) {
            //     queryPersonProject(projectId);
            //     form.$setPristine();
            // };
            //
            // self.resetCompany = function (companyId, form) {
            //     queryPersonCompany(companyId);
            //     form.$setPristine();
            // };
            //
            self.toggleNewSkill = function (keyCompany, keyProject) {
                self.personCompanies[keyCompany].projects[keyProject].addSkillFlag = !self.personCompanies[keyCompany].projects[keyProject].addSkillFlag;
                self.personCompanies[keyCompany].projects[keyProject].newSkill = "";

            };
            //
            // self.removeSkill = function (name, projectId, form) {
            //     var project = getProject(projectId);
            //     project.environmentSkills.some(function (skill, i, arr) {
            //         if (skill.skill.name === name) {
            //             form.$setDirty();
            //             arr.splice(i, 1);
            //             return true;
            //         }
            //     });
            // };
            //
            // self.addNewSkill = function (skill, projectId, form) {
            //     var project = getProject(projectId);
            //     if (skill.id === undefined) {
            //         if (!checkSkillInArray(skill, projectId)) {
            //             form.$setDirty();
            //             project.environmentSkills.push({
            //                 skill: {name: skill}
            //             });
            //         }
            //     } else {
            //         if (!checkSkillInArray(skill.name, projectId)) {
            //             form.$setDirty();
            //             project.environmentSkills.push({
            //                 skill: {id: skill.id, name: skill.name}
            //             });
            //         }
            //     }
            //     project.addSkillFlag = false;
            //     project.newSkill = "";
            // };

            // self.deleteProject = function (projectId) {
            //     if (angular.isNumber(projectId)) {
            //         ProjectSrv.delete({projectId: projectId}).$promise.then(function () {
            //             queryPersonProjects();
            //         });
            //     } else {
            //         self.personProjects.some(function (item, i, arr) {
            //             if (item.id === projectId) {
            //                 arr.splice(i, 1);
            //                 if (!angular.isNumber(item.companyInfo.id))
            //                     self.addCompanyFlag = false;
            //             }
            //         })
            //     }
            // };

            // self.deleteCompany = function (companyId) {
            //     if (angular.isNumber(companyId)) {
            //         CompanySrv.delete({companyId: companyId}).$promise.then(function () {
            //             queryPersonProjects();
            //         });
            //     } else {
            //         self.personProjects.some(function (item, i, arr) {
            //             if (item.companyInfo.id === companyId)
            //                 arr.splice(i, 1)
            //         })
            //     }
            // };

            // self.addCompany = function () {
            //     self.addCompanyFlag = true;
            //     var tempId = "temp" + Math.floor(Date.now() / 100);
            //     self.personProjects.push({
            //         id: tempId,
            //         companyInfo: {
            //             id: tempId,
            //             editCompanyFlag: true
            //         },
            //         personId: $routeParams.personId,
            //         editProjectFlag: true,
            //         environmentSkills: []
            //     });
            // };

        }]
});