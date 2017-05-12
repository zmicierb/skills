'use strict';

angular.module('core.positionSrv').factory('PositionSrv', ['$resource',
    function ($resource) {
        return $resource('/api/position/:personId', {}, {});
    }
]);