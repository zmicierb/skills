// 'use strict';
//
// describe('DepartmentFindSrv', function () {
//     var $httpBackend;
//     var DepartmentFindSrv;
//     var departmentsData = {
//         "success": true,
//         "message": "Completed successfully",
//         "errors": null,
//         "total": null,
//         "data": [{id: 1, name: "Dept1", new: false},
//             {id: 2, name: "Dept2", new: false}
//         ]
//     };
//
//     // Add a custom equality tester before each test
//     beforeEach(function () {
//         jasmine.addCustomEqualityTester(angular.equals);
//     });
//
//     // Load the module that contains the `PersonFind` service before each test
//     beforeEach(module('core.departmentFindSrv'));
//
//     // Instantiate the service and "train" `$httpBackend` before each test
//     beforeEach(inject(function (_$httpBackend_, _DepartmentFindSrv_) {
//         $httpBackend = _$httpBackend_;
//         $httpBackend.expectGET('/api/department/find/Dept').respond(departmentsData);
//
//         DepartmentFindSrv = _DepartmentFindSrv_;
//     }));
//
//     // Verify that there are no outstanding expectations or requests after each test
//     afterEach(function () {
//         $httpBackend.verifyNoOutstandingExpectation();
//         $httpBackend.verifyNoOutstandingRequest();
//     });
//
//     it('should find department by name', function () {
//         var departments = DepartmentFindSrv.get({query: "Dept"});
//
//         expect(departments).toEqual({});
//
//         $httpBackend.flush();
//         expect(departments).toEqual(departmentsData);
//     });
//
// });