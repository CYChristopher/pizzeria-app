<<<<<<< Upstream, based on branch 'develop' of git@github.com:DTAFormation/pizzeria-app.git
import template from './inscription.html';

class controller {

    constructor(ClientService, $location) {
        this.$location = $location;
        this.ClientService = ClientService;
        this.client = {
            email : '',
            motDePasse : '',
            nom : '',
            prenom : '',
        }
        this.confMdp = '';
    }

    validerForm(userForm){
        if(userForm.$valid){
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
=======
import template from './inscription.html';

class controller {

    constructor(UserService) {
        this.UserService = UserService;
        this.user = {
            email : '',
            mdp : '',
            nom : '',
            prenom : '',
        }
        this.confMdp = '';
    }

    validerForm(){
        console.log(this.UserService);
    }
}

export const InscriptionComponent = {
    controller,
    template,
>>>>>>> d09eecd Création de l'ihm d'inscription
}