export function routes($routeProvider) {
    $routeProvider

    .when('/', {
        template: '<home></home>'
    })
    
    .when('/inscription', {
            template: '<inscription-component></inscription-component>',
    })

    .when('/pizzas', {
        template: '<liste-pizzas></liste-pizzas>'
    })

    .when('/panier', {
            template: '<panier></panier>'
        })

    .otherwise({
        redirectTo: '/'
    })
}