describe('Skills Application', function () {

    it('should redirect `index.html` to `index.html#!/persons', function () {
        browser.get('index.html');
        expect(browser.getLocationAbsUrl()).toBe('/persons');
    });

    describe('View: Person list', function () {

        beforeEach(function () {
            browser.get('index.html#!/persons');
        });

        it('should filter the person list as a user types into the search box', function () {
            var personList = element.all(by.repeater('person in $ctrl.persons'));
            var query = element(by.model('$ctrl.query'));
            var findButton = element(by.id("findId"));

            expect(personList.count()).toBeGreaterThan(0);

            query.sendKeys('Dzmitry');
            findButton.click();
            browser.waitForAngular();
            expect(personList.count()).toBe(1);

            query.clear();
            query.sendKeys('Kate');
            findButton.click();
            browser.waitForAngular();
            expect(personList.count()).toBe(0);
        });

        it('should render person specific links', function () {
            var query = element(by.model('$ctrl.query'));
            query.sendKeys('Dzmitry');

            element.all(by.css('.persons li a')).first().click();
            expect(browser.getLocationAbsUrl()).toBe('/persons/1');
        })
    });

    describe('View: Person detail', function () {

        beforeEach(function () {
            browser.get('index.html#!/persons/1');
        });

        it('should display person name', function () {
            expect(element(by.binding('$ctrl.person.name')).getText()).toBe('Dzmitry Barysevich');
        });

        it('should display skills', function () {
            expect(element.all(by.binding('personSkill.skill.name')).count()).toBeGreaterThan(0);
        });

        it('should display projects', function () {
            expect(element.all(by.repeater('$ctrl.personProjects')).count()).toBeGreaterThan(0);
        });

    });

});