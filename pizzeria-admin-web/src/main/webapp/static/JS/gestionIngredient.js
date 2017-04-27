
let eventAjout = document.getElementById("ingredients").addEventListener("click",addIngredient);
let ingredientSelectione = document.getElementById('ingredientSelectione');
let eventIngredientSelectione = document.getElementById('ingredientSelectione').addEventListener("click",removeIngredient);

let h = document.createElement.bind(document);

function addIngredient(a){
	

	
	let li = h('input');
    li.className = 'list-group-item';
    li.setAttribute('value',a.target.innerHTML);
    li.setAttribute('name','ingredientSelectione');
    ingredientSelectione.appendChild(li);
}


function removeIngredient(a){	
	
	
   ingredientSelectione.removeChild(a.target);
}

