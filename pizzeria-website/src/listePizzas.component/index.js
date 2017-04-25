import template from './listePizzas.html';

class controller{
	constructor(PizzaService){
		this.PizzaService=PizzaService
	}
	$onInit(){		
		this.pizzas=this.PizzaService.getPizzas()//.then(pizzas=>
			//this.pizzas=pizzas
		//)        
    }
	
}

export const ListePizzasComponent = {
	    bindings: {
	    },
	    template,
	    controller,
	}