import angular from 'angular'
import ngRoute from 'angular-route'
import { HomeComponent } from './home.component'

import {routes} from './routes'
import {PizzaService} from './shared/service/pizza.service'
import {PizzaComponent} from './pizza.component'
import {ListePizzasComponent} from './listePizzas.component'
import {AjouterPanierComponent} from './ajouterPanier.component'



angular.module('pizzeria', [ngRoute, 'LocalStorageModule'])
	.config(routes)
    .config(function ($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true);
    })
    .config(['localStorageServiceProvider',function (localStorageServiceProvider){
    	localStorageServiceProvider
    		.setPrefix('pizzeriaLS')
    }])

.service('PizzaService',PizzaService)
.component('pizza',PizzaComponent)
.component('listePizzas',ListePizzasComponent)
.component('home', HomeComponent)
.component('ajouterPanier', AjouterPanierComponent)


