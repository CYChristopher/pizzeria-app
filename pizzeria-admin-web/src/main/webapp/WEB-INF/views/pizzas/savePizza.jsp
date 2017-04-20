<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Pizza" name="title" />
</jsp:include>

<body class="container">
	<h1>Nouvelle Pizza</h1>
	
	<form method="post">
			<label for="newcode">Code :</label> <input class="form-control"
				id="newcode" name="newcode" type="text" placeholder="Code">

			<label for="ref">Nom :</label> <input class="form-control" id="ref"
				name="ref" type="text" placeholder="Nom"> <label for="prix">Prix
				: </label> <input step="0.01" class="form-control" id="prix" name="prix"
				type="number" placeholder="prix">

			<div class="form-group">
				<label for="categorie">Categorie : </label> 
				<select id="categorie"	class="form-control" name="categorie">
					<option>VIANDE</option>
					<option>POISSON</option>
					<option>SANS_VIANDE</option>
							
				</select>
			</div>
			<input class="btn btn-success" type="submit" value="Valider">
			<a href=<c:url value='/pizzas/list'/>><button type="button"
					class="btn btn-primary">Retour</button></a>
		</form>
	
	
</body>
</html>