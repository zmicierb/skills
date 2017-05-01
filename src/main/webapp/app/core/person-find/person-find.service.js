'use strict';

angular.module('core.personFindSrv').factory('PersonFindSrv', ['$resource',
    function ($resource) {
        return $resource('/api/person/find/:query', {}, {});
    }
]);