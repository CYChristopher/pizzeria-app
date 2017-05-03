import template from "./connexion.html";

class controller {
    constructor($location, $http, API_URL, localStorageService, ClientService) {
        this.$location = $location;
        this.$http = $http;
        this.API_URL = API_URL;
        this.stockageService = localStorageService;
        this.clientService = ClientService;
    }

    connexion() {
        this.clientService
            .verifierUtilisateur(this.email, this.motDePasse)
            .then(resp => {
                console.log(resp);
                
                this.stockageService.set("utilisateur", resp, "sessionStorage");
                let pagePrecedente = this.stockageService.get(
                    "pageRedirectionConnexion"
                );
                if (!pagePrecedente) {
                    this.$location.path("/");
                } else {
                    this.$location.path(pagePrecedente);
                }
            })
            .catch(resp => {
                console.log(resp);

                alert("ERREUR : " + resp.data.message);
            });
    }


}

export const ConnexionComponent = {
    template,
    controller
};
