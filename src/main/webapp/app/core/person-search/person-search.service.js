'use strict';

angular.module('core.personSearch').factory('PersonSearch', ['$resource',
    function ($resource) {
        return $resource('/api/person/find/name=:query', {}, {});
    }
]);