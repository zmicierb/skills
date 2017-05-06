'use strict';

angular.module('skillsApp').component('personProject', {
    templateUrl: 'person-project/person-project.template.html',
    controller: ['$routeParams', 'PersonProjectSrv',
        function PersonDetailController($routeParams, PersonProjectSrv) {
            var self = this;
            var personProjects = PersonProjectSrv.query({personId: $routeParams.personId}, function () {
                self.personProjects = personProjects.data;
            });
        }]
});