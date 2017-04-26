import template from './connexion.html'

class controller {
    constructor($location, $http, API_URL, localStorageService, ClientService) {
        this.$location = $location;
        this.$http = $http;
        this.API_URL = API_URL;
        this.stockageService = localStorageService;
        this.clientService = ClientService;
    }

    connexion() {
        this.clientService.verifierUtilisateur(this.email, this.motDePasse).then(resp => {
            if (resp === -1) {
                alert('ERREUR : Utilisateur non reconnu');
            }
            else {
                this.stockageService.set('utilisateur', resp, 'sessionStorage');
                this.$location.path('/');// TODO : Redirection vers la derniere page visitée
            }
        })
    }
}

export const ConnexionComponent = {
    template,
    controller
}