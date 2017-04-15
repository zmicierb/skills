// 'use strict';
//
// describe('PersonController', function () {
//     var $httpBackend, $rootScope, createController, personRequestHandler;
//
//     // Set up the module
//     beforeEach(module('skillsApp'));
//
//     beforeEach(inject(function ($injector) {
//         // Set up the mock http service responses
//         $httpBackend = $injector.get('$httpBackend');
//         // backend definition common for all tests
//         personRequestHandler = $httpBackend.when('GET', '/api/person')
//             .respond({
//                 "success": true,
//                 "message": "Completed successfully",
//                 "errors": null,
//                 "total": null,
//                 "data": [{"id": 57, "name": "DIMA", "deleted": false, "new": false}, {
//                     "id": 58,
//                     "name": "KATE",
//                     "deleted": false,
//                     "new": false
//                 }]
//             });
//
//         // Get hold of a scope (i.e. the root scope)
//         $rootScope = $injector.get('$rootScope');
//         // The $controller service is used to create instances of controllers
//         var $controller = $injector.get('$controller');
//
//         createController = function () {
//             return $controller('PersonController', {'$scope': $rootScope});
//         };
//     }));
//
//
//     afterEach(function() {
//         $httpBackend.verifyNoOutstandingExpectation();
//         $httpBackend.verifyNoOutstandingRequest();
//     });
//
//
//     it('should fetch authentication token', function () {
//         $httpBackend.expectGET('/api/person');
//         var controller = createController();
//         $httpBackend.flush();
//         expect($rootScope.persons.length).toBeGreaterThan(0);
//     });
//
// });