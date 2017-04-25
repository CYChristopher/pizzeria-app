import template from "./home.html"


class controller {
    constructor(PizzaService) {
        this.PizzaService = PizzaService;
        this.pizzas = this.PizzaService.getPizzas();
        this.last3 = [];
        this.getLast3();
        this.test = "test";
    }

    getLast3() {
        this.pizzas.then(pizza => this.last3.push(pizza[pizza.length - 1]));
        this.pizzas.then(pizza => this.last3.push(pizza[pizza.length - 2]));
        this.pizzas.then(pizza => this.last3.push(pizza[pizza.length - 3]));
    }

}

export const HomeComponent = {
    bindings: {},
    template,
    controller,
}