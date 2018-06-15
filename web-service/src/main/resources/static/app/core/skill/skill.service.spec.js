'use strict';

describe('SkillSrv', function () {
    var $httpBackend;
    var SkillSrv;
    var skillsData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "data": [{"id": 1, "name": "Java 6"}, {"id": 2, "name": "Java 7"}, {
            "id": 3,
            "name": "Java 8"
        }, {"id": 4, "name": "SQL"}, {"id": 5, "name": "PL/SQL"}, {
            "id": 6,
            "name": "JavaScript"
        }, {"id": 7, "name": "Java EE 6"}, {"id": 8, "name": "EJB 3.0"}, {
            "id": 9,
            "name": "JPA/Hibernate"
        }, {"id": 10, "name": "JSF 2.0"}],
        "first": true,
        "last": false,
        "totalPages": 5,
        "totalElements": 50,
        "size": 10,
        "number": 0,
        "numberOfElements": 10,
        "sort": null
    };

    var skillData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "data": {"id": 1, "name": "Test"}
    };

    // Add a custom equality tester before each test
    beforeEach(function () {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `PersonFind` service before each test
    beforeEach(module('core.skillSrv'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _SkillSrv_) {
        $httpBackend = _$httpBackend_;

        SkillSrv = _SkillSrv_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should find skills data', function () {
        $httpBackend.expectGET('/api/skill').respond(skillsData);
        var skills = SkillSrv.get();

        expect(skills).toEqual({});

        $httpBackend.flush();
        expect(skills).toEqual(skillsData);
    });

    it('should find skill data by id', function () {
        $httpBackend.expectGET('/api/skill/1').respond(skillData);
        var skill = SkillSrv.get({skillId: 1});

        expect(skill).toEqual({});

        $httpBackend.flush();
        expect(skill).toEqual(skillData);
    });

});