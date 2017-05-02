import template from './ajouterPanier.html';

class controller {
  constructor( localStorageService, PanierService ) {
    this.StockageService = localStorageService;
    this.ps = PanierService;
  }

  ajouterAuStockageLocal() {
    this.ps.ajouterAuStockageLocal( this.item );
  }

}

export const AjouterPanierComponent = {
  bindings: {
    item: '<'
  },
  controller,
  template
}
