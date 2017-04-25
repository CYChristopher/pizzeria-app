import angular from 'angular'
import ngRoute from 'angular-route'
import { HomeComponent } from './home.component'

import { routes } from './routes'
import { PizzaService } from './shared/service/pizza.service'
import { ClientService } from './shared/service/client.service'
import { PizzaComponent } from './pizza.component'
import { ListePizzasComponent } from './listePizzas.component'
import { InscriptionComponent } from './inscription/index'


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
.service('PizzaService',PizzaService)
.service('ClientService', ClientService)
.component('pizza',PizzaComponent)
.component('listePizzas',ListePizzasComponent)
.component('home', HomeComponent)
.component('ajouterPanier', AjouterPanierComponent)
.component('inscriptionComponent', InscriptionComponent)