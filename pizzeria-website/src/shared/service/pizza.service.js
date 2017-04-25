export class PizzaService{
	
	constructor($http, localStorageService){
		this.localStorageService = localStorageService
		this.$http=$http
	}
	
	getPizzas(){
		if(!this.localStorageService.get('pizzas')){
			this.$http.get('http://localhost:8080/pizzas').then(r=>this.localStorageService.set('pizzas',r.data))
		}
		return this.localStorageService.get('pizzas')
	}	
} 