'use strict';

angular.module('core.personProjectSrv').factory('PersonProjectSrv', ['$resource',
    function ($resource) {
        return $resource('/api/company/person/:personId', {}, {
            query: {
                method: 'GET'
                , isArray: false
            }
        });
    }
]);