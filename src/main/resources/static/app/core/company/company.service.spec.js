'use strict';

describe('CompanySrv', function () {
    var $httpBackend;
    var CompanySrv;
    var companyData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "total": null,
        "data": {
            "endDate": null,
            "id": 1,
            "name": "Test",
            "new": false,
            "startDate": "2016/11/01"
        }
    };
    var companyDataUpdate = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "data": {
            "id": 1,
            "name": "TestUpdated",
            "startDate": "2016/11/01",
            "startDt": 1477947600000,
            "new": false,
            "endDate": "2016/11/01",
            "endDt": 1477947600000
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
    var companyDataDeleted = {
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
    beforeEach(module('core.companySrv'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _CompanySrv_) {
        $httpBackend = _$httpBackend_;

        CompanySrv = _CompanySrv_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should find company data by id', function () {
        $httpBackend.expectGET('/api/company/1').respond(companyData);
        var company = CompanySrv.get({companyId: 1});

        expect(company).toEqual({});

        $httpBackend.flush();
        expect(company).toEqual(companyData);
    });

    it('should update company data by id', function () {
        $httpBackend.expectPUT('/api/company/1').respond(companyDataUpdate);
        var company = CompanySrv.update({companyId: 1}, companyDataUpdate.data);

        expect(company).toEqual(companyDataUpdate.data);

        $httpBackend.flush();
        expect(company).toEqual(companyDataUpdate);
    });

    it('should save company data', function () {
        $httpBackend.expectPOST('/api/company').respond(companyDataUpdate);
        var company = CompanySrv.save(companyDataUpdate.data);

        expect(company).toEqual(companyDataUpdate.data);

        $httpBackend.flush();
        expect(company).toEqual(companyDataUpdate);
    });

    it('should delete company data by id', function () {
        $httpBackend.expectDELETE('/api/company/1').respond(companyDataDeleted);
        var company = CompanySrv.delete({companyId: 1});

        expect(company).toEqual({});

        $httpBackend.flush();
        expect(company).toEqual(companyDataDeleted);
    });

});