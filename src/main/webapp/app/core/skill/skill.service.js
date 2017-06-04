'use strict';

angular.module('core.skillSrv').factory('SkillSrv', ['$resource',
    function ($resource) {
        return $resource('/api/skill/:skillId', {}, {});
    }
]);