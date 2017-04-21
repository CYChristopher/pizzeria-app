export class UserService {

    constructor($http, API_URL, $q) {
        this.$http = $http;
        //Service de promesse fournit par angular
        this.$q = $q;
        this.API_URL = API_URL;
    }

    getUser(id) {
        return (id !== undefined)
            ? this.$http.get(`${this.API_URL}/${id}`)
                .then(response => response.data)
            : Promise.resolve({});
    }

    saveUser(user) {
        return (user.id)
            ? this.$http.put(`${this.API_URL}/${user.id}dsd`, user)
            : this.$http.post(this.API_URL, user)
                .then(response => response.data);
    }

    deleteUser(user) {
        return this.$http.delete(`${this.API_URL}/${user.id}`)
            .then(response => response.data);
    }

}