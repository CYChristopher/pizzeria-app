import angular from 'angular'
import ngRoute from 'angular-route'

import { routes } from './routes';

import { PizzaService } from "./shared/service/pizza.service.js"

import { HomeComponent } from './home.component'


angular.module('pizzeria', [
    ngRoute, 
    'LocalStorageModule'
])
.config(routes)
.config(function ($locationProvider) {
    $locationProvider.html5Mode(true);
})
   

.component('home', HomeComponent)

.service('PizzaService', PizzaService)

;
