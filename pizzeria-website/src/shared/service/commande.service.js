
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
	
	setCommande(){
		this.localStorageService.setStorageType('sessionStorage')
		this.ClientService.getClient(this.localStorageService.get('utilisateur')).then(u=>{
			let commande={
				'dateCommande':'1520',
				'numeroCommande':123,
				'statut':'LIVRE',
				'type':'LIVRAISON',
				'client':u[0],
				'livreur':null				
			}
			
			console.log(commande)
			this.$http.post(`${this.API_URL}/commandes`,commande).then(r=>r.data)
		
		})
		
	}	
} 