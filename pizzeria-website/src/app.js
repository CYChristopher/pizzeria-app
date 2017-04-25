import angular from 'angular'
import ngRoute from 'angular-route'
import home from './home'
import { Routes } from './routes.js'
import { CommandeComponent } from './commande/index.js'
import {CommandeService} from './shared/service/commande.service'

angular.module('pizzeria', [ngRoute, home])
    .value('API_URL', 'http://localhost:8080')
    .config(Routes)
    .service('CommandeService', CommandeService)
    .component('commandeComponent', CommandeComponent);
