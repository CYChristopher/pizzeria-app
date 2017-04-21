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
}