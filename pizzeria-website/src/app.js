import angular from 'angular'
import ngRoute from 'angular-route'
import home from './home'
import { Routes } from './routes.js'
import { InscriptionComponent } from './inscription/index'
import {ClientService} from './shared/service/client.service'

angular.module('pizzeria', [ngRoute, home])
    .value('API_URL', 'http://localhost:8080')
    .config(Routes)
    .service('ClientService', ClientService)
    .component('inscriptionComponent', InscriptionComponent);
