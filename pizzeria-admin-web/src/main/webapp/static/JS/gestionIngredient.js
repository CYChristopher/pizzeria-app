
let selectedIngredients=[];


let eventAjout = document.getElementById("ingredients").addEventListener("click",addIngredient);
let eventIngredientSelectione = document.getElementById('ingredientSelectione');
let h = document.createElement.bind(document);

function addIngredient(a){
	
	selectedIngredients.push({id: a.target.id , name: a.target.innerHTML});
	
	let li = h('input');
    li.className = 'list-group-item';
    li.setAttribute('value',a.target.innerHTML);
    li.setAttribute('name','ingredientSelectione');
    eventIngredientSelectione.appendChild(li);
}


