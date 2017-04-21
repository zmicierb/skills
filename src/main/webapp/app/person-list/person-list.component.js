'use strict';

angular.module('skillsApp').component('personList', {
    templateUrl: 'person-list/person-list.template.html',
    controller: ['$http',
        function PersonListController($http) {
            var self = this;
            self.orderProp = 'id';
            $http.get('/api/person').then(function (response) {
                self.persons = response.data.data;
            });
        }]
});
