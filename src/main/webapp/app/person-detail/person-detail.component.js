'use strict';

angular.module('skillsApp').component('personDetail', {
    templateUrl: 'person-detail/person-detail.template.html',
    controller: ['$http', '$filter', '$routeParams', 'PersonSrv', 'PositionSrv', 'PositionFindSrv', 'DepartmentSrv', 'DepartmentFindSrv',
        function PersonDetailController($http, $filter, $routeParams, PersonSrv, PositionSrv, PositionFindSrv, DepartmentSrv, DepartmentFindSrv) {
            var self = this;
            self.editFlag = false;
            var person = PersonSrv.get({personId: $routeParams.personId}, function () {
                self.person = person.data;
                self.person.dt = new Date(self.person.birthDate);
            });

            self.toggleEditFlag = function () {
                self.editFlag = true;
            };

            self.getPosition = function (val) {
                if (val) {
                    return PositionFindSrv.get({query: val}).$promise.then(function (response) {
                            return response.data;
                        }
                    );
                } else {
                    return PositionSrv.get().$promise.then(function (response) {
                            return response.data;
                        }
                    );
                }
            };

            self.getDepartment = function (val) {
                if (val) {
                    return DepartmentFindSrv.get({query: val}).$promise.then(function (response) {
                            return response.data;
                        }
                    );
                } else {
                    return DepartmentSrv.get().$promise.then(function (response) {
                            return response.data;
                        }
                    );
                }
            };

            self.clear = function () {
                self.person.dt = null;
            };

            self.dateOptions = {
                formatYear: 'yyyy',
                startingDay: 1
            };

            self.popup = {
                opened: false
            };

            self.open = function () {
                self.popup.opened = true;
            };

            self.submit = function (form) {
                self.person.birthDate = $filter('date')(self.person.dt, "yyyy-MM-dd");
                console.log(self.person);
                PersonSrv.update({personId: $routeParams.personId}, self.person).$promise.then(function (response) {
                    self.person = response.data;
                    self.person.dt = new Date(self.person.birthDate);
                });
                form.$setPristine();
            };

            self.cancel = function () {
                self.editFlag = false;
            };

            self.reset = function (form) {
                person = PersonSrv.get({personId: $routeParams.personId}, function () {
                    self.person = person.data;
                    self.person.dt = new Date(self.person.birthDate);
                });
                form.$setPristine();
            };

        }]
});