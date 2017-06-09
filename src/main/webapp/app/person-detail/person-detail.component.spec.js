describe('personDetail', function () {

    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonDetailController', function () {
        var $httpBackend, ctrl, $scope;
        var updateName = "testName";
        var personData = {
            "id": 1,
            "name": "test name",
            "position": {"id": 1, "name": "Java Developer", "new": false},
            "department": {"id": 1, "name": "Application Development department", "new": false},
            "deleted": false,
            "new": false,
            "birthDate": "1987/06/20",
            "dt": Date.parse("1987/06/20")
        };

        var responseFetchData = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "total": null,
            "data": personData
        };

        var responseUpdateData = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": {
                "id": 1,
                "name": updateName,
                "position": {"id": 1, "name": "Java Developer", "new": false},
                "department": {"id": 1, "name": "Application Development department", "new": false},
                "birthDate": "1987/06/20",
                "deleted": false,
                "new": false,
                "dt": Date.parse("1987/06/20")
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

        beforeEach(inject(function ($componentController, _$httpBackend_, $routeParams, $rootScope, $controller, formDirective) {
            $httpBackend = _$httpBackend_;
            $scope = $rootScope.$new();

            $routeParams.personId = '1';

            ctrl = $componentController('personDetail', {$scope: $scope});

            //mock form
            ctrl.form = $controller(formDirective[0].controller, {
                $scope: $scope,
                $element: angular.element("<form></form>"),
                $attrs: {}
            });
        }));

        it('should fetch person details', function () {
            $httpBackend.expectGET('/api/person/1').respond(responseFetchData);
            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.person).toBeUndefined();

            $httpBackend.flush();
            expect(ctrl.person).toEqual(personData);
        });

        it('should update person details', function () {

            $httpBackend.expectGET('/api/person/1').respond(responseFetchData);

            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.person).toBeUndefined();

            $httpBackend.flush();
            $httpBackend.expectPUT('/api/person/1').respond(responseUpdateData);

            ctrl.person.name = updateName;
            ctrl.submit(ctrl.form);
            $httpBackend.flush();
            expect(ctrl.person).toEqual(responseUpdateData.data);
        });

    });

});