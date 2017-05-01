'use strict';

angular.module('skillsApp').component('personList', {
    templateUrl: 'person-list/person-list.template.html',
    controller: ['PersonSrv', 'PersonFindSrv', 'PersonSearchFormSrv',
        function PersonListController(PersonSrv, PersonFindSrv, PersonSearchFormSrv) {
            var self = this;
            var serviceFlag = false; //added due to issue with ng-model = "currentPage" while loading html-template
            var personsQuery = function (query, page, size) {
                var persons;
                if (query) {
                    persons = PersonFindSrv.get({query: query, page: page - 1, size: size}, function () {
                        self.persons = persons.data;
                        self.totalItems = persons.totalElements;
                    });
                } else {
                    persons = PersonSrv.query({page: page - 1, size: size}, function () {
                        self.persons = persons.data;
                        self.totalItems = persons.totalElements;
                    });
                }
            };
            if (angular.equals(PersonSearchFormSrv.getData(), {})) {
                self.currentPage = 1;
                self.itemsPerPage = 5;
                self.maxSize = 5;
                self.query = '';
            } else {
                self.currentPage = PersonSearchFormSrv.getData().currentPage;
                self.itemsPerPage = PersonSearchFormSrv.getData().itemsPerPage;
                self.maxSize = PersonSearchFormSrv.getData().maxSize;
                self.query = PersonSearchFormSrv.getData().query;
                serviceFlag = true;
            }

            personsQuery(self.query, self.currentPage, self.itemsPerPage);

            self.pageChanged = function () {
                if (!serviceFlag) {
                    personsQuery(self.query, self.currentPage, self.itemsPerPage);
                    PersonSearchFormSrv.setData({
                        currentPage: self.currentPage,
                        itemsPerPage: self.itemsPerPage,
                        maxSize: self.maxSize,
                        query: self.query
                    });
                } else {
                    self.currentPage = PersonSearchFormSrv.getData().currentPage;
                    self.itemsPerPage = PersonSearchFormSrv.getData().itemsPerPage;
                    self.maxSize = PersonSearchFormSrv.getData().maxSize;
                    self.query = PersonSearchFormSrv.getData().query;
                    serviceFlag = false;
                }
            };

            self.submit = function () {
                personsQuery(self.query, self.currentPage, self.itemsPerPage);
            }
        }]
});
