describe('personList', function () {

    // Load the module that contains the `personList` component before each test
    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonListController', function () {

        it('should create a `persons` model', inject(function ($componentController) {
            var ctrl = $componentController('personList');

            expect(ctrl.persons.length).toBeGreaterThan(0);
        }));

    });

});