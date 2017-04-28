'use strict';

angular.module('skillsApp').component('personList', {
    templateUrl: 'person-list/person-list.template.html',
    controller: ['Person', 'PersonSearch',
        function PersonListController(Person, PersonSearch) {
            var self = this;
            var serviceFlag = false; //added due to issue with ng-model = "currentPage" while loading html-template
            var personsQuery = function (page, size) {
                var persons = Person.query({page: page - 1, size: size}, function () {
                    self.persons = persons.data;
                    self.totalItems = persons.totalElements;
                });
            };
            if (angular.equals(PersonSearch.getData(), {})) {
                self.currentPage = 1;
                self.itemsPerPage = 5;
                self.maxSize = 5;
            } else {
                self.currentPage = PersonSearch.getData().currentPage;
                self.itemsPerPage = PersonSearch.getData().itemsPerPage;
                self.maxSize = PersonSearch.getData().maxSize;
                serviceFlag = true;
            }

            personsQuery(self.currentPage, self.itemsPerPage);

            self.pageChanged = function () {
                if (!serviceFlag) {
                    personsQuery(self.currentPage, self.itemsPerPage);
                    PersonSearch.setData({
                        currentPage: self.currentPage,
                        itemsPerPage: self.itemsPerPage,
                        maxSize: self.maxSize
                    });
                } else {
                    self.currentPage = PersonSearch.getData().currentPage;
                    self.itemsPerPage = PersonSearch.getData().itemsPerPage;
                    self.maxSize = PersonSearch.getData().maxSize;
                    serviceFlag = false;
                }
            };
        }]
});
