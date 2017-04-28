import template from './inscription.html';

class controller {

    constructor(ClientService, $location) {
        this.$location = $location;
        this.ClientService = ClientService;
        this.client = {
            email: '',
            motDePasse: '',
            nom: '',
            prenom: '',
        }
        this.confMdp = '';
    }

    validerForm(userForm) {
        if (userForm.$valid) {
            this.ClientService.saveClient(this.client)
                .then(response => {
                    //Modifier la redirection
                    this.$location.path('/');
                });
        }
    }
}

export const InscriptionComponent = {
    controller,
    template,
}