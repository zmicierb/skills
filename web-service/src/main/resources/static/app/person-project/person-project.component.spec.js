describe('personProject', function () {

    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonProjectController', function () {
        var $httpBackend, ctrl, $scope;
        var personProjectsResponse = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": [{
                "id": 1,
                "personId": 1,
                "position": {"id": 1, "name": "Java Developer"},
                "description": "Intranet web-portal for company.",
                "environmentSkills": [{
                    "id": 1,
                    "projectId": 1,
                    "skillId": 7,
                    "skill": {"id": 7, "name": "Java EE 6"},
                    "position": 1
                }, {
                    "id": 2,
                    "projectId": 1,
                    "skillId": 20,
                    "skill": {"id": 20, "name": "Liferay Enterprise"},
                    "position": 2
                }, {
                    "id": 3,
                    "projectId": 1,
                    "skillId": 25,
                    "skill": {"id": 25, "name": "PostgreSQL"},
                    "position": 3
                }],
                "result": "Project in progress.",
                "responsibility": "Developing portlet.",
                "companyInfo": {"id": 1, "name": "IBA", "startDate": "2016/11/01", "endDate": null},
                "deleted": false
            }, {
                "id": 2,
                "personId": 1,
                "position": {"id": 1, "name": "Java Developer"},
                "description": "Online-shop for sale valuables.",
                "environmentSkills": [{
                    "id": 4,
                    "projectId": 2,
                    "skillId": 7,
                    "skill": {"id": 7, "name": "Java EE 6"},
                    "position": 1
                }, {
                    "id": 5,
                    "projectId": 2,
                    "skillId": 17,
                    "skill": {"id": 17, "name": "IBM WebSphere Application Server 8.5"},
                    "position": 2
                }, {
                    "id": 6,
                    "projectId": 2,
                    "skillId": 18,
                    "skill": {"id": 18, "name": "IBM WebSphere Portal Server 8.5"},
                    "position": 3
                }],
                "result": "First phase is complete.",
                "responsibility": "Developing catalog of items.",
                "companyInfo": {"id": 1, "name": "IBA", "startDate": "2016/11/01", "endDate": null},
                "deleted": false
            }, {
                "id": 3,
                "personId": 1,
                "position": {"id": 1, "name": "Java Developer"},
                "description": "Enterprise web-application for flow of documentations.",
                "environmentSkills": [{
                    "id": 7,
                    "projectId": 3,
                    "skillId": 3,
                    "skill": {"id": 3, "name": "Java 8"},
                    "position": 1
                }, {
                    "id": 8,
                    "projectId": 3,
                    "skillId": 12,
                    "skill": {"id": 12, "name": "Spring 4.x"},
                    "position": 2
                }, {
                    "id": 9,
                    "projectId": 3,
                    "skillId": 21,
                    "skill": {"id": 21, "name": "Oracle 11"},
                    "position": 3
                }],
                "result": "Working project from scratch in tight terms.",
                "responsibility": "Developing functionality for editing and printing documents from web-browser.",
                "companyInfo": {
                    "id": 2,
                    "name": "BELHARD",
                    "startDate": "2010/09/01",
                    "endDate": "2016/10/30"
                },
                "deleted": false
            }, {
                "id": 4,
                "personId": 1,
                "position": {"id": 1, "name": "Java Developer"},
                "description": "Enterprise web-application for organization with branches in every regional city in Belarus.",
                "environmentSkills": [{
                    "id": 10,
                    "projectId": 4,
                    "skillId": 2,
                    "skill": {"id": 2, "name": "Java 7"},
                    "position": 1
                }, {
                    "id": 11,
                    "projectId": 4,
                    "skillId": 5,
                    "skill": {"id": 5, "name": "PL/SQL"},
                    "position": 2
                }, {
                    "id": 12,
                    "projectId": 4,
                    "skillId": 46,
                    "skill": {"id": 46, "name": "Jasper Report"},
                    "position": 3
                }],
                "result": "Long term cooperation in full-stack developer team.",
                "responsibility": "Developing functionality for printing indetifier card of applicants from web-browser.",
                "companyInfo": {
                    "id": 2,
                    "name": "BELHARD",
                    "startDate": "2010/09/01",
                    "endDate": "2016/10/30"
                },
                "deleted": false
            }, {
                "id": 5,
                "personId": 1,
                "position": {"id": 2, "name": "Senior System Integrator"},
                "environmentSkills": [{
                    "id": 13,
                    "projectId": 5,
                    "skillId": 29,
                    "skill": {"id": 29, "name": "RHEL"},
                    "position": 2
                }, {
                    "id": 14,
                    "projectId": 5,
                    "skillId": 23,
                    "skill": {"id": 23, "name": "MySQL"},
                    "position": 1
                }],
                "result": "All projects are in production.",
                "responsibility": "Configuring OS, installing Oracle.",
                "companyInfo": {
                    "id": 2,
                    "name": "BELHARD",
                    "startDate": "2010/09/01",
                    "endDate": "2016/10/30"
                },
                "deleted": false
            }],
            "first": false,
            "last": false,
            "totalPages": 0,
            "totalElements": 0,
            "size": 0,
            "number": 0,
            "numberOfElements": 0,
            "sort": null
        };
        var personProjectResponse = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": {
                "id": 3,
                "personId": 1,
                "position": {"id": 1, "name": "Java Developer"},
                "description": "Enterprise web-application for flow of documentations.",
                "environmentSkills": [{
                    "id": 7,
                    "projectId": 3,
                    "skillId": 3,
                    "skill": {"id": 3, "name": "Java 8"},
                    "position": 1
                }, {
                    "id": 8,
                    "projectId": 3,
                    "skillId": 12,
                    "skill": {"id": 12, "name": "Spring 4.x"},
                    "position": 2
                }, {
                    "id": 9,
                    "projectId": 3,
                    "skillId": 21,
                    "skill": {"id": 21, "name": "Oracle 11"},
                    "position": 3
                }],
                "result": "Working project from scratch in tight terms.",
                "responsibility": "Developing functionality for editing and printing documents from web-browser.",
                "companyInfo": {
                    "id": 2,
                    "name": "BELHARD",
                    "startDate": "2010/09/01",
                    "endDate": "2016/10/30"
                },
                "deleted": false
            },
            "first": false,
            "last": false,
            "totalPages": 0,
            "totalElements": 0,
            "size": 0,
            "number": 0,
            "numberOfElements": 0,
            "sort": null
        };
        var personProjectUpdateResponse = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": {
                "id": 3,
                "personId": 1,
                "position": {"id": 1, "name": "Java Developer"},
                "description": "Enterprise web-application for flow of documentations.",
                "environmentSkills": [{
                    "id": 7,
                    "projectId": 3,
                    "skillId": 3,
                    "skill": {"id": 3, "name": "Java 8"},
                    "position": 1
                }, {
                    "id": 8,
                    "projectId": 3,
                    "skillId": 12,
                    "skill": {"id": 12, "name": "Spring 4.x"},
                    "position": 2
                }, {
                    "id": 9,
                    "projectId": 3,
                    "skillId": 21,
                    "skill": {"id": 21, "name": "Oracle 11"},
                    "position": 3
                }, {
                    "id": 1479,
                    "projectId": null,
                    "skillId": null,
                    "skill": {"id": 4, "name": "SQL"},
                    "position": 4
                }],
                "result": "Working project from scratch in tight terms.",
                "responsibility": "Developing functionality for editing and printing documents from web-browser.",
                "companyInfo": {
                    "id": 2,
                    "name": "BELHARD",
                    "startDate": "2010/09/01",
                    "endDate": "2016/10/30"
                },
                "deleted": false,
                "new": false
            },
            "first": false,
            "last": false,
            "totalPages": 0,
            "totalElements": 0,
            "size": 0,
            "number": 0,
            "numberOfElements": 0,
            "sort": null
        };
        var personNewProject = {
            "id": "temp1",
            "personId": 1,
            "position": {"id": 1, "name": "Java Developer"},
            "description": "Enterprise web-application for flow of documentations.",
            "environmentSkills": [{
                "id": 7,
                "projectId": 3,
                "skillId": 3,
                "skill": {"id": 3, "name": "Java 8"},
                "position": 1
            }, {
                "id": 8,
                "projectId": 3,
                "skillId": 12,
                "skill": {"id": 12, "name": "Spring 4.x"},
                "position": 2
            }, {
                "id": 9,
                "projectId": 3,
                "skillId": 21,
                "skill": {"id": 21, "name": "Oracle 11"},
                "position": 3
            }, {
                "id": 1479,
                "projectId": null,
                "skillId": null,
                "skill": {"id": 4, "name": "SQL"},
                "position": 4
            }],
            "result": "Working project from scratch in tight terms.",
            "responsibility": "Developing functionality for editing and printing documents from web-browser.",
            "companyInfo": {
                "id": 2,
                "name": "BELHARD",
                "startDate": "2010/09/01",
                "endDate": "2016/10/30"
            }
        };
        var personNewProjectResponse = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": {
                "id": 10,
                "personId": 1,
                "position": {"id": 1, "name": "Java Developer"},
                "description": "Enterprise web-application for flow of documentations.",
                "environmentSkills": [{
                    "id": 7,
                    "projectId": 3,
                    "skillId": 3,
                    "skill": {"id": 3, "name": "Java 8"},
                    "position": 1
                }, {
                    "id": 8,
                    "projectId": 3,
                    "skillId": 12,
                    "skill": {"id": 12, "name": "Spring 4.x"},
                    "position": 2
                }, {
                    "id": 9,
                    "projectId": 3,
                    "skillId": 21,
                    "skill": {"id": 21, "name": "Oracle 11"},
                    "position": 3
                }, {
                    "id": 1479,
                    "projectId": null,
                    "skillId": null,
                    "skill": {"id": 4, "name": "SQL"},
                    "position": 4
                }],
                "result": "Working project from scratch in tight terms.",
                "responsibility": "Developing functionality for editing and printing documents from web-browser.",
                "companyInfo": {
                    "id": 2,
                    "name": "BELHARD",
                    "startDate": "2010/09/01",
                    "endDate": "2016/10/30"
                }
            },
            "first": false,
            "last": false,
            "totalPages": 0,
            "totalElements": 0,
            "size": 0,
            "number": 0,
            "numberOfElements": 0,
            "sort": null
        };
        var personCompanyResponse = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": {"id": 1, "name": "IBA", "startDate": "2016/11/01", "endDate": null},
            "first": false,
            "last": false,
            "totalPages": 0,
            "totalElements": 0,
            "size": 0,
            "number": 0,
            "numberOfElements": 0,
            "sort": null
        };
        var personCompanyUpdateResponse = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": {"id": 1, "name": "IBA", "startDate": "2016/11/02", "endDate": null},
            "first": false,
            "last": false,
            "totalPages": 0,
            "totalElements": 0,
            "size": 0,
            "number": 0,
            "numberOfElements": 0,
            "sort": null
        };
        var personNewCompany = {"id": "temp4", "name": "IBA", "startDate": "2016/11/02", "endDate": null};
        var personNewCompanyResponse = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": {"id": 4, "name": "IBA", "startDate": "2016/11/02", "endDate": null},
            "first": false,
            "last": false,
            "totalPages": 0,
            "totalElements": 0,
            "size": 0,
            "number": 0,
            "numberOfElements": 0,
            "sort": null
        };

        beforeEach(inject(function ($componentController, _$httpBackend_, $routeParams, $rootScope, $controller, formDirective) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/api/person/1/projects')
                .respond(personProjectsResponse);
            $scope = $rootScope.$new();

            $routeParams.personId = '1';

            ctrl = $componentController('personProject', {$scope: $scope});

            //mock form
            ctrl.form = $controller(formDirective[0].controller, {
                $scope: $scope,
                $element: angular.element("<form></form>"),
                $attrs: {}
            });
        }));

        it('should fetch person projects', function () {
            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.personProjects).toBeUndefined();

            $httpBackend.flush();
            expect(ctrl.personProjects.length).toEqual(personProjectsResponse.data.length);

            angular.forEach(ctrl.personProjects, function (project) {
                project.environmentSkills.forEach(function (skill, i) {
                    if (i > 0) {
                        expect(skill.position).toBeGreaterThan(project.environmentSkills[i - 1].position);
                    }
                })
            });
        });

        it('should fetch the person project', function () {
            var projectId = 3;
            $httpBackend.expectGET('/api/project/' + projectId)
                .respond(personProjectResponse);

            jasmine.addCustomEqualityTester(angular.equals);

            ctrl.resetProject(projectId, ctrl.form);

            expect(ctrl.personProjects).toBeUndefined();

            $httpBackend.flush();

            expect(personProjectResponse.data.id).toEqual(projectId);
        });

        it('should update the person project', function () {
            var projectId = 3;

            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.personProjects).toBeUndefined();

            $httpBackend.flush();

            ctrl.submitProject(projectId, ctrl.form);
            $httpBackend.expectPUT('/api/project/' + projectId)
                .respond(personProjectUpdateResponse);
            $httpBackend.expectGET('/api/project/' + projectId)
                .respond(personProjectResponse);
            $httpBackend.flush();

            expect(personProjectUpdateResponse.data.id).toEqual(projectId);
        });

        it('should save new person project', function () {
            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.personProjects).toBeUndefined();

            $httpBackend.flush();
            ctrl.personProjects.push(personNewProject);
            ctrl.submitProject(personNewProject.id, ctrl.form);
            $httpBackend.expectPOST('/api/project')
                .respond(personNewProjectResponse);
            $httpBackend.expectGET('/api/project/' + personNewProjectResponse.data.id)
                .respond(personNewProjectResponse);
            $httpBackend.flush();
        });

        it('should fetch the person company', function () {
            var companyId = 1;
            $httpBackend.expectGET('/api/company/' + companyId)
                .respond(personProjectResponse);

            jasmine.addCustomEqualityTester(angular.equals);

            ctrl.resetCompany(companyId, ctrl.form);

            expect(ctrl.personProjects).toBeUndefined();

            $httpBackend.flush();

            expect(personCompanyResponse.data.id).toEqual(companyId);
        });

        it('should update the person company', function () {
            var companyId = 1;

            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.personProjects).toBeUndefined();

            $httpBackend.flush();

            ctrl.submitCompany(companyId, ctrl.form);
            $httpBackend.expectPUT('/api/company/' + companyId)
                .respond(personCompanyUpdateResponse);
            $httpBackend.expectGET('/api/company/' + companyId)
                .respond(personCompanyResponse);
            $httpBackend.flush();

            expect(personCompanyUpdateResponse.data.id).toEqual(companyId);
        });

        it('should save new person company', function () {
            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.personProjects).toBeUndefined();

            $httpBackend.flush();
            var project = personProjectResponse.data;
            project.companyInfo = personNewCompany;
            ctrl.personProjects.push(project);
            ctrl.submitCompany(personNewCompany.id, ctrl.form);
            $httpBackend.expectPOST('/api/company')
                .respond(personNewCompanyResponse);
            $httpBackend.expectGET('/api/company/' + personNewCompanyResponse.data.id)
                .respond(personNewCompanyResponse);
            $httpBackend.flush();
        });

    });

});