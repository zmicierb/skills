'use strict';

angular.module('core.personSearch').factory('PersonSearch', function () {
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