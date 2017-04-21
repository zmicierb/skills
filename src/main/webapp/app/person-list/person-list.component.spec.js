describe('personList', function () {

    // Load the module that contains the `personList` component before each test
    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonListController', function () {
        var $httpBackend, ctrl;

        beforeEach(inject(function ($componentController, _$httpBackend_) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/api/person')
                .respond({
                    "success": true,
                    "message": "Completed successfully",
                    "errors": null,
                    "total": null,
                    "data": [{"id": 57, "name": "DIMA", "deleted": false, "new": false},
                        {"id": 58, "name": "KATE", "deleted": false, "new": false}]
                });
            ctrl = $componentController('personList');
        }));

        it('should create a `persons` model', function () {
            expect(ctrl.persons).toBeUndefined();
            $httpBackend.flush();
            expect(ctrl.persons.length).toBeGreaterThan(0);
        });

        it('should set a default value for the `orderProp` model', function () {
            expect(ctrl.orderProp).toBe('id');
        });

    });
});