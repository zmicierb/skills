'use strict';

angular.module('core.personSearchForm').factory('PersonSearchForm', function () {
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