import template from './ajouterPanier.html';

class controller {
    ajouterAuStockageLocal() {
        let contenuStockage = JSON.parse(localStorage.getItem('panier'));
        if (contenuStockage === null) {
            let premierItem = [this.item];
            localStorage.setItem('panier', JSON.stringify(premierItem));
        }
        else {
            let donnees = JSON.parse(localStorage.getItem('panier'));
            donnees.push(this.item);
            console.log(donnees);
            localStorage.setItem('panier', JSON.stringify(donnees));
        }
    }
}

export const AjouterPanierComponent = {
    bindings: {
        item: '<'
    },
    controller,
    template
}