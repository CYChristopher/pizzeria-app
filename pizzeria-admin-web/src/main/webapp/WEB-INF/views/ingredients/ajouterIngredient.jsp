<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page ajouter Ingredient" name="title" />
</jsp:include>

<body class="container">

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
	
</body>
</html>