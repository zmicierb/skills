'use strict';

angular.module('core.rowSrv').factory('RowSrv', ['$resource',
    function ($resource) {
        return $resource('/api/row/:rowId', {}, {});
    }
]);