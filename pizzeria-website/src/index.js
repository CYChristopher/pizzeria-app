import angular from 'angular'
import ngRoute from 'angular-route'

import { routes } from './routes'
import { PizzaService } from './shared/service/pizza.service'
import { ClientService } from './shared/service/client.service'
import { HomeComponent } from './home.component'
import { PizzaComponent } from './pizza.component'
import { ListePizzasComponent } from './listePizzas.component'
import { InscriptionComponent } from './inscription/index'
import { AjouterPanierComponent } from './ajouterPanier.component'
import { PanierComponent } from './panier.component'


angular.module('pizzeria', [ngRoute, 'LocalStorageModule'])
    .value('API_URL', BACKEND_API_URL)
    .config(routes)
    .config(function ($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true);
    })
    .config(['localStorageServiceProvider', function (localStorageServiceProvider) {
        localStorageServiceProvider
            .setPrefix('pizzeriaLS')
    }])
    .service('PizzaService', PizzaService)
    .service('ClientService', ClientService)
    .component('pizza', PizzaComponent)
    .component('listePizzas', ListePizzasComponent)
    .component('home', HomeComponent)
    .component('ajouterPanier', AjouterPanierComponent)
    .component('inscriptionComponent', InscriptionComponent)
    .component('panierComponent', PanierComponent)