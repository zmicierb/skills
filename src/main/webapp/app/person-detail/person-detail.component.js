'use strict';

angular.module('skillsApp').component('personDetail', {
    templateUrl: 'person-detail/person-detail.template.html',
    controller: ['$routeParams', 'Person',
        function PersonDetailController($http, $routeParams) {
            var self = this;
            $http.get('/api/person/' + $routeParams.personId).then(function (response) {
                self.person = response.data.data;
            });
        }
    ]
});