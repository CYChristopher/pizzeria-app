export function Routes($locationProvider, $routeProvider) {
    $locationProvider.html5Mode(true);
    $routeProvider
        .when('/', {
            template: '',
        })
        .when('/inscription', {
            template: '<inscription-component></inscription-component>',
        })
        .otherwise('/');
}