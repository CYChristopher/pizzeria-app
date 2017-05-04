import template from './ajouterPanier.html';

class controller {
  constructor( localStorageService, PanierService, AlertService ) {
    this.StockageService = localStorageService;
    this.ps = PanierService;
    this.AlertService = AlertService;
  }

  ajouterAuStockageLocal() {
    this.ps.ajouterAuStockageLocal( this.item );
    this.AlertService.addAlert('Pizza ajoutée à votre panier', 'success')
  }
}

export const AjouterPanierComponent = {
  bindings: {
    item: '<'
  },
  controller,
  template
}
