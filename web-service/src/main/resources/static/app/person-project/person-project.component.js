'use strict';

angular.module('skillsApp').component('personProject', {
    templateUrl: 'person-project/person-project.template.html',
    controller: ['$routeParams', '$filter', 'PersonProjectSrv', 'PositionSrv', 'PositionFindSrv', 'ProjectSrv', 'CompanySrv', 'SkillSrv', 'SkillFindSrv',
        function PersonDetailController($routeParams, $filter, PersonProjectSrv, PositionSrv, PositionFindSrv, ProjectSrv, CompanySrv, SkillSrv, SkillFindSrv) {
            var self = this;
            var queryPersonProjects = function () {
                var personProjects = PersonProjectSrv.query({personId: $routeParams.personId}, function () {
                    personProjects.data.forEach(function (project) {
                        //sort skills in environment rows by position
                        project.environmentSkills.sort(function (a, b) {
                            return a.position - b.position;
                        });
                        //convert for datepicker
                        project.companyInfo.startDt = Date.parse(project.companyInfo.startDate);
                        project.companyInfo.endDt = Date.parse(project.companyInfo.endDate);
                        project.companyInfo.startOpened = false;
                        project.companyInfo.endOpened = false;

                        //due to issue with grouping by company name
                        project.companyInfo.nameTemp = project.companyInfo.name;
                    });
                    self.personProjects = personProjects.data;
                });
            };

            var queryPersonProject = function (projectId) {
                var personProject = ProjectSrv.get({projectId: projectId}, function () {
                    //sort skills in environment rows by position
                    personProject.data.environmentSkills.sort(function (a, b) {
                        return a.position - b.position;
                    });
                    self.personProjects.some(function (item) {
                        if (item.id == projectId || item.id == null) {
                            item.id = personProject.data.id;
                            item.environmentSkills = personProject.data.environmentSkills;
                            item.position = personProject.data.position;
                            item.responsibility = personProject.data.responsibility;
                            item.result = personProject.data.result;
                            item.description = personProject.data.description;
                            item.editProjectFlag = true;
                            return true;
                        }
                    })
                })
            };

            var queryPersonCompany = function (companyId) {
                var personCompany = CompanySrv.get({companyId: companyId}, function () {
                    self.personProjects.forEach(function (item) {
                        if (item.companyInfo.id == companyId || item.id == null) {
                            item.companyInfo = personCompany.data;
                            item.companyInfo.editCompanyFlag = true;

                            //convert for datepicker
                            item.companyInfo.startDt = Date.parse(item.companyInfo.startDate);
                            item.companyInfo.endDt = Date.parse(item.companyInfo.endDate);
                            item.companyInfo.startOpened = false;
                            item.companyInfo.endOpened = false;

                            //due to issue with grouping by company name
                            item.companyInfo.nameTemp = item.companyInfo.name;
                        }
                    })
                })
            };

            self.dateOptions = {
                formatYear: 'yyyy',
                startingDay: 1
            };

            self.open = function (companyId, type) {
                self.personProjects.some(function (item) {
                    if (item.companyInfo.id == companyId) {
                        if (type == "start")
                            item.companyInfo.startOpened = true;
                        if (type == "end")
                            item.companyInfo.endOpened = true;
                        return true;
                    }
                })
            };

            var getProject = function (projectId) {
                var project;
                self.personProjects.some(function (item) {
                    if (item.id == projectId) {
                        project = item;
                        return true;
                    }
                });
                return project;
            };

            var getCompany = function (companyId) {
                var company;
                self.personProjects.some(function (item) {
                    if (item.companyInfo.id == companyId) {
                        company = item.companyInfo;
                        return true;
                    }
                });
                return company;
            };

            var checkSkillInArray = function (skillName, projectId) {
                var check = false;
                var project = getProject(projectId);
                check = project.environmentSkills.some(function (item) {
                    return item.skill.name == skillName;
                });
                return check;
            };

            var skillForProject = function (arr, projectId) {
                var output = [];
                arr.forEach(function (skill) {
                    if (!checkSkillInArray(skill.name, projectId))
                        output.push(skill);
                });
                return output;
            };

            queryPersonProjects();

            self.toggleEditCompany = function (companyId) {
                self.personProjects.some(function (item) {
                    if (item.companyInfo.id == companyId) {
                        item.companyInfo.editCompanyFlag = !item.companyInfo.editCompanyFlag;
                        return true;
                    }
                })
            };

            self.toggleEditProject = function (projectId) {
                self.personProjects.some(function (item) {
                    if (item.id == projectId) {
                        item.editProjectFlag = !item.editProjectFlag;
                        return true;
                    }
                })
            };

            self.getPosition = function (val) {
                if (val) {
                    return PositionFindSrv.get({query: val, page: 0, size: 10}).$promise.then(function (response) {
                            return response.data;
                        }
                    );
                } else {
                    return PositionSrv.get({page: 0, size: 10}).$promise.then(function (response) {
                            return response.data;
                        }
                    );
                }
            };

            self.getSkill = function (val, projectId) {
                if (val) {
                    return SkillFindSrv.get({query: val, page: 0, size: 10}).$promise.then(function (response) {
                        return skillForProject(response.data, projectId)
                    });
                } else {
                    return SkillSrv.get({page: 0, size: 10}).$promise.then(function (response) {
                        return skillForProject(response.data, projectId)
                    });
                }
            };

            self.addProject = function (companyId) {
                var company = getCompany(companyId);

                self.personProjects.push({
                    id: "temp" + Math.floor(Date.now() / 100),
                    companyInfo: company,
                    personId: $routeParams.personId,
                    editProjectFlag: true,
                    environmentSkills: []
                });
            };

            self.submitProject = function (projectId, form) {
                var project = getProject(projectId);
                project.environmentSkills.forEach(function (item, i) {
                    item.position = i + 1;
                });
                if (angular.isNumber(projectId)) {
                    ProjectSrv.update({projectId: projectId}, project).$promise.then(function () {
                        queryPersonProject(projectId);
                        form.$setPristine();
                    });
                } else {
                    if (angular.isNumber(project.companyInfo.id)) {
                        project.id = null;
                        ProjectSrv.save(project).$promise.then(function (response) {
                            queryPersonProject(response.data.id);
                            form.$setPristine();
                        });
                    } else {
                        project.companyInfo.id = null;
                        project.companyInfo.startDate = $filter('date')(project.companyInfo.startDt, "yyyy/MM/dd");
                        project.companyInfo.endDate = $filter('date')(project.companyInfo.endDt, "yyyy/MM/dd");
                        CompanySrv.save(project.companyInfo).$promise.then(function (response) {
                            project.companyInfo.id = response.data.id;
                            project.id = null;
                            ProjectSrv.save(project).$promise.then(function (response) {
                                queryPersonProject(response.data.id);
                                self.addCompanyFlag = false;
                                form.$setPristine();
                            });
                        });
                    }
                }
            };

            self.submitCompany = function (companyId, form) {
                var company = getCompany(companyId);

                company.startDate = $filter('date')(company.startDt, "yyyy/MM/dd");
                company.endDate = $filter('date')(company.endDt, "yyyy/MM/dd");

                if (angular.isNumber(companyId)) {
                    CompanySrv.update({companyId: companyId}, company).$promise.then(function () {
                        queryPersonCompany(companyId);
                        form.$setPristine();
                    });
                } else {
                    company.id = null;
                    CompanySrv.save(company).$promise.then(function (response) {
                        queryPersonCompany(response.data.id);
                        form.$setPristine();
                    });
                }
            };

            self.resetProject = function (projectId, form) {
                queryPersonProject(projectId);
                form.$setPristine();
            };

            self.resetCompany = function (companyId, form) {
                queryPersonCompany(companyId);
                form.$setPristine();
            };

            self.toggleNewSkill = function (projectId) {
                self.personProjects.some(function (item) {
                    if (item.id == projectId) {
                        item.addSkillFlag = !item.addSkillFlag;
                        item.newSkill = "";
                        return true;
                    }
                });
            };

            self.removeSkill = function (name, projectId, form) {
                var project = getProject(projectId);
                project.environmentSkills.some(function (skill, i, arr) {
                    if (skill.skill.name == name) {
                        form.$setDirty();
                        arr.splice(i, 1);
                        return true;
                    }
                });
            };

            self.addNewSkill = function (skill, projectId, form) {
                var project = getProject(projectId);
                if (skill.id == undefined) {
                    if (!checkSkillInArray(skill, projectId)) {
                        form.$setDirty();
                        project.environmentSkills.push({
                            skill: {name: skill}
                        });
                    }
                } else {
                    if (!checkSkillInArray(skill.name, projectId)) {
                        form.$setDirty();
                        project.environmentSkills.push({
                            skill: {id: skill.id, name: skill.name}
                        });
                    }
                }
                project.addSkillFlag = false;
                project.newSkill = "";
            };

            self.deleteProject = function (projectId) {
                if (angular.isNumber(projectId)) {
                    ProjectSrv.delete({projectId: projectId}).$promise.then(function () {
                        queryPersonProjects();
                    });
                } else {
                    self.personProjects.some(function (item, i, arr) {
                        if (item.id == projectId) {
                            arr.splice(i, 1);
                            if (!angular.isNumber(item.companyInfo.id))
                                self.addCompanyFlag = false;
                        }
                    })
                }
            };

            self.deleteCompany = function (companyId) {
                if (angular.isNumber(companyId)) {
                    CompanySrv.delete({companyId: companyId}).$promise.then(function () {
                        queryPersonProjects();
                    });
                } else {
                    self.personProjects.some(function (item, i, arr) {
                        if (item.companyInfo.id == companyId)
                            arr.splice(i, 1)
                    })
                }
            };

            self.addCompany = function () {
                self.addCompanyFlag = true;
                var tempId = "temp" + Math.floor(Date.now() / 100);
                self.personProjects.push({
                    id: tempId,
                    companyInfo: {
                        id: tempId,
                        editCompanyFlag: true
                    },
                    personId: $routeParams.personId,
                    editProjectFlag: true,
                    environmentSkills: []
                });
            };

        }]
});