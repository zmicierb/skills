'use strict';

describe('PositionSrv', function () {
    var $httpBackend;
    var PositionSrv;
    var positionsData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "total": null,
        "data": [{id: 1, name: "Position1", new: false},
            {id: 2, name: "Position2", new: false}
        ]
    };

    var positionData = {
        "success": true,
        "message": "Completed successfully",
        "errors": null,
        "total": null,
        "data": {id: 1, name: "Position1", new: false}
    };

    // Add a custom equality tester before each test
    beforeEach(function () {
        jasmine.addCustomEqualityTester(angular.equals);
    });

    // Load the module that contains the `PersonFind` service before each test
    beforeEach(module('core.positionSrv'));

    // Instantiate the service and "train" `$httpBackend` before each test
    beforeEach(inject(function (_$httpBackend_, _PositionSrv_) {
        $httpBackend = _$httpBackend_;

        PositionSrv = _PositionSrv_;
    }));

    // Verify that there are no outstanding expectations or requests after each test
    afterEach(function () {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('should find positions data', function () {
        $httpBackend.expectGET('/api/position').respond(positionsData);
        var positions = PositionSrv.get();

        expect(positions).toEqual({});

        $httpBackend.flush();
        expect(positions).toEqual(positionsData);
    });

    it('should find position data by id', function () {
        $httpBackend.expectGET('/api/position/1').respond(positionData);
        var position = PositionSrv.get({positionId: 1});

        expect(position).toEqual({});

        $httpBackend.flush();
        expect(position).toEqual(positionData);
    });

});