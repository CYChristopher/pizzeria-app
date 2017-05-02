<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Lister Pizza" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<div class="container">
	<div class="row">
		<div class="col-md-9">
	<h1>Editer Pizza ${editPizza.code}</h1>


	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<form method="post">

				<div class="form-group">
					<label for="newcode">Code :</label> <input id="newcode"
						class="form-control" name="newcode" type="text"
						value="${editPizza.code}" required>
				</div>

				<div class="form-group">
					<label for="ref">Nom :</label> <input id="ref" name="ref"
						class="form-control" type="text" value="${editPizza.nom}" required>
				</div>

		<div class="form-group">
			<label for="prix">Prix : </label> <input step="0.01" id="number"
				name="prix" class="form-control" type="number"
				value="${editPizza.prix}" required>
		</div>
		
		<div class="form-group">
			<label for="image"> Image :</label> <input id="urlImage" name="urlImage"
				class="form-control" type="text" placeholder="Lien vers image" value="${editPizza.urlImage}" required>
		</div>


		<div class="form-group">

			<label for="categorie">Categorie : </label> <select id="categorie"
				class="form-control" name="categorie">
				<c:forEach var="current" items="${categoriePizza}">
					<c:choose>
						<c:when
							test="${editPizza.categorie.name().equals(current.name())}">
							<option selected value="${current}">${current.name()}</option>
						</c:when>
						<c:otherwise>
							<option value="${current}">${current.name()}</option>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</select>
		</div>


		<div class="row">
			<div class="col-sm-6">
				<h2>Ingredients Sélectionné</h2>
				<div  id="ingredientSelectione" class='list-group'>
				<c:forEach var="current" items="${editPizza.listeIngredients}">
					<input id='${current.id}' class="list-group-item"
						name="ingredientSelectione" value='${current.nom}'>
				</c:forEach>
				</div>
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
				<a href=<c:url value=''/>>
					<button type="button" class="btn btn-primary">Reset</button>
				</a>
			</form>


</div>

<script type="text/javascript"
	src='<c:url value="/static/JS/gestionIngredient.js"/>'></script>

<jsp:include page="../layout/footer.jsp" />
