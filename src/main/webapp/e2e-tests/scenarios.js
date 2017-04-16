describe('Skills Application', function () {

    describe('personList', function () {

        beforeEach(function () {
            browser.get('index.html');
        });

        it('should filter the person list as a user types into the search box', function () {
            var personList = element.all(by.repeater('person in $ctrl.persons'));
            var query = element(by.model('$ctrl.query'));

            expect(personList.count()).toBeGreaterThan(0);

            query.sendKeys('Dima');
            expect(personList.count()).toBe(1);

            query.clear();
            query.sendKeys('Kate');
            expect(personList.count()).toBe(1);
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

                queryField.sendKeys('a');   // Let's narrow the dataset to make the assertions shorter

                expect(getNames()).toEqual([
                    'DIMA',
                    'KATE'
                ]);

                nameOption.click();

                expect(getNames()).toEqual([
                    'DIMA',
                    'KATE'
                ]);
            }
        );
    });

});