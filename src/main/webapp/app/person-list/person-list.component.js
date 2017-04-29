'use strict';

angular.module('skillsApp').component('personList', {
    templateUrl: 'person-list/person-list.template.html',
    controller: ['Person', 'PersonSearch', 'PersonSearchForm',
        function PersonListController(Person, PersonSearch, PersonSearchForm) {
            var self = this;
            var serviceFlag = false; //added due to issue with ng-model = "currentPage" while loading html-template
            var personsQuery = function (query, page, size) {
                var persons;
                if (query) {
                    persons = PersonSearch.get({query: query, page: page - 1, size: size}, function () {
                        self.persons = persons.data;
                        self.totalItems = persons.totalElements;
                    });
                } else {
                    persons = Person.query({page: page - 1, size: size}, function () {
                        self.persons = persons.data;
                        self.totalItems = persons.totalElements;
                    });
                }
            };
            if (angular.equals(PersonSearchForm.getData(), {})) {
                self.currentPage = 1;
                self.itemsPerPage = 5;
                self.maxSize = 5;
                self.query = '';
            } else {
                self.currentPage = PersonSearchForm.getData().currentPage;
                self.itemsPerPage = PersonSearchForm.getData().itemsPerPage;
                self.maxSize = PersonSearchForm.getData().maxSize;
                self.query = PersonSearchForm.getData().query;
                serviceFlag = true;
            }

            personsQuery(self.query, self.currentPage, self.itemsPerPage);

            self.pageChanged = function () {
                if (!serviceFlag) {
                    personsQuery(self.query, self.currentPage, self.itemsPerPage);
                    PersonSearchForm.setData({
                        currentPage: self.currentPage,
                        itemsPerPage: self.itemsPerPage,
                        maxSize: self.maxSize,
                        query: self.query
                    });
                } else {
                    self.currentPage = PersonSearchForm.getData().currentPage;
                    self.itemsPerPage = PersonSearchForm.getData().itemsPerPage;
                    self.maxSize = PersonSearchForm.getData().maxSize;
                    self.query = PersonSearchForm.getData().query;
                    serviceFlag = false;
                }
            };

            self.submit = function () {
                personsQuery(self.query, self.currentPage, self.itemsPerPage);
            }
        }]
});
