'use strict';

describe('ProjectSrv', function () {
    var $httpBackend;
    var ProjectSrv;
    var projectData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "total": null,
        "data": {
            "id": 3,
            "personId": 1,
            "position": {
                "id": 1,
                "name": "Test"
            },
            "description": "Test",
            "environmentSkills": [
                {
                    "id": 7,
                    "projectId": 3,
                    "skillId": 3,
                    "skill": {
                        "id": 3,
                        "name": "Test"
                    },
                    "position": 1
                },
                {
                    "id": 8,
                    "projectId": 3,
                    "skillId": 12,
                    "skill": {
                        "id": 12,
                        "name": "Test"
                    },
                    "position": 2
                }
            ],
            "result": "Test",
            "responsibility": "Test",
            "companyInfo": {
                "id": 2,
                "name": "Test",
                "startDate": "2010/09/01",
                "endDate": "2016/10/30"
            },
            "deleted": false
        }
    };
    var projectDataUpdate = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "data": {
            "id": 3,
            "personId": 1,
            "position": {"id": 1, "name": "Test"},
            "description": "Test",
            "environmentSkills": [{
                "id": 7,
                "projectId": 3,
                "skillId": 3,
                "skill": {"id": 3, "name": "Test"},
                "position": 1
            }, {
                "id": 8,
                "projectId": 3,
                "skillId": 12,
                "skill": {"id": 12, "name": "Test"},
                "position": 2
            }],
            "result": "Test",
            "responsibility": "Test",
            "companyInfo": {
                "id": 2,
                "name": "Test",
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
    var projectDataDeleted = {
        data: null,
        errors: null,
        first: false,
        last: false,
        message: "Completed successfully",
        number: 0,
        numberOfElements: 0,
        size: 0,
        sort: null,
        success: true,
        totalElements: 0,
        totalPages: 0
    };

    // Add a custom equality tester before each test
    beforeEach(function () {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `PersonFind` service before each test
    beforeEach(module('core.projectSrv'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _ProjectSrv_) {
        $httpBackend = _$httpBackend_;

        ProjectSrv = _ProjectSrv_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should find project data by id', function () {
        $httpBackend.expectGET('/api/project/1').respond(projectData);
        var project = ProjectSrv.get({projectId: 1});

        expect(project).toEqual({});

        $httpBackend.flush();
        expect(project).toEqual(projectData);
    });

    it('should update project data by id', function () {
        $httpBackend.expectPUT('/api/project/1').respond(projectDataUpdate);
        var project = ProjectSrv.update({projectId: 1}, projectDataUpdate.data);

        expect(project).toEqual(projectDataUpdate.data);

        $httpBackend.flush();
        expect(project).toEqual(projectDataUpdate);
    });

    it('should save project data', function () {
        $httpBackend.expectPOST('/api/project').respond(projectDataUpdate);
        var project = ProjectSrv.save(projectDataUpdate.data);

        expect(project).toEqual(projectDataUpdate.data);

        $httpBackend.flush();
        expect(project).toEqual(projectDataUpdate);
    });

    it('should delete project data by id', function () {
        $httpBackend.expectDELETE('/api/project/1').respond(projectDataDeleted);
        var project = ProjectSrv.delete({projectId: 1});

        expect(project).toEqual({});

        $httpBackend.flush();
        expect(project).toEqual(projectDataDeleted);
    });

});