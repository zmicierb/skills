describe('personList', function () {

    // Load the module that contains the `personList` component before each test
    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonListController', function () {
        var $httpBackend, ctrl;

        beforeEach(inject(function ($componentController, _$httpBackend_) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/api/person?page=0&size=5')
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

        it('should set a default value for the `query`', function () {
            expect(ctrl.query).toBe('');
        });

        it('should set a default value for the `page`', function () {
            expect(ctrl.currentPage - 1).toBe(0);
        });

        it('should set a default value for the `size`', function () {
            expect(ctrl.maxSize).toBe(5);
        });

        it('should create a `persons` model', function () {
            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.persons).toBeUndefined();
            $httpBackend.flush();
            expect(ctrl.persons.length).toBeGreaterThan(0);
        });

        it('should find person by name', function () {
            jasmine.addCustomEqualityTester(angular.equals);

            ctrl.query = 'di';
            $httpBackend.expectGET('/api/person/find/name=di?page=0&size=5')
                .respond({
                    "success": true,
                    "message": "Completed successfully",
                    "errors": null,
                    "total": null,
                    "data": [{"id": 57, "name": "DIMA", "deleted": false, "new": false}]
                });
            ctrl.submit();
            $httpBackend.flush();
            expect(ctrl.persons.length).toBe(1);
        });

    });
});