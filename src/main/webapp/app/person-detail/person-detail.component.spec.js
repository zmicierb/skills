describe('personDetail', function () {

    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonDetailController', function () {
        var $httpBackend, ctrl;
        var personData = {
            "id": 1,
            "name": "test name",
            "deleted": false,
            "new": false,
            "birthDate": "1987-06-20",
            "dt": new Date("1987-06-20")
        };

        beforeEach(inject(function ($componentController, _$httpBackend_, $routeParams) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/api/person/1')
                .respond({
                    "success": true,
                    "message": "Completed successfully",
                    "errors": null,
                    "total": null,
                    "data": personData
                });

            $routeParams.personId = '1';

            ctrl = $componentController('personDetail');
        }));

        it('should fetch the person details', function () {
            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.person).toBeUndefined();

            $httpBackend.flush();
            expect(ctrl.person).toEqual(personData);
        });

    });

});