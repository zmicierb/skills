describe('personProject', function () {

    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonProjectController', function () {
        var $httpBackend, ctrl;

        beforeEach(inject(function ($componentController, _$httpBackend_, $routeParams) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/api/person/1/projects')
                .respond({
                    "success": true,
                    "message": "Completed successfully",
                    "errors": null,
                    "total": null,
                    "data": [{
                        "id": 1,
                        "personId": 1,
                        "projectId": 1,
                        "project": {
                            "id": 1,
                            "position": {"id": 1, "name": "Java Developer", "new": false},
                            "description": "Intranet web-portal for company.",
                            "environmentRow": [{
                                "id": 1,
                                "projectId": 1,
                                "skillId": 7,
                                "skill": {"id": 7, "name": "Java EE 6", "new": false},
                                "weight": 3,
                                "new": false
                            }, {
                                "id": 2,
                                "projectId": 1,
                                "skillId": 20,
                                "skill": {"id": 20, "name": "Liferay Enterprise", "new": false},
                                "weight": 2,
                                "new": false
                            }, {
                                "id": 3,
                                "projectId": 1,
                                "skillId": 25,
                                "skill": {"id": 25, "name": "PostgreSQL", "new": false},
                                "weight": 1,
                                "new": false
                            }],
                            "result": "Project in progress.",
                            "responsibility": "Developing portlet.",
                            "deleted": false,
                            "new": false
                        },
                        "companyInfo": {
                            "id": 1,
                            "name": "IBA",
                            "startDate": "2016-11-01",
                            "position": {"id": 1, "name": "Java Developer", "new": false},
                            "deleted": false,
                            "new": false
                        },
                        "new": false
                    }, {
                        "id": 2,
                        "personId": 1,
                        "projectId": 2,
                        "project": {
                            "id": 2,
                            "position": {"id": 1, "name": "Java Developer", "new": false},
                            "description": "Online-shop for sale valuables.",
                            "environmentRow": [{
                                "id": 4,
                                "projectId": 2,
                                "skillId": 7,
                                "skill": {"id": 7, "name": "Java EE 6", "new": false},
                                "weight": 2,
                                "new": false
                            }, {
                                "id": 5,
                                "projectId": 2,
                                "skillId": 17,
                                "skill": {"id": 17, "name": "IBM WebSphere Application Server 8.5", "new": false},
                                "weight": 1,
                                "new": false
                            }, {
                                "id": 6,
                                "projectId": 2,
                                "skillId": 18,
                                "skill": {"id": 18, "name": "IBM WebSphere Portal Server 8.5", "new": false},
                                "weight": 3,
                                "new": false
                            }],
                            "result": "First phase is complete.",
                            "responsibility": "Developing catalog of items.",
                            "deleted": false,
                            "new": false
                        },
                        "companyInfo": {
                            "id": 1,
                            "name": "IBA",
                            "startDate": "2016-11-01",
                            "position": {"id": 1, "name": "Java Developer", "new": false},
                            "deleted": false,
                            "new": false
                        },
                        "new": false
                    }]
                });

            $routeParams.personId = '1';

            ctrl = $componentController('personProject');
        }));

        it('should fetch the person projects', function () {
            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.personProjects).toBeUndefined();

            $httpBackend.flush();
            expect(ctrl.personProjects.length).toEqual(2);
        });

    });

});