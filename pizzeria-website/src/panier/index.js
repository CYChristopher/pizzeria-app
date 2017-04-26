import angular from 'angular';
import { PanierController } from './controllers/panier.controller';

export const PanierModule = angular.module('panier.module', [])

.controller('PanierController', PanierController)

.name;