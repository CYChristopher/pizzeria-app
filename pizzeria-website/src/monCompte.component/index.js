import template from "./monCompte.html"


class controller {
    constructor(ClientService) {
        this.ClientService = ClientService;
        this.modif = false;
        this.currentMdp="";
        this.newMdp="";
        this.newMdp2="";

        this.ClientService.getConnectedClient().then(client => this.clientConnecte = client)
    }

    modifications() {
        this.modif = !this.modif;
    }

    soumissionFormulaire() {
        this.modifications()
        this.ClientService.saveClient(this.clientConnecte);
    }


    emailExiste($form) {
        
        let email = $form.emailClient.$viewValue;
        
        if(email != ""){
            this.ClientService.emailExiste(email).then(resp => {
                $form.emailClient.$setValidity("email_existant", !resp);
                console.log($form.emailClient.$error);
            });
        }
    }

    testMdpUtilisateur($form)
    {
        let mdp = $form.currentMdpClient.$viewValue;
       if(mdp !="")
       {
        this.ClientService.testMdpUtilisateur(this.clientConnecte.id,mdp)
        .then(resp =>{ $form.currentMdpClient.$setValidity("mdp_faux", resp);
                      
                     });
        }else{
            $form.currentMdpClient.$setValidity("mdp_faux", true);
        }
    }


}

export const MonCompteComponent = {
    bindings: {},
    template,
    controller,
}