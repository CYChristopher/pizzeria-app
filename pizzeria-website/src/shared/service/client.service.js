export class ClientService {

    constructor($http, API_URL, $q) {
        this.$http = $http;
        //Service de promesse fournit par angular
        this.$q = $q;
        this.API_URL = API_URL;
    }

    getClient(id) {
        return (id !== undefined)
            ? this.$http.get(`${this.API_URL}/${id}`)
                .then(response => response.data)
            : Promise.resolve({});
    }

    saveClient(client) {
        return (client.id)
            ? this.$http.put(`${this.API_URL}/client/${client.id}`, client)
            : this.$http.post(`${this.API_URL}/client`, client)
                .then(response => response.data);
    }

    deleteClient(client) {
        return this.$http.delete(`${this.API_URL}/${client.id}`)
            .then(response => response.data);
    }

}