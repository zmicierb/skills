'use strict';

angular.module('skillsApp').component('personProject', {
    templateUrl: 'person-project/person-project.template.html',
    controller: ['$routeParams', 'PersonProjectSrv',
        function PersonDetailController($routeParams, PersonProjectSrv) {
            var self = this;
            self.editFlag = false;
            var personProjects = PersonProjectSrv.query({personId: $routeParams.personId}, function () {
                //sort skills in environment rows by weight
                personProjects.data.forEach(function (project) {
                    project.project.environmentRow.sort(function (a, b) {
                        return a.weight - b.weight;
                    });
                });
                self.personProjects = personProjects.data;
            });

            self.toggleEditFlag = function () {
                self.editFlag = true;
            }
        }]
});