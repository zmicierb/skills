'use strict';

describe('Person', function () {
    var $httpBackend;
    var Person;
    var personsData = [
        {name: 'Name X'},
        {name: 'Name Y'},
        {name: 'Name Z'}
    ];

    // Add a custom equality tester before each test
    beforeEach(function () {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `Person` service before each test
    beforeEach(module('core.person'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _Person_) {
        $httpBackend = _$httpBackend_;
        $httpBackend.expectGET('/api/person/').respond(personsData);

        Person = _Person_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should fetch the persons data from `/api/person`', function () {
        var persons = Person.query();

        expect(persons).toEqual([]);

        $httpBackend.flush();
        expect(persons).toEqual(personsData);
    });

});