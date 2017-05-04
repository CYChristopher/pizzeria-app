
let eventAjout = document.getElementById("ingredients").addEventListener("click",addIngredient);
let ingredientSelectione = document.getElementById('ingredientSelectione');
let eventIngredientSelectione = document.getElementById('ingredientSelectione').addEventListener("click",removeIngredient);

let h = document.createElement.bind(document);
let ingredients = [];
//Si on edite une pizza on récupère la liste des ingrédients déjà présents
let ingredientPresents = ingredientSelectione.getElementsByTagName("INPUT");
//Et on les ajoute au tableau des ingrédients sélectionnés
if(ingredientPresents.length > 0){
	for(i = 0; i < ingredientPresents.length; i++){
		ingredients.push(ingredientPresents[i].value);
	};
}
let isInList = false;
let div = h('div');
div.classList.add('alert','alert-danger', 'alert-dismissable', 'fade', 'in');
div.role = "alert";

function addIngredient(a){
	//On ne peut ajouter plus de 10 ingrédients à une pizza
	if(ingredients.length >= 10){
		div.innerText = 'Vous ne pouvez ajouter plus d\'ingrédients à cette pizza ! La limite est de 10.';
		div.style.display = 'block';
		ingredientSelectione.prepend(div);
	}else{
		//On vérifie que l'ingrédient n'est pas déjà présent dans la pizza, pour éviter les doublons
		ingredients.forEach(ingredient => {
			if(ingredient === a.target.innerHTML){
				isInList = true;
			}
		});
		//Si ce n'est pas un doublon
		if(!isInList){
			div.style.display = 'none';
			let input = h('input');
		    input.className = 'list-group-item';
		    input.value = a.target.innerHTML;
		    //On l'ajoute au tableau des ingrédients sélectionnés
		    ingredients.push(a.target.innerHTML);
		    input.setAttribute('name','ingredientSelectione');
		    input.dataToggle = 'tooltip';
		    input.title = 'Supprimer un ingrédient';
		    input.style.cursor = 'pointer';
		    input.style.width = '100%';
		    //Puis on l'ajoute à la pizza
		    ingredientSelectione.appendChild(input);
		}
		//Si c'est un doublon on avertit l'utilisateur
		else {
			div.innerText = 'Vous ne pouvez ajouter l\'ingrédient ' + a.target.innerHTML + ' qu\'une fois !';
			div.style.display = 'block';
			ingredientSelectione.prepend(div);
			isInList = false;
		}
	}
}

function removeIngredient(a){	
	div.style.display = 'none';
	//On supprime l'ingrédient du tableau des ingrédients sélectionnés
	let i = ingredients.indexOf(a.target.innerHTML);
	if(i != -1){
		ingredients.splice(i, 1);
	}
   ingredientSelectione.removeChild(a.target);
}

