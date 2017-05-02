export class CommandeService{
	
	constructor($http, API_URL, localStorageService, ClientService, $q){
		this.localStorageService = localStorageService
		this.ClientService = ClientService
		this.$http=$http
		this.$q=$q
        this.API_URL = API_URL
	}
	
	getCommandes(){
		return this.$http.get(`${this.API_URL}/commandes`).then(r=>r.data)
	}	
	
	setCommande(type){
			
		this.ClientService.getClient(this.localStorageService.get('utilisateur','sessionStorage')).then(utilisateur=>{
			let pizzas=this.localStorageService.get('pizzas','localStorage')
			let panier = this.localStorageService.get('panier','localStorage')
			

			if(utilisateur !== '')
			{
		
			


			let commandeComplete={
				'commande':{
					'type':type,
					'client':utilisateur
				},
				'commandesPizza':[]
			}
			
			panier.forEach(pizza=>{
				commandeComplete.commandesPizza.push({
					'quantite':pizza.quantite,
					'id':{
						'pizza':pizza,		
						'commande':commandeComplete.commande	
					}
				}
				)
			})
			this.localStorageService.removeItem("panier");
			
			console.log(commandeComplete)
			this.$http.post(`${this.API_URL}/commandes`,commandeComplete).then(r=>r.data)
			}
			else
			{
				alert('Vous avez été deconnecté pour une raison mystérieuse. Veuillez vous reloguer')
			}	
		})		
	}	
} 