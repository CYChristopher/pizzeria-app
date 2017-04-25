import template from './commande.html';

class controller {

    constructor(CommandeService, $routeParams) {
        this.CommandeService = CommandeService;
        CommandeService.getCommande($routeParams.id)
        .then(commande => {this.commande = commande; console.log(this.commande)});
    }
}

export const CommandeComponent = {
    controller,
    template,
}