// 'use strict';
//
// describe('PersonFindSrv', function () {
//     var $httpBackend;
//     var PersonFindSrv;
//     var personData = {
//         "success": true,
//         "message": "Completed successfully",
//         "errors": null,
//         "total": null,
//         "data": {name: 'TestName'}
//     };
//
//     // Add a custom equality tester before each test
//     beforeEach(function () {
//         jasmine.addCustomEqualityTester(angular.equals);
//     });
//
//     // Load the module that contains the `PersonFind` service before each test
//     beforeEach(module('core.personFindSrv'));
//
//     // Instantiate the service and "train" `$httpBackend` before each test
//     beforeEach(inject(function (_$httpBackend_, _PersonFindSrv_) {
//         $httpBackend = _$httpBackend_;
//         $httpBackend.expectGET('/api/person/find/TestName?page=0&size=5').respond(personData);
//
//         PersonFindSrv = _PersonFindSrv_;
//     }));
//
//     // Verify that there are no outstanding expectations or requests after each test
//     afterEach(function () {
//         $httpBackend.verifyNoOutstandingExpectation();
//         $httpBackend.verifyNoOutstandingRequest();
//     });
//
//     it('should find person data by name', function () {
//         var person = PersonFindSrv.get({query: "TestName", page: 0, size: 5});
//
//         expect(person).toEqual({});
//
//         $httpBackend.flush();
//         expect(person).toEqual(personData);
//     });
//
// });