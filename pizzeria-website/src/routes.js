export function routes($routeProvider) {
    $routeProvider

    .when('/', {
        template: '<home></home>'
    })
    
    .when('/inscription', {
            template: '<inscription-component></inscription-component>',
    })
    
    .when('/commande/:id?', {
            template: `<commande-component></commande-component>`,
        })

    .when('/pizzas', {
        template: '<liste-pizzas></liste-pizzas>'
    })

    .otherwise({
        redirectTo: '/'
    })
}