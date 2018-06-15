'use strict';

describe('RowSrv', function () {
    var $httpBackend;
    var RowSrv;
    var rowsData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "data": [{"id": 1, "name": "Programming Languages", "new": false}, {
            "id": 2,
            "name": "Web Technologies",
            "new": false
        }, {"id": 3, "name": "Application Servers", "new": false}, {
            "id": 4,
            "name": "Databases",
            "new": false
        }, {"id": 5, "name": "Operating Systems", "new": false}, {"id": 6, "name": "Other Skills", "new": false}],
        "first": true,
        "last": true,
        "totalPages": 1,
        "totalElements": 6,
        "size": 20,
        "number": 0,
        "numberOfElements": 6,
        "sort": null
    };

    var rowData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "total": null,
        "data": {"id": 1, "name": "Programming Languages", "new": false}
    };

    // Add a custom equality tester before each test
    beforeEach(function () {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `PersonFind` service before each test
    beforeEach(module('core.rowSrv'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _RowSrv_) {
        $httpBackend = _$httpBackend_;

        RowSrv = _RowSrv_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should find rows data', function () {
        $httpBackend.expectGET('/api/row').respond(rowsData);
        var rows = RowSrv.get();

        expect(rows).toEqual({});

        $httpBackend.flush();
        expect(rows).toEqual(rowsData);
    });

    it('should find row data by id', function () {
        $httpBackend.expectGET('/api/row/1').respond(rowData);
        var row = RowSrv.get({rowId: 1});

        expect(row).toEqual({});

        $httpBackend.flush();
        expect(row).toEqual(rowData);
    });

});