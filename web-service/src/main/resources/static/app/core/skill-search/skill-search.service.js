'use strict';

angular.module('core.skillSearchSrv').factory('SkillSearchSrv', ['$resource',
    function ($resource) {
        return $resource('/api/search/find/:query', {}, {});
    }
]);