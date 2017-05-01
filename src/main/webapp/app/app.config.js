'use strict';

angular.module('skillsApp').config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.when('/persons', {
            template: '<person-list></person-list>'
        }).when('/persons/:personId', {
            template: '<person-detail></person-detail>' +
            '<person-skill></person-skill>>'
        }).otherwise('/persons');
    }
]);