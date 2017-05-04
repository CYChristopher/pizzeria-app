export class CommandeService {
	constructor($http, API_URL, localStorageService, ClientService, $q, $location, PanierService) {
		this.PanierService = PanierService;
		this.localStorageService = localStorageService;
		this.$location = $location;
		this.ClientService = ClientService;
		this.$http = $http;
		this.$q = $q;
		this.API_URL = API_URL;
	}

	getCommandes() {
		let utilisateur = this.localStorageService.get(
			"utilisateur",
			"localStorage"
		);

		if (utilisateur) {
			let token = this.localStorageService.get("token", "localStorage");
			this.$http.defaults.headers.common.Authorization =
				"Bearer " + token;
		}
		return this.$http.get(`${this.API_URL}/commandes`).then(r => r.data);
	}

	setCommande(type) {
		let utilisateur = this.localStorageService.get(
			"utilisateur",
			"localStorage"
		);
		if (utilisateur) {
			let token = this.localStorageService.get("token", "localStorage");
			this.$http.defaults.headers.common.Authorization =
				"Bearer " + token;

			this.ClientService.getClient(utilisateur.id).then(utilisateur => {
				let pizzas = this.localStorageService.get(
					"pizzas",
					"localStorage"
				);
				let panier = this.localStorageService.get(
					"panier",
					"localStorage"
				);

				if (utilisateur !== "") {
					let commandeComplete = {
						commande: {
							type: type,
							client: utilisateur
						},
						commandesPizza: []
					};

					panier.forEach(pizza => {
						commandeComplete.commandesPizza.push({
							quantite: pizza.quantite,
							id: {
								pizza: pizza,
								commande: commandeComplete.commande
							}
						});
					});
					this.localStorageService.remove("panier");

					console.log(commandeComplete);
					this.$http
						.post(`${this.API_URL}/commandes`, commandeComplete)
						.then(r => r.data);
					this.PanierService.panier = '';
					this.$location.path('/');
				} else {
					alert(
						"Vous avez été deconnecté pour une raison mystérieuse. Veuillez vous reloguer"
					);
				}
			});
		}
	}
}
