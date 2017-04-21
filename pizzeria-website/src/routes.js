export function routes($routeProvider) {
    $routeProvider

    .when('/', {
        template: '<home></home>'
    })

    .when('/pizzas', {
        template: '<liste-pizzas></liste-pizzas>'
    })

    .otherwise({
        redirectTo: '/'
    })
}