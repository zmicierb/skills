'use strict';

angular.module('core.personCompanySrv').factory('PersonCompanySrv', ['$resource',
    function ($resource) {
        return $resource('/api/company/person/:personId', {}, {
            query: {
                method: 'GET'
                , isArray: false
            }
        });
    }
]);