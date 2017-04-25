import template from './ajouterPanier.html';

class controller {
    constructor(localStorageService) {
        this.StockageService = localStorageService;
    }
    ajouterAuStockageLocal() {
        let contenuStockage = this.StockageService.get('panier');
        let itemPresent = false;
        if (contenuStockage === null) {
            console.log('hello')
            contenuStockage = [];
        }
        else {
            contenuStockage.forEach(panierItem => {
                if (this.item.id === panierItem.id) {
                    panierItem.quantite++;
                    itemPresent = true;
                }
            });
        }
        if (!itemPresent) {
            contenuStockage.push({ id: `${this.item.id}`, quantite: 1 });
        }
        this.StockageService.set('panier', contenuStockage, 'localStorage');
        console.log(this.StockageService.get('panier'));
    }
}

export const AjouterPanierComponent = {
    bindings: {
        item: '<'
    },
    controller,
    template
}