'use strict';

angular.module('skillsApp').component('personDetail', {
    templateUrl: 'person-detail/person-detail.template.html',
    controller: ['$http', '$routeParams', 'PersonSrv', 'PositionSrv', 'PositionFindSrv',
        function PersonDetailController($http, $routeParams, PersonSrv, PositionSrv, PositionFindSrv) {
            var self = this;
            self.editFlag = false;
            var person = PersonSrv.get({personId: $routeParams.personId}, function () {
                self.person = person.data;
            });

            self.toggleEditFlag = function () {
                self.editFlag = true;
            };

            self.getPosition = function (val) {
                return $http.get('/api/position/find/' + val).then(function (response) {
                    return response.data.data;
                });
                // var output = [];
                // var positions;
                // // return ['Alabama', 'Alaska', 'Arizona', 'Arkansas',
                // //     'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida',
                // //     'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa',
                // //     'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland',
                // //     'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi',
                // //     'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
                // //     'New Jersey', 'New Mexico', 'New York', 'North Dakota',
                // //     'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania',
                // //     'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee',
                // //     'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington',
                // //     'West Virginia', 'Wisconsin', 'Wyoming'];
                // if (val) {
                //     // return ['Alabama', 'Alaska', 'Arizona'];
                //     positions = PositionFindSrv.get({query: val, page: 0, size: 10}, function () {
                //         output = positions.data;
                //         return output;
                //     });
                // } else {
                //     PositionSrv.get({page: 0, size: 10}).$promise.then(function (response) {
                //         return response.data;
                //     });
                // }
            };
        }]
});