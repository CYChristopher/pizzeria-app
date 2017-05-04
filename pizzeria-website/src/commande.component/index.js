import template from './commande.html';

class controller{
	constructor(CommandeService, AlertService ){
		this.CommandeService=CommandeService
		this.AlertService = AlertService
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
		this.AlertService.addAlert("Commande passée", "success")
	}
	
}

export const CommandeComponent = {
	    bindings: {
	    },
	    template,
	    controller,
	}