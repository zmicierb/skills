'use strict';

describe('PersonSearchFormSrv', function () {
    var PersonSearchFormSrv;
    var testData = {
        test1: 1,
        test2: 2
    };

    beforeEach(module('core.personSearchFormSrv'));

    it('should set, get and reset data',
        inject(function (_PersonSearchFormSrv_) {
            PersonSearchFormSrv = _PersonSearchFormSrv_;
            PersonSearchFormSrv.setData(testData);
            expect(PersonSearchFormSrv.getData()).toBe(testData);
            PersonSearchFormSrv.resetData();
            expect(PersonSearchFormSrv.getData()).toEqual({});
        })
    );

});