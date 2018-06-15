'use strict';

angular.module('core.companySrv').factory('CompanySrv', ['$resource',
    function ($resource) {
        return $resource('/api/company/:companyId', {}, {
            update: {method: 'PUT'}
        });
    }
]);