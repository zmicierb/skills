'use strict';

describe('PersonSrv', function () {
    var $httpBackend;
    var PersonSrv;
    var personsData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "total": null,
        "data": [{name: 'Name X'},
            {name: 'Name Y'},
            {name: 'Name Z'}]
    };
    var personDetailData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "total": null,
        "data": [{
            "id": 1,
            "name": "Dzmitry Barysevich",
            "position": {"id": 1, "name": "Java Developer", "new": false},
            "department": {"id": 1, "name": "Application Development department", "new": false},
            "birthDate": "1987-06-20",
            "deleted": false,
            "new": false
        }]
    };

    // Add a custom equality tester before each test
    beforeEach(function () {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `Person` service before each test
    beforeEach(module('core.personSrv'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _PersonSrv_) {
        $httpBackend = _$httpBackend_;

        PersonSrv = _PersonSrv_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should fetch the persons data from `/api/person`', function () {
        $httpBackend.expectGET('/api/person').respond(personsData);
        var persons = PersonSrv.query();

        expect(persons).toEqual({});

        $httpBackend.flush();
        expect(persons).toEqual(personsData);
    });

    it('should fetch the person details from `/api/person/1`', function () {
        $httpBackend.expectGET('/api/person/1').respond(personDetailData);
        var personDetail = PersonSrv.get({personId: 1});

        expect(personDetail).toEqual({});

        $httpBackend.flush();
        expect(personDetail).toEqual(personDetailData);
    });

});