// 'use strict';
//
// describe('DepartmentSrv', function () {
//     var $httpBackend;
//     var DepartmentSrv;
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
//     var departmentData = {
//         "success": true,
//         "message": "Completed successfully",
//         "errors": null,
//         "total": null,
//         "data": {id: 1, name: "Dept1", new: false}
//     };
//
//     // Add a custom equality tester before each test
//     beforeEach(function () {
//         jasmine.addCustomEqualityTester(angular.equals);
//     });
//
//     // Load the module that contains the `PersonFind` service before each test
//     beforeEach(module('core.departmentSrv'));
//
//     // Instantiate the service and "train" `$httpBackend` before each test
//     beforeEach(inject(function (_$httpBackend_, _DepartmentSrv_) {
//         $httpBackend = _$httpBackend_;
//
//         DepartmentSrv = _DepartmentSrv_;
//     }));
//
//     // Verify that there are no outstanding expectations or requests after each test
//     afterEach(function () {
//         $httpBackend.verifyNoOutstandingExpectation();
//         $httpBackend.verifyNoOutstandingRequest();
//     });
//
//     it('should find departments data', function () {
//         $httpBackend.expectGET('/api/department').respond(departmentsData);
//         var departments = DepartmentSrv.get();
//
//         expect(departments).toEqual({});
//
//         $httpBackend.flush();
//         expect(departments).toEqual(departmentsData);
//     });
//
//     it('should find department data by id', function () {
//         $httpBackend.expectGET('/api/department/1').respond(departmentData);
//         var department = DepartmentSrv.get({departmentId: 1});
//
//         expect(department).toEqual({});
//
//         $httpBackend.flush();
//         expect(department).toEqual(departmentData);
//     });
//
// });