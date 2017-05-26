'use strict';

angular.module('core.departmentFindSrv').factory('DepartmentFindSrv', ['$resource',
    function ($resource) {
        return $resource('/api/department/find/:query', {}, {});
    }
]);