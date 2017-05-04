import template from "./panier.html";

class controller {

  constructor( $location, PanierService, localStorageService ) {
    this.localStorageService = localStorageService;
    this.ps = PanierService;
    this.panier = this.localStorageService.get( 'panier', 'localStorage' );
    this.$location = $location
  }

  supprimer( itemPanier ) {
    this.ps.supprimer( itemPanier );
  }

  passerCommande() {
    let utilisateur = this.localStorageService.get( 'utilisateur',
      'localStorage' );
    if ( utilisateur ) {
      console.log( 'hello' )
      this.$location.path( '/commande' )
    } else {
      this.localStorageService.set( 'pageRedirectionConnexion', this.$location
        .path(), 'sessionStorage' );
      this.$location.path( '/connexion' );
    }
  }
}

export const PanierComponent = {
    bindings: {},
    template,
    controller
};
