import template from './commande.html';

class controller {

    constructor(CommandeService, $routeParams) {
        this.CommandeService = CommandeService;
        this.total = 0;
        CommandeService.getCommande($routeParams.id)
        .then(commande => 
        {
            this.commande = commande; console.log(this.commande)
            this.commande.commandesPizzas.forEach(function(element) {
                this.total += element.quantite * element.pizza.prix;
            }, this);
        });
    }
}

export const CommandeComponent = {
    controller,
    template,
}