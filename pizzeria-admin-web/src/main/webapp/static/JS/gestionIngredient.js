
let eventAjout = document.getElementById("ingredients").addEventListener("click",addIngredient);
let ingredientSelectione = document.getElementById('ingredientSelectione');
let eventIngredientSelectione = document.getElementById('ingredientSelectione').addEventListener("click",removeIngredient);

let h = document.createElement.bind(document);
let ingredients = [];
let isInList = false;
let div = h('div');
div.classList.add('alert','alert-danger', 'alert-dismissable', 'fade', 'in');
div.role = "alert";

function addIngredient(a){
	ingredients.forEach(ingredient => {
		if(ingredient === a.target.innerHTML){
			isInList = true;
		}
	});
	if(!isInList){
		let li = h('li');
	    li.className = 'list-group-item';
	    li.innerText = a.target.innerHTML;
	    ingredients.push(a.target.innerHTML);
	    li.setAttribute('name','ingredientSelectione');
	    li.dataToggle = 'tooltip';
	    li.title = 'Supprimer un ingrédient';
	    li.style.cursor = 'pointer';
	    ingredientSelectione.appendChild(li);
	} else {
		div.innerText = 'Vous ne pouvez ajouter l\'ingrédient ' + a.target.innerHTML + ' qu\'une fois !';
		div.style.display = 'block';
		ingredientSelectione.prepend(div);
	}
}

function removeIngredient(a){	
	div.style.display = 'none';
	let i = ingredients.indexOf(a.target.innerHTML);
	if(i != -1)
		ingredients.splice(i, 1);
   ingredientSelectione.removeChild(a.target);
}

