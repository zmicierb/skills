describe('personSkill', function () {

    beforeEach(module('skillsApp'));

    // Test the controller
    describe('PersonSkillController', function () {
        var $httpBackend, ctrl, $scope;
        var skillsResponse = {
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
                            "position": 2
                        },
                        {
                            "skillId": 2,
                            "skillName": "Java 7",
                            "position": 1
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
                            "position": 1
                        },
                        {
                            "skillId": 8,
                            "skillName": "EJB 3.0",
                            "position": 2
                        }
                    ]
                }
            }
        };
        var rowsResponse = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": [{"id": 1, "name": "Programming Languages", "new": false}, {
                "id": 2,
                "name": "Web Technologies",
                "new": false
            }, {"id": 3, "name": "Application Servers", "new": false}, {
                "id": 4,
                "name": "Databases",
                "new": false
            }, {"id": 5, "name": "Operating Systems", "new": false}, {"id": 6, "name": "Other Skills", "new": false}],
            "first": true,
            "last": true,
            "totalPages": 1,
            "totalElements": 6,
            "size": 20,
            "number": 0,
            "numberOfElements": 6,
            "sort": null
        };
        var modelsLists = {
            "Programming Languages": {
                newSkill: '', rowId: 1, addSkillFlag: false,
                skills: [{
                    skillId: 2,
                    skillName: 'Java 7',
                    position: 1
                },
                    {
                        skillId: 1,
                        skillName: 'Java 6',
                        position: 2
                    }],
                type: 'Programming Languages'
            },
            "Web Technologies": {
                newSkill: '', rowId: 2, addSkillFlag: false,
                skills: [{
                    skillId: 7,
                    skillName: 'Java EE 6',
                    position: 1
                },
                    {
                        skillId: 8,
                        skillName: 'EJB 3.0',
                        position: 2
                    }],
                type: 'Web Technologies'
            }
        };
        var updateResponse = {
            "success": true,
            "message": "Completed successfully",
            "errors": null,
            "data": null,
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

            ctrl = $componentController('personSkill', {$scope: $scope});

            //mock form
            ctrl.form = $controller(formDirective[0].controller, {
                $scope: $scope,
                $element: angular.element("<form></form>"),
                $attrs: {}
            });

        }));

        it('should fetch the person skills', function () {
            $httpBackend.expectGET('/api/person/1/skills').respond(skillsResponse);
            $httpBackend.expectGET('/api/row').respond(rowsResponse);

            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.models.lists).toEqual({});

            $httpBackend.flush();
            expect(ctrl.models.lists).toEqual(modelsLists);
        });

        it('should update person skills', function () {
            $httpBackend.expectGET('/api/person/1/skills').respond(skillsResponse);
            $httpBackend.expectGET('/api/row').respond(rowsResponse);

            jasmine.addCustomEqualityTester(angular.equals);

            expect(ctrl.models.lists).toEqual({});

            $httpBackend.flush();
            $httpBackend.expectPUT('/api/person/1/skills').respond(updateResponse);
            $httpBackend.expectGET('/api/person/1/skills').respond(skillsResponse);
            $httpBackend.expectGET('/api/row').respond(rowsResponse);

            ctrl.submit(ctrl.form);
            $httpBackend.flush();
            expect(ctrl.models.lists).toEqual(modelsLists);
        });

    });

});