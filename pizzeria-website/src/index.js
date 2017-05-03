import angular from 'angular'
import ngRoute from 'angular-route'

/* beautify preserve:start */
import { routes } from './routes'
import { LocalStorageModule } from 'angular-local-storage'
import { PizzaService } from './shared/service/pizza.service'
import { CommandeService } from './shared/service/commande.service'
import { ClientService } from './shared/service/client.service'
import { PanierService } from './shared/service/panier.service.js'
import { HomeComponent } from './home.component'
import { PizzaComponent } from './pizza.component'
import { ListePizzasComponent } from './listePizzas.component'
import { CommandeComponent } from './commande.component'
import { InscriptionComponent } from './inscription.component'
import { ConnexionComponent } from './connexion.component'
import { AjouterPanierComponent } from './ajouterPanier.component'
import { PanierComponent } from './panier.component'
import { NavbarComponent } from './navbar.component'
import { MonCompteComponent } from './monCompte.component'
import { PanierIndicateurComponent } from './panierIndicateur.component'
/* beautify preserve:end */

angular.module( 'pizzeria', [ ngRoute, 'LocalStorageModule' ] )
  .value( 'API_URL', BACKEND_API_URL )
  .config( routes )
  .config( function ( $routeProvider, $locationProvider ) {
    $locationProvider.html5Mode( true );
  } )
  .config( [ 'localStorageServiceProvider', function (
    localStorageServiceProvider ) {
    localStorageServiceProvider
      .setPrefix( 'pizzeriaLS' )
    } ] )
  .service( 'PizzaService', PizzaService )
  .service( 'ClientService', ClientService )
  .service( 'PanierService', PanierService )
  .component( 'pizza', PizzaComponent )
  .component( 'listePizzas', ListePizzasComponent )
  .component( 'home', HomeComponent )
  .component( 'ajouterPanier', AjouterPanierComponent )
  .component( 'inscription', InscriptionComponent )
  .component( 'connexion', ConnexionComponent )
  .component( 'panier', PanierComponent )
  .component( 'navbar', NavbarComponent )
  .component( 'monCompte', MonCompteComponent )
  .component( 'panierIndicateur', PanierIndicateurComponent )
  
  if ('serviceWorker' in navigator) {
  navigator.serviceWorker.register('/serviceworker.js', { scope: '/' }).then(function(reg) {
    // registration worked
    console.log('Registration succeeded. Scope is ' + reg.scope);
    setInterval(function(){ reg.update(); }, 3000);
  }).catch(function(error) {
    // registration failed
    console.log('Registration failed with ' + error);
  });
}
