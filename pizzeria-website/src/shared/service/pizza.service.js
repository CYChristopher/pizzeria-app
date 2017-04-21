export class PizzaService{
	
	constructor($http){
		this.$http=$http
	}
	
	getPizzas(){
		return this.$http.get('http.localhost:8000/pizzas').then(r=>r.data)
	}	
} 