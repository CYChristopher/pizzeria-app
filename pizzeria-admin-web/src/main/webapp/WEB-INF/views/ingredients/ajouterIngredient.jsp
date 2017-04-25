<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/layout/entete.jsp">
	<jsp:param name="title" value="Ajout Ingredient" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="jumbotron">

	<div class="container">
	
		<h1>Nouvelle Ingredient</h1>
		
		<form method="post">
			<label for="nom">Nom :</label>
			<input class="form-control" id="nom" name="nom" type="text" placeholder="Nom">
			
			<label for="qte">Quantité :</label>
			<input class="form-control" id="qte" name="qte" type="number" placeholder="quantité">
			
			<label for="prix">Prix :</label>
			<input class="form-control" id="prix" name="prix" type="number" placeholder="prix">
			
			<button class="btn btn-success" type="submit">Valider</button>
		</form>
		
	</div>
	
</div>

<jsp:include page="../layout/footer.html"/>