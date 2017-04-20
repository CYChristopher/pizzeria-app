import angular from 'angular'
import ngRoute from 'angular-route'

import { HomeComponent } from './home.component'


angular.module('pizzeria', [ngRoute])
    .config(function($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true);
        $routeProvider
            .when('/', {
                template: '<home></home>'
            })
            .otherwise('/')
    })

.component('home', HomeComponent)

;