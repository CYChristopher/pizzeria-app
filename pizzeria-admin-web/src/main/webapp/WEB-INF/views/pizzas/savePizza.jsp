<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Lister Pizza" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<div class="container">

	<h1>Nouvelle Pizza</h1>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<form method="post">
		<label for="newcode">Code :</label> <input
			style="border-color:${erreur[0]}" class="form-control" id="newcode"
			name="newcode" type="text" placeholder="Code" value="${newcode}">

		<label for="ref">Nom :</label> <input
			style="border-color:${erreur[1]}" class="form-control" id="ref"
			name="ref" type="text" placeholder="Nom" value="${ref}"> <label
			for="prix">Prix : </label> <input style="border-color:${erreur[2]}"
			step="0.01" class="form-control" id="prix" name="prix" type="number"
			placeholder="prix" value="${prix}">

		<div class="form-group">
			<label for="categorie">Categorie : </label> <select id="categorie"
				class="form-control" name="categorie">

				<c:forEach var="current" items="${categoriePizza}">
					<option value="${current}">${current.name()}</option>
				</c:forEach>
			</select>
		</div>

		<div class="row">

			<div class="col-sm-6">
				<h2>Ingredients sélectionné</h2>
				<div id="ingredientSelectione" class='list-group'></div>
			</div>
			<div class="col-sm-6">
				<h2>Liste des ingredients</h2>
				<ul id="ingredients" class='list-group'>
					<c:forEach var="current" items="${listeIngredients}">
						<li id='${current.id}' class="list-group-item">${current.nom}</li>
					</c:forEach>
				</ul>
			</div>
		</div>



		<input class="btn btn-success" type="submit" value="Valider">
		<a href=<c:url value='/pizzas/list'/>><button type="button"
				class="btn btn-primary">Retour</button></a>
	</form>
</div>

<script type="text/javascript"
	src='<c:url value="/static/JS/gestionIngredient.js"/>'></script>

<jsp:include page="../layout/footer.jsp" />
