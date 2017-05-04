import template from "./home.html"

class controller {
  constructor( PizzaService ) {
    this.PizzaService = PizzaService;
  }

  $onInit() {
    return this.pizzas = this.PizzaService.getPizzas()
      .then( pizzas => {
        this.pizzas = pizzas
      } )
  }

}

export const HomeComponent = {
  bindings: {},
  template,
  controller,
}
