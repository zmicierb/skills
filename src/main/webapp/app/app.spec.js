'use strict';

describe('PersonController', function () {

    beforeEach(module('skillsApp'));

    it('should create a `persons` model', inject(function ($controller) {
        var scope = {};
        var ctrl = $controller('PersonController', {$scope: scope});

        expect(scope.persons.length).toBe(3);
    }));

});