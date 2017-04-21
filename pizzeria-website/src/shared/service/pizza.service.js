
export class PizzaService {
    constructor ($http) {
        this.$http = $http ;
        this.API_URL = "http://localhost:3000/api-pizzas";
    }

    getPizzas() {
        return this.$http.get(this.API_URL)
            .then(response => response.data);
    }

   


}