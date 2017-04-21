import angular from 'angular'
import ngRoute from 'angular-route'
import home from './home'

import {routes} from './routes'
import {PizzaComponent} from './pizza.component'
import {ListePizzasComponent} from './listePizzas.component'

import {PizzaService} from './shared/service/pizza.service'


angular.module('pizzeria', [ngRoute, home])
	.config(routes)
    .config(function ($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true);
    })

.component('pizza',PizzaComponent)
.component('listePizzas',ListePizzasComponent)

.service('PizzaService',PizzaService)