import angular from 'angular'
import ngRoute from 'angular-route'
import { HomeComponent } from './home.component'


import { routes } from './routes'
import { PizzaService } from './shared/service/pizza.service'
import { ClientService } from './shared/service/client.service'
import { PizzaComponent } from './pizza.component'
import { ListePizzasComponent } from './listePizzas.component'
<<<<<<< HEAD
import { InscriptionComponent } from './inscription/index'
=======
>>>>>>> #23 usw008 mon compte
import { AjouterPanierComponent } from './ajouterPanier.component'
import { MonCompteComponent } from './monCompte.component'



angular.module('pizzeria', [ngRoute, 'LocalStorageModule'])
    .value('API_URL', 'http://localhost:8080')
    .config(routes)
    .config(function ($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true);
    })
    .config(['localStorageServiceProvider', function (localStorageServiceProvider) {
        localStorageServiceProvider
            .setPrefix('pizzeriaLS')
    }])
<<<<<<< HEAD
    .service('PizzaService', PizzaService)
    .service('ClientService', ClientService)
    .component('pizza', PizzaComponent)
    .component('listePizzas', ListePizzasComponent)
    .component('home', HomeComponent)
    .component('ajouterPanier', AjouterPanierComponent)
    .component('inscriptionComponent', InscriptionComponent)
    .component('monCompte', MonCompteComponent)
=======

.service('PizzaService', PizzaService)

.component('pizza', PizzaComponent)
    .component('listePizzas', ListePizzasComponent)
    .component('home', HomeComponent)
    .component('ajouterPanier', AjouterPanierComponent)
>>>>>>> #23 usw008 mon compte
