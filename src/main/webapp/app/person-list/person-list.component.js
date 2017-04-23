'use strict';

angular.module('skillsApp').component('personList', {
    templateUrl: 'person-list/person-list.template.html',
    controller: ['Person',
        function PersonListController(Person) {
            var self = this;
            var persons = Person.query(function () {
                self.persons = persons.data;
            });
            this.orderProp = 'id';
        }]
});
