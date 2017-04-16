'use strict';

// Register `personList` component, along with its associated controller and template
angular.module('skillsApp').component('personList', {
    templateUrl: 'person-list/person-list.template.html',
    controller: ['$http',
        function PersonListController($http) {
            var self = this;
            self.orderProp = 'id';
            $http.get('/api/person').then(function (response) {
                self.persons = response.data.data;
            });
        }
    ]
});
