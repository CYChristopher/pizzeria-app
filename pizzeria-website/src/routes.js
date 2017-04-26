export function routes($routeProvider) {
    $routeProvider

    .when('/', {
        template: '<home></home>'
    })
    
    .when('/inscription', {
            template: '<inscriptiont></inscription>',
    })

    .when('/pizzas', {
        template: '<liste-pizzas></liste-pizzas>'
    })

    .when('/commande', {
        template: '<commande></commande>'
    })

    .otherwise({
        redirectTo: '/'
    })
}