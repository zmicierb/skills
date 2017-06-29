'use strict';

angular.module('core.skillFindSrv').factory('SkillFindSrv', ['$resource',
    function ($resource) {
        return $resource('/api/skill/find/:query', {}, {});
    }
]);