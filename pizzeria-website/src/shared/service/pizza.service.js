export class PizzaService {

  constructor( $http, localStorageService, $q, API_URL ) {
    this.localStorageService = localStorageService
    this.$http = $http
    this.$q = $q
    this.API_URL = API_URL;
    this.lssPizzas = this.localStorageService.get( 'pizzas' );
  }

  getPizzas() {
    return ( !this.lssPizzas ? this.$http.get( `${this.API_URL}/pizzas` )
      .then( r => {
        this.localStorageService.set( 'pizzas', r.data )
        return r.data;
      } ) : this.$q.resolve( this.lssPizzas ) )
  }
}
