import template from './ajouterPanier.html';

class controller {
    constructor(localStorageService) {
        this.StockageService = localStorageService;
    }
    ajouterAuStockageLocal() {
        let contenuStockage = this.StockageService.get('panier','localStorage');
        if (contenuStockage === null) {
            contenuStockage = [];
        }
        
        let index = contenuStockage.findIndex(panierItem => this.item.id === parseInt(panierItem.id));
        if (index >= 0){
            contenuStockage[index].quantite++;
        }
        else {
            contenuStockage.push({ id: `${this.item.id}`, quantite: 1 });
        }
        this.StockageService.set('panier', contenuStockage, 'localStorage');
    }
}

export const AjouterPanierComponent = {
    bindings: {
        item: '<'
    },
    controller,
    template
}