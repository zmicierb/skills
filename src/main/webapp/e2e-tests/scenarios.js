describe('Skills Application', function () {

    it('should redirect `index.html` to `index.html#!/persons', function () {
        browser.get('index.html');
        expect(browser.getCurrentUrl()).toBe('http://localhost:8080/app/index.html#!/persons');
    });

    describe('View: Person list', function () {

        beforeEach(function () {
            browser.get('index.html#!/persons');
        });

        it('should render person specific links', function () {
            var query = element(by.model('$ctrl.query'));
            query.value = 'Dzmitry';

            element.all(by.css('.persons li a')).first().click().then(function () {
                expect(browser.getCurrentUrl()).toBe('http://localhost:8080/app/index.html#!/person/1')
            });
        })
    });

    describe('View: Person detail', function () {

        beforeEach(function () {
            browser.get('index.html#!/person/1');
        });

        it('should display person name', function () {
            expect(element(by.binding('$ctrl.person.name')).getText()).toBe('Dzmitry Barysevich');
        });

        it('should display/hide inputs', function () {
            var headerHref = element(by.css("div.person-detail-header > h3 > a"));
            headerHref.click().then(function () {
                expect(element(by.model('$ctrl.person.name')).isPresent()).toBe(true);

                var btnBack = element(by.css('[ng-click="$ctrl.cancel()"]'));
                btnBack.click().then(function () {
                    expect(element(by.model('$ctrl.person.name')).isPresent()).toBe(false);
                });
            });
        });

    });

    describe('View: Person skills', function () {

        beforeEach(function () {
            browser.get('index.html#!/person/1');
        });

        it('should display skills', function () {
            expect(element.all(by.binding('skill.skillName')).count()).toBeGreaterThan(0);
        });

        it('should display/hide inputs', function () {
            var headerHref = element(by.css("person-skill > div > div > div > h4 > a"));
            headerHref.click().then(function () {
                expect(element(by.css('[ng-click="$ctrl.toggleNewSkill(listName)"]')).isPresent()).toBe(true);

                var btnBack = element(by.css('[ng-click="$ctrl.cancel()"]'));
                btnBack.click().then(function () {
                    expect(element(by.css('[ng-click="$ctrl.toggleNewSkill(listName)"]')).isPresent()).toBe(false);
                });
            });
        });

    });

    describe('View: Person projects', function () {

        beforeEach(function () {
            browser.get('index.html#!/person/1');
        });

        it('should display projects', function () {
            expect(element.all(by.repeater('$ctrl.personProjects')).count()).toBeGreaterThan(0);
        });

        it('should display/hide inputs for adding new company', function () {
            var headerHref = element(by.css('[ng-click="$ctrl.addCompany()"]'));
            headerHref.click().then(function () {
                expect(element(by.css('[ng-submit="$ctrl.submitProject(project.id, personProject)"]')).isPresent()).toBe(true);

                var btnBack = element(by.css('[ng-click="$ctrl.deleteProject(project.id)"]'));
                btnBack.click().then(function () {
                    expect(element(by.css('[ng-submit="$ctrl.submitProject(project.id, personProject)"]')).isPresent()).toBe(false);
                });
            });
        });

        it('should display/hide inputs for editing company', function () {
            var headerHref = element(by.css('[ng-click="$ctrl.toggleEditCompany(value[0].companyInfo.id)"]'));
            headerHref.click().then(function () {
                expect(element(by.css('[ng-submit="$ctrl.submitCompany(value[0].companyInfo.id, personCompany)"]')).isPresent()).toBe(true);

                var btnBack = element(by.css('[ng-click="$ctrl.toggleEditCompany(value[0].companyInfo.id)"]'));
                btnBack.click().then(function () {
                    expect(element(by.css('[ng-submit="$ctrl.submitCompany(value[0].companyInfo.id, personCompany)"]')).isPresent()).toBe(false);
                });
            });
        });

        it('should display/hide inputs for editing project', function () {
            var headerHref = element(by.css('[ng-click="$ctrl.toggleEditProject(project.id)"]'));
            headerHref.click().then(function () {
                expect(element(by.css('[ng-submit="$ctrl.submitProject(project.id, personProject)"]')).isPresent()).toBe(true);

                var btnBack = element(by.css('[ng-click="$ctrl.toggleEditProject(project.id)"]'));
                btnBack.click().then(function () {
                    expect(element(by.css('[ng-submit="$ctrl.submitProject(project.id, personProject)"]')).isPresent()).toBe(false);
                });
            });
        });

        it('should add new project', function () {
            var headerHref = element(by.css('[ng-click="$ctrl.addCompany()"]'));
            headerHref.click().then(function () {
                expect(element(by.css('[ng-submit="$ctrl.submitProject(project.id, personProject)"]')).isPresent()).toBe(true);

                var btnSave = element(by.id('btnSaveNewCompanyId'));
                var companyName = element(by.model('value[0].companyInfo.name'));
                var position = element(by.model('project.position'));
                var description = element(by.model('project.description'));
                var startDate = element(by.model('value[0].companyInfo.startDt'));

                companyName.sendKeys("test");
                position.sendKeys("test");
                description.sendKeys("test");
                startDate.sendKeys("2017-06-15");

                btnSave.click().then(function () {
                    expect(element(by.css('[ng-click="$ctrl.addProject(value[0].companyInfo.id)"')).isPresent()).toBe(true);
                });
            });
        });

    });

});