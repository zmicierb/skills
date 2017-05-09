'use strict';

describe('PersonProjectSrv', function () {
    var $httpBackend;
    var PersonProjectSrv;
    var personProjectsData = {
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
        }]
    };

    // Add a custom equality tester before each test
    beforeEach(function () {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `Person` service before each test
    beforeEach(module('core.personProjectSrv'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _PersonProjectSrv_) {
        $httpBackend = _$httpBackend_;
        $httpBackend.expectGET('/api/person/1/projects').respond(personProjectsData);

        PersonProjectSrv = _PersonProjectSrv_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should fetch the person projects', function () {
        var personProjects = PersonProjectSrv.query({personId: 1});

        expect(personProjects).toEqual({});

        $httpBackend.flush();
        expect(personProjects).toEqual(personProjectsData);
    });

});