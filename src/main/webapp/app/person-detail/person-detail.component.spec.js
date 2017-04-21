describe('personDetail', function () {

    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonDetailController', function () {
        var $httpBackend, ctrl;

        beforeEach(inject(function ($componentController, _$httpBackend_, $routeParams) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/api/person/1')
                .respond({
                    "success": true,
                    "message": "Completed successfully",
                    "errors": null,
                    "total": null,
                    "data": {"id": 1, "name": "test name", "deleted": false, "new": false}
                });

            $routeParams.personId = '1';

            ctrl = $componentController('personDetail');
        }));

        it('should fetch the person details', function () {
            expect(ctrl.person).toBeUndefined();

            $httpBackend.flush();
            expect(ctrl.person).toEqual({"id": 1, "name": "test name", "deleted": false, "new": false});
        });

    });

});