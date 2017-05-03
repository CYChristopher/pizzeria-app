import template from "./navbar.html";

class controller {
  constructor( localStorageService, $location, ClientService ) {
    this.ClientService = ClientService;
    this.stockageService = localStorageService;
    this.$location = $location;

  }

  getConnectedClient() {
    return parseInt( this.stockageService.get( 'utilisateur',
      'sessionStorage' ) );
  }

  connecter() {
    this.stockageService.set( 'pageRedirectionConnexion', this.$location.path(),
      'sessionStorage' );
    this.$location.path( '/connexion' );
  }
}

export const NavbarComponent = {
  bindings: {},
  controller,
  template
}
