import template from "./navbar.html";

class controller {
    constructor(localStorageService, $location) {
        this.stockageService = localStorageService;
        this.$location = $location;
    }

    connecter(){
        this.stockageService.set('pageRedirectionConnexion', this.$location.path(), 'sessionStorage');
        this.$location.path('/connexion');
    }
}

export const NavbarComponent = {
    bindings: {},
    controller,
    template
}