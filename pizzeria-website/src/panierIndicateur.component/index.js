import template from './panierIndicateur.html';

class controller {
  constructor( localStorageService ) {
    this.localStorageService = localStorageService;

    this.panier = this.localStorageService.get( 'panier', 'localStorage' );
    this.pizzas = [];
    this.quantiteTotale = this.getQuantiteTotale();
    this.prixTotal = 0;
  }

  getQuantiteTotale() {
    return ( this.panier ? 0 : this.panier
      .reduce( ( accumulateur, item ) => {
        return accumulateur + ( this.panier.quantite );
      }, 0 ) )
  }

};

export const PanierIndicateurComponent = {
  bindings: {},
  controller,
  template
};
