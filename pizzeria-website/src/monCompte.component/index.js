import template from "./monCompte.html"


class controller {
    constructor(ClientService, ) {
        this.ClientService = ClientService;
        this.modif = false;


        this.ClientService.getConnectedClient().then(client => this.clientConnecte = client)
    }

    modifications() {
        this.modif = !this.modif;
    }




}

export const MonCompteComponent = {
    bindings: {},
    template,
    controller,
}