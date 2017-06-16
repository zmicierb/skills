'use strict';

angular.module('core.projectSrv').factory('ProjectSrv', ['$resource',
    function ($resource) {
        return $resource('/api/project/:projectId', {}, {
            update: {method: 'PUT'}
        });
    }
]);