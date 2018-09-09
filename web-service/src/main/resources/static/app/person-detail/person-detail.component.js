'use strict';

angular.module('skillsApp').component('personDetail', {
    templateUrl: 'person-detail/person-detail.template.html',
    bindings: {
        new: '<'
    },
    controller: ['$http', '$filter', '$location', '$routeParams', 'PersonSrv',
        function PersonDetailController($http, $filter, $location, $routeParams, PersonSrv) {
            let self = this;
            self.isNew = !(self.new == undefined || self.new === false);
            const getPerson = function () {
                const person = PersonSrv.get({personId: $routeParams.personId}, function () {
                    self.person = person.data;
                    self.person.dt = Date.parse(self.person.birthDate);
                });
            };

            if (!self.isNew)
                getPerson();

            if (self.isNew)
                self.editFlag = true;
            else
                self.editFlag = false;

            self.toggleEditFlag = function () {
                self.editFlag = true;
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
                self.person.birthDate = $filter('date')(self.person.dt, "yyyy/MM/dd");
                if (!self.isNew)
                    PersonSrv.save(self.person).$promise.then(function (response) {
                        self.person = response.data;
                        self.person.dt = Date.parse(self.person.birthDate);
                        form.$setPristine();
                    });
                else
                    PersonSrv.save(self.person).$promise.then(function (response) {
                        $location.path("/person/" + response.data.id);
                    });
            };

            self.cancel = function () {
                self.editFlag = false;
            };

            self.reset = function (form) {
                getPerson();
                form.$setPristine();
            };

        }]
});