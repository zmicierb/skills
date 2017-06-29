'use strict';

angular.module('core.personProjectSrv').factory('PersonProjectSrv', ['$resource',
    function ($resource) {
        return $resource('/api/person/:personId/projects', {}, {
            query: {
                method: 'GET'
                , isArray: false
            }
        });
    }
]);