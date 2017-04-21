import angular from 'angular'
import ngRoute from 'angular-route'
import home from './home'
import { AjouterPanierComponent } from './ajouterPanier.component'


angular.module('pizzeria', [ngRoute, home])
    .config(function ($routeProvider, $locationProvider) {
        $locationProvider.html5Mode(true);
        $routeProvider.otherwise('/')
    })
    .component('ajouterPanier', AjouterPanierComponent)
    
    ;
