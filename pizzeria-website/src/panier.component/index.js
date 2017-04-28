import template from './panier.html';

class controller {

    constructor(localStorageService, PizzaService, $location) {
        this.localStorageService = localStorageService;
        this.PizzaService = PizzaService;

        this.panier = this.localStorageService.get('panier', 'localStorage');
        this.pizzas = [];
        this.prixTotal = 0;
        this.PizzaService.getPizzas()
            .then(pizzas => { this.pizzas = pizzas })
            .then(() => this.prixTotal = this.getPrixTotal());
        this.$location = $location
    }

    //retourne la pizza de 'pizzas' avec pizzaId
    getPizzabyId(pizzaId) {
        return this.pizzas.find(pizza => pizza.id === parseInt(pizzaId));
    }


    supprimer(itemPanier) {
        let index = this.panier.indexOf(itemPanier);
        if (index > -1) {
            this.panier.splice(index, 1);
        }
        this.updatePanier();
    }

    plusNbPizza(itemPanier) {
        itemPanier.quantite++;
        this.updatePanier();
    }

    moinsNbPizza(itemPanier) {
        if (itemPanier.quantite === 1) {
            this.supprimer(itemPanier)
        } else {
            itemPanier.quantite--;
        }
        this.updatePanier();
    }

    //repercute tout les changements du panier local sur le localStorage
    updatePanier() {
        this.localStorageService.set('panier', this.panier, 'localStorage');
        this.prixTotal = this.getPrixTotal();
    }

    getPrixTotal() {
        if (this.panier === null) return 0;
        return this.panier
            .reduce((accumulateur, item) => {
                return accumulateur + (this.getPizzabyId(item.id).prix * item.quantite);
            }, 0)
    }

    passerCommande() {
        let utilisateur = this.localStorageService.get('utilisateur', 'sessionStorage');
        if (utilisateur) {
            console.log('hello')
            this.$location.path('/commande')
        }
        else {
            this.localStorageService.set('pageRedirectionConnexion', this.$location.path(), 'sessionStorage');
            this.$location.path('/connexion');
        }
    }
}


export const PanierComponent = {
    bindings: {},
    template,
    controller
}