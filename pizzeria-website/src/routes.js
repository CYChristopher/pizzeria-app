export function routes($routeProvider) {
    $routeProvider

    .when('/', {
        template: '<home></home>'
    })
    
    .when('/inscription', {
            template: '<inscription-component></inscription-component>',
<<<<<<< Upstream, based on branch 'develop' of git@github.com:DTAFormation/pizzeria-app.git
    })
=======
        })
>>>>>>> d09eecd Création de l'ihm d'inscription

    .when('/pizzas', {
        template: '<liste-pizzas></liste-pizzas>'
    })

    .otherwise({
        redirectTo: '/'
    })
}