'use strict';

// Define the `skillsApp` module
var skillsApp = angular.module('skillsApp', []);

// Define the `PersonController` controller on the `skillsApp` module
skillsApp.controller('PersonController', ["$scope", "$http", function PersonController($scope, $http) {

    $http.get("api/person").success(function (response) {
        $scope.persons = response.data;
    });

}]);