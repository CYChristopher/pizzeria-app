import template from './listePizzas.html';
import {PizzaService} from '../shared/service/pizza.service'

class controller{
	
	$onInit(){
		this.pizzas=PizzaService.getPizzas()
        
    }
	
}

export const ListePizzasComponent = {
	    bindings: {
	    },
	    template,
	    controller,
	}