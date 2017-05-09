describe('personSkill', function () {

    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonSkillController', function () {
        var $httpBackend, ctrl;

        beforeEach(inject(function ($componentController, _$httpBackend_, $routeParams) {
            $httpBackend = _$httpBackend_;
            $httpBackend.expectGET('/api/person/1/skills')
                .respond({
                    "success": true,
                    "message": "Completed successfully",
                    "errors": null,
                    "total": null,
                    "data": [{
                        "id": 1,
                        "personId": 1,
                        "skillId": 1,
                        "skill": {"id": 1, "name": "Java 6", "new": false},
                        "rowId": 1,
                        "row": {"id": 1, "name": "Programming Languages", "new": false},
                        "weight": 1,
                        "new": false
                    }, {
                        "id": 2,
                        "personId": 1,
                        "skillId": 2,
                        "skill": {"id": 2, "name": "Java 7", "new": false},
                        "rowId": 1,
                        "row": {"id": 1, "name": "Programming Languages", "new": false},
                        "weight": 2,
                        "new": false
                    }]
                });

            $routeParams.personId = '1';

            ctrl = $componentController('personSkill');
        }));

        it('should fetch the person skills', function () {
            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.personSkills).toBeUndefined();

            $httpBackend.flush();
            expect(ctrl.personSkills.length).toEqual(2);
        });

    });

});