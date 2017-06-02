'use strict';

angular.module('core.personSrv').factory('PersonSrv', ['$resource',
    function ($resource) {
        return $resource('/api/person/:personId', {}, {
            query: {
                method: 'GET'
                , isArray: false
            },
            update: {method: 'PUT'}
        });
    }
]);