import template from "./navbar.html";

class controller {
    constructor(localStorageService, $location, ClientService, $http) {
        this.ClientService = ClientService;
        this.stockageService = localStorageService;
        this.$location = $location;
        this.$http = $http;
    }

    getConnectedClient() {
        let utilisateur = this.stockageService.get(
            "utilisateur",
            "localStorage"
        );

        if (!utilisateur) return null;

        return parseInt(utilisateur.id);
    }

    connecter() {
        this.stockageService.set(
            "pageRedirectionConnexion",
            this.$location.path(),
            "sessionStorage"
        );
        this.$location.path("/connexion");
    }

    deconnexion() {
        this.stockageService.remove("utilisateur", "localStorage");
        this.stockageService.remove("token", "localStorage");
        this.$http.defaults.headers.common = {};
        this.$location.path("/");
    }
}

export const NavbarComponent = {
    bindings: {},
    controller,
    template
};
