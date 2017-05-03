import template from './listePizzas.html';

class controller {
    constructor(PizzaService) {
        this.PizzaService = PizzaService
    }
    $onInit() {
        return this.pizzas = this.PizzaService.getPizzas().then(pizzas => {
            this.pizzas = pizzas
            this.pizzasCat = pizzas
        })
    }
    setCategorie(cat) {
        this.pizzasCat = this.pizzas
        this.categorie = cat
        if(cat==null) return this.pizzasCat
        return this.pizzasCat = this.pizzasCat.filter(pizza => {
            if(pizza.categorie===cat)
                return pizza
        });
    }
}

export const ListePizzasComponent = {
    bindings: {},
    template,
    controller,
}