'use strict';

angular.module('core.personSkillSrv').factory('PersonSkillSrv', ['$resource',
    function ($resource) {
        return $resource('/api/skills/person/:personId', {}, {
            query: {
                method: 'GET'
                , isArray: false
            }
        });
    }
]);