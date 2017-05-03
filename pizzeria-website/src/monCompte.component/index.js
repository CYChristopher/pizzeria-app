import template from "./monCompte.html"


class controller {
    constructor(ClientService) {
        this.ClientService = ClientService;
        this.modif = false;
        this.currentMdp="";
        this.newMdp="";
        this.newMdp2="";

        this.ClientService.getConnectedClient().then(client => {this.clientConnecte = client;this.clientConnecteNonModif = Object.assign({},client);})
    }

    modifications() {
        this.modif = !this.modif;
    }

    soumissionFormulaire($form) {
        this.modifications()
         
        if($form.mdpClient.$viewValue.trim() !== "")
        {
         this.clientConnecte.motDePasse = $form.mdpClient.$viewValue;
        }
        if($form.currentMdpClient.$viewValue === "")
        {
            this.clientConnecte.motDePasse = "";
        }
         this.ClientService.saveClient(this.clientConnecte);
       
       
    }


    emailExiste($form) {
        
        let email = $form.emailClient.$viewValue;
        
        if(email !== "" && email !== this.clientConnecteNonModif.email){
            this.ClientService.emailExiste(email).then(resp => {
                $form.emailClient.$setValidity("email_existant", !resp);
            });
        }else
        {
            $form.emailClient.$setValidity("email_existant", true);
        }
    }

    testMdpUtilisateur($form)
    {
        let mdp = $form.currentMdpClient.$viewValue;
       if(mdp.trim() !=="" )
       {
         $form.currentMdpClient.$setValidity("mdp_aucune_saisie", true); 
         $form.currentMdpClient.$setValidity("mdp_vide", true);    
        this.ClientService.testMdpUtilisateur(this.clientConnecte.id,mdp)
        .then(resp =>{ $form.currentMdpClient.$setValidity("mdp_faux", resp);
                      
                     });
        }else{
            $form.currentMdpClient.$setValidity("mdp_faux", true);
            if(mdp !=="" )
            {
                $form.currentMdpClient.$setValidity("mdp_vide", false); 
                $form.currentMdpClient.$setValidity("mdp_aucune_saisie", true); 
            }else{
                 $form.currentMdpClient.$setValidity("mdp_aucune_saisie", false); 
                $form.currentMdpClient.$setValidity("mdp_vide", true);  
            }
        
        }
    }

     testNouveauMdpUtilisateur($form)
    {
        let mdp = $form.mdpClient.$viewValue;
        
       if(mdp.trim() !=="" )
       {
         $form.mdpClient.$setValidity("mdpMod_vide", true);    
         $form.mdpClient.$setValidity("mdpMod_aucune_saisie", true);
        }else{
            if(mdp !=="" )
            {
                $form.mdpClient.$setValidity("mdpMod_aucune_saisie", true);
                $form.mdpClient.$setValidity("mdpMod_vide", false);  
            }else{
                $form.mdpClient.$setValidity("mdpMod_aucune_saisie", false);
                $form.mdpClient.$setValidity("mdpMod_vide", true);  
            }
        
        }
    }


}

export const MonCompteComponent = {
    bindings: {},
    template,
    controller,
}