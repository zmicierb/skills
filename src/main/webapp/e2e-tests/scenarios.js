describe('Skills Application', function () {

    it('should redirect `index.html` to `index.html#!/persons', function () {
        browser.get('index.html');
        expect(browser.getLocationAbsUrl()).toBe('/persons');
    });

    describe('View: Person list', function () {

        beforeEach(function () {
            browser.get('index.html#!/phones');
        });

        it('should filter the person list as a user types into the search box', function () {
            var personList = element.all(by.repeater('person in $ctrl.persons'));
            var query = element(by.model('$ctrl.query'));

            expect(personList.count()).toBeGreaterThan(0);

            query.sendKeys('Dima');
            expect(personList.count()).toBe(1);

            query.clear();
            query.sendKeys('Kate');
            expect(personList.count()).toBe(0);
        });

        it('should be possible to control person order via drop-down menu', function () {
                var queryField = element(by.model('$ctrl.query'));
                var orderSelect = element(by.model('$ctrl.orderProp'));
                var nameOption = orderSelect.element(by.css('option[value="name"]'));
                var personNameColumn = element.all(by.repeater('person in $ctrl.persons').column('person.name'));

                function getNames() {
                    return personNameColumn.map(function (elem) {
                        return elem.getText();
                    });
                };

            queryField.sendKeys('j');   // Let's narrow the dataset to make the assertions shorter

                expect(getNames()).toEqual([
                    'jlong',
                    'jhoeller'
                ]);

                nameOption.click();

                expect(getNames()).toEqual([
                    'jhoeller',
                    'jlong'
                ]);
            }
        );

        it('should render person specific links', function () {
            var query = element(by.model('$ctrl.query'));
            query.sendKeys('Dima');

            element.all(by.css('.persons li a')).first().click();
            expect(browser.getLocationAbsUrl()).toBe('/persons/1');
        })
    });

    describe('View: Person detail', function () {

        beforeEach(function () {
            browser.get('index.html#!/persons/1');
        });

        it('should display placeholder page with `personId`', function () {
            expect(element(by.binding('$ctrl.person.name')).getText()).toBe('Dima');
        });

    });

});