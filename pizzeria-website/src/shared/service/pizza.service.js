export class PizzaService{
	
	constructor($http, localStorageService, $q){
		this.localStorageService = localStorageService
		this.$http=$http
		this.$q=$q
	}
	
	getPizzas(){
		if(!this.localStorageService.get('pizzas')){
			this.$http.get('http://localhost:8080/pizzas').then(r=>this.localStorageService.set('pizzas',r.data))
		}
		return this.$q.resolve(this.localStorageService.get('pizzas'))
	}	
} 