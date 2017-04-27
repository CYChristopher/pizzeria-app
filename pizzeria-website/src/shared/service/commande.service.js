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
		this.localStorageService.setStorageType('sessionStorage')
		this.ClientService.getClient(this.localStorageService.get('utilisateur')).then(utilisateur=>{
			let commande={
				'dateCommande':Date.now(),
				'numeroCommande':'CMD'+utilisateur.id+Math.floor(Date.now()/60),
				'statut':'NON_TRAITE',
				'type':type,
				'client':utilisateur,
				'livreur':null				
			}
			this.$http.post(`${this.API_URL}/commandes`,commande).then(r=>r.data)
		
		})
		
	}	
} 