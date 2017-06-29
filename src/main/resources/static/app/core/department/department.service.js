'use strict';

angular.module('core.departmentSrv').factory('DepartmentSrv', ['$resource',
    function ($resource) {
        return $resource('/api/department/:departmentId', {}, {});
    }
]);