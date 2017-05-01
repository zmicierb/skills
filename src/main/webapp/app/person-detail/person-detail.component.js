'use strict';

angular.module('skillsApp').component('personDetail', {
    templateUrl: 'person-detail/person-detail.template.html',
    controller: ['$routeParams', 'PersonSrv',
        function PersonDetailController($routeParams, PersonSrv) {
            var self = this;
            var person = PersonSrv.get({personId: $routeParams.personId}, function () {
                self.person = person.data;
            });
        }]
});