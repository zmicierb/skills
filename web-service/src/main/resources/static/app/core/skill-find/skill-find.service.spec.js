'use strict';

describe('SkillFindSrv', function () {
    var $httpBackend;
    var SkillFindSrv;
    var skillsData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "data": [{"id": 1, "name": "Java 6"}, {"id": 2, "name": "Java 7"}, {
            "id": 3,
            "name": "Java 8"
        }, {"id": 6, "name": "JavaScript"}, {"id": 7, "name": "Java EE 6"}, {
            "id": 8,
            "name": "EJB 3.0"
        }, {"id": 9, "name": "JPA/Hibernate"}, {"id": 10, "name": "JSF 2.0"}, {
            "id": 11,
            "name": "JSP"
        }, {"id": 13, "name": "JQuery"}],
        "first": false,
        "last": false,
        "totalPages": 0,
        "totalElements": 0,
        "size": 0,
        "number": 0,
        "numberOfElements": 0,
        "sort": null
    };

    // Add a custom equality tester before each test
    beforeEach(function () {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `PersonFind` service before each test
    beforeEach(module('core.skillFindSrv'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _SkillFindSrv_) {
        $httpBackend = _$httpBackend_;
        $httpBackend.expectGET('/api/skill/find/J').respond(skillsData);

        SkillFindSrv = _SkillFindSrv_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should find skill by name', function () {
        var skills = SkillFindSrv.get({query: "J"});

        expect(skills).toEqual({});

        $httpBackend.flush();
        expect(skills).toEqual(skillsData);
    });

});