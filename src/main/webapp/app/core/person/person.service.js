'use strict';

angular.module('core.person').factory('Person', ['$resource',
    function ($resource) {
        return $resource('/api/person/:personId', {}, {
            query: {
                method: 'GET'
                , isArray: false
            }
        });
    }
]);