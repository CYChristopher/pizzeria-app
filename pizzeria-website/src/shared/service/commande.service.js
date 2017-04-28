export class CommandeService {

    constructor($http, API_URL, $q) {
        this.$http = $http;
        //Service de promesse fournit par angular
        this.$q = $q;
        this.API_URL = API_URL;
    }

    getCommande(id) {
        return (id !== undefined)
            ? this.$http.get(`${this.API_URL}/commande/${id}`)
                .then(response => response.data)
            : Promise.resolve({});
    }

    saveCommande(commande) {
        return (commande.id)
            ? this.$http.put(`${this.API_URL}/commande/${commande.id}`, commande)
            : this.$http.post(`${this.API_URL}/commande`, commande)
                .then(response => response.data);
    }

}