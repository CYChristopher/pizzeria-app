import template from './commande.html';

class controller{
	constructor(CommandeService){
		this.CommandeService=CommandeService
	}
	$onInit(){		
		this.CommandeService.getCommandes().then(commandes=>{
			this.commandes=commandes	
		})
		this.commandeType='A_EMPORTER'
    }
	
	setCommandeType(type){
		this.commandeType = type
	}
	
	setCommande(){
		this.CommandeService.setCommande(this.commandeType)
	}
	
}

export const CommandeComponent = {
	    bindings: {
	    },
	    template,
	    controller,
	}