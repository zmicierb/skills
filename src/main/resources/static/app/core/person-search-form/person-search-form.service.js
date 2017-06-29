'use strict';

angular.module('core.personSearchFormSrv').factory('PersonSearchFormSrv', function () {
        var formData = {};

        return {
            getData: function () {
                return formData;
            }
            , setData: function (newFormData) {
                formData = newFormData
            }
            , resetData: function () {
                formData = {};
            }
        };
    }
);