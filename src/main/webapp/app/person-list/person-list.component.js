'use strict';

// Register `personList` component, along with its associated controller and template
angular.module('skillsApp').component('personList', {
    templateUrl: 'person-list/person-list.template.html',
    controller: function PersonListController() {
        this.persons = [
            {name: "Dima"},
            {name: "Kate"}
        ];
    }
});

// angular.module("skillsApp").factory("personList", ["$http",
//     function ($http) {
//         return {
//             getPersons: function () {
//                 return $http.get("/api/person").then(function (d) {
//                     return d.data;
//                 });
//             }
//         }
//     }]
// );