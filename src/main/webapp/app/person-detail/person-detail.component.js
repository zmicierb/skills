'use strict';

angular.module('skillsApp').component('personDetail', {
    templateUrl: 'person-detail/person-detail.template.html',
    controller: ['$routeParams', 'Person',
        function PersonDetailController($routeParams, Person) {
            var self = this;
            var person = Person.get({personId: $routeParams.personId}, function () {
                self.person = person.data;
            });
        }]
});