'use strict';

describe('PersonSkillSrv', function () {
    var $httpBackend;
    var PersonSkillSrv;
    var personSkillsData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "total": null,
        "data": [{
            "id": 1,
            "personId": 1,
            "skillId": 1,
            "skill": {"id": 1, "name": "Java 6", "new": false},
            "rowId": 1,
            "row": {"id": 1, "name": "Programming Languages", "new": false},
            "position": 1,
            "new": false
        }, {
            "id": 2,
            "personId": 1,
            "skillId": 2,
            "skill": {"id": 2, "name": "Oracle 11", "new": false},
            "rowId": 1,
            "row": {"id": 2, "name": "Databases", "new": false},
            "position": 2,
            "new": false
        }]
    };
    var personSkillsResponseUpdate = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "data": personSkillsDataUpdate,
        "first": false,
        "last": false,
        "totalPages": 0,
        "totalElements": 0,
        "size": 0,
        "number": 0,
        "numberOfElements": 0,
        "sort": null
    };
    var personSkillsDataUpdate = {
        personId: 1,
        skill: {
            id: 1,
            name: "testSkill"
        },
        row: {
            id: 1,
            name: "testRow"
        },
        position: 1
    };

    // Add a custom equality tester before each test
    beforeEach(function () {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `Person` service before each test
    beforeEach(module('core.personSkillSrv'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _PersonSkillSrv_) {
        $httpBackend = _$httpBackend_;

        PersonSkillSrv = _PersonSkillSrv_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should fetch person skills', function () {
        $httpBackend.expectGET('/api/person/1/skills').respond(personSkillsData);
        var personSkills = PersonSkillSrv.query({personId: 1});

        expect(personSkills).toEqual({});

        $httpBackend.flush();
        expect(personSkills).toEqual(personSkillsData);
    });

    it('should update person skills', function () {
        $httpBackend.expectPUT('/api/person/1/skills').respond(personSkillsResponseUpdate);
        var personSkills = PersonSkillSrv.update({personId: 1}, personSkillsDataUpdate);

        expect(personSkills).toEqual(personSkillsDataUpdate);

        $httpBackend.flush();
        expect(personSkills).toEqual(personSkillsResponseUpdate);
    });

});