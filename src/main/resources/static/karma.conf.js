//jshint strict: false
module.exports = function (config) {
    config.set({

        basePath: './app',

        files: [
            '../bower_components/angular/angular.js',
            '../bower_components/angular-animate/angular-animate.js',
            '../bower_components/angular-resource/angular-resource.js',
            '../bower_components/angular-route/angular-route.js',
            '../bower_components/angular-mocks/angular-mocks.js',
            'https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.min.js',
            '../bower_components/angular-filter/dist/angular-filter.min.js',
            '../bower_components/angular-drag-and-drop-lists/angular-drag-and-drop-lists.min.js',
            '../**/*.module.js',
            '*!(.module|.spec).js',
            '!(bower_components)/**/*!(.module|.spec).js',
            '**/*.spec.js'
        ],

        exclude: [
            '../bower_components/**/*.spec.js',
            '../bower_components/**/*.module.js'
        ],

        autoWatch: true,

        frameworks: ['jasmine'],

        browsers: ['Chrome'],

        plugins: [
            'karma-chrome-launcher',
            'karma-jasmine'
        ]

    });
};