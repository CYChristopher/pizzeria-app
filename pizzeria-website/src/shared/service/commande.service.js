
export class CommandeService{
	
	constructor($http, localStorageService, $q){
		this.localStorageService = localStorageService
		this.$http=$http
		this.$q=$q
	}
	
	getCommandes(){
		return this.$http.get('http://localhost:8080/commandes').then(r=>r.data)
	}	
} 