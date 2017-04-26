import template from "./home.html"


class controller {
    constructor(PizzaService) {
        this.PizzaService = PizzaService;
        this.PizzaService.getPizzas().then(pizzas => {
            this.pizzas = pizzas;
            this.getLast3()
        }) ;
        this.last3 = [] ;
        this.test = "test";
    }

    getLast3() {
        this.last3.push(this.pizzas[this.pizzas.length-1]) ;
        this.last3.push(this.pizzas[this.pizzas.length-2]) ;
        this.last3.push(this.pizzas[this.pizzas.length-3]) ;
    }

}

export const HomeComponent = {
    bindings: {},
    template,
    controller,
}