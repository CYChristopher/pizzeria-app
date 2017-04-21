import angular from 'angular'
import ngRoute from 'angular-route'
import home from './home'
import { Routes } from './routes.js'
import { InscriptionComponent } from './inscription/index'
import {UserService} from './shared/service/user.service'

angular.module('pizzeria', [ngRoute, home])
    .value('API_URL', 'http://localhost:8080')
    .config(Routes)
    .service('UserService', UserService)
    .component('inscriptionComponent', InscriptionComponent);