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
                    "data": {
                        "1": {
                            "personId": 1,
                            "rowId": 1,
                            "rowName": "Programming Languages",
                            "skills": [
                                {
                                    "skillId": 1,
                                    "skillName": "Java 6",
                                    "weight": 2
                                },
                                {
                                    "skillId": 2,
                                    "skillName": "Java 7",
                                    "weight": 1
                                }
                            ]
                        },
                        "2": {
                            "personId": 1,
                            "rowId": 2,
                            "rowName": "Web Technologies",
                            "skills": [
                                {
                                    "skillId": 7,
                                    "skillName": "Java EE 6",
                                    "weight": 1
                                },
                                {
                                    "skillId": 8,
                                    "skillName": "EJB 3.0",
                                    "weight": 2
                                }
                            ]
                        }
                    }
                });

            $routeParams.personId = '1';

            ctrl = $componentController('personSkill');
        }));

        it('should fetch the person skills', function () {
            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.models.lists).toEqual({});

            $httpBackend.flush();
            expect(ctrl.models.lists).toEqual({
                "Programming Languages": {
                    skills: [{
                        skillId: 2,
                        skillName: 'Java 7',
                        weight: 1
                    },
                        {
                            skillId: 1,
                            skillName: 'Java 6',
                            weight: 2
                        }],
                    type: 'Programming Languages'
                },
                "Web Technologies": {
                    skills: [{
                        skillId: 7,
                        skillName: 'Java EE 6',
                        weight: 1
                    },
                        {
                            skillId: 8,
                            skillName: 'EJB 3.0',
                            weight: 2
                        }],
                    type: 'Web Technologies'
                }
            })
            ;
        });

    });

});