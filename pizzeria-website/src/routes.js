export function Routes($locationProvider, $routeProvider) {
    $locationProvider.html5Mode(true);
    $routeProvider
        .when('/', {
            template: '',
        })
        .when('/commande/:id?', {
            template: `<commande-component></commande-component>`,
        })
        .otherwise('/');
}