export class PizzaService{
	
	constructor($http, localStorageService, $q){
		this.localStorageService = localStorageService
		this.$http=$http
		this.$q=$q
	}
	
	getPizzas(){
		if(!this.localStorageService.get('pizzas')){
		}
		return this.$q.resolve(this.localStorageService.get('pizzas'))
	}	
} 