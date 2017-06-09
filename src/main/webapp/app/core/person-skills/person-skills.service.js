'use strict';

angular.module('core.personSkillSrv').factory('PersonSkillSrv', ['$resource',
    function ($resource) {
        return $resource('/api/person/:personId/skills', {}, {
            query: {
                method: 'GET'
                , isArray: false
            }
            , update: {method: 'PUT'}
        });
    }
]);