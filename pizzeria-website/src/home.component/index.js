import template from "./home.html"


class controller {
    constructor(PizzaService) {
        this.PizzaService = PizzaService;
    }

}

export const HomeComponent = {
    bindings: {},
    template,
    controller,
}