import template from './commande.html';

class controller{
	constructor(CommandeService){
		this.CommandeService=CommandeService
	}
	$onInit(){		
		this.CommandeService.getCommandes().then(commandes=>{
			this.commandes=commandes	
		})
    }
	
	setCommande(){
		this.CommandeService.setCommande()
	}
	
}

export const CommandeComponent = {
	    bindings: {
	    },
	    template,
	    controller,
	}