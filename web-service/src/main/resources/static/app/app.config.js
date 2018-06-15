'use strict';

angular.module('skillsApp').config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider.when('/persons', {
            template: '<person-list></person-list>'
        }).when('/person/:personId', {
            template: '<person-detail></person-detail>' +
            '<person-skill></person-skill>' +
            '<person-project></person-project>'
        }).otherwise('/persons');
    }
]);