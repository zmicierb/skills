'use strict';

angular.module('core.positionFindSrv').factory('PositionFindSrv', ['$resource',
    function ($resource) {
        return $resource('/api/position/find/:query', {}, {});
    }
]);