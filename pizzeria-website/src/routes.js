export function routes($routeProvider) {
    $routeProvider
    
    .when('/', {
        template: '<home> </home>'
    })

    .when('/pizzas', {
        template: 'test'
    })

    .otherwise({
        redirectTo: '/'
    })
}