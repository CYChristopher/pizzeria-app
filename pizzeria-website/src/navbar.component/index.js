import template from "./navbar.html";

class controller {
    constructor(ClientService, ) {
        this.ClientService = ClientService;
        this.ClientService.getConnectedClient().then(client => this.clientConnecte = client)
    }

}

export const NavbarComponent = {
    bindings: {},
    controller,
    template
}