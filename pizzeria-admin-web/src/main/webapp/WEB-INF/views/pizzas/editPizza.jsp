<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Lister Pizza" name="title" />
</jsp:include>

<body class="container">
	<h1>Editer Pizza ${editPizza.code} </h1>
	
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<form method="post">

		<div class="form-group">
			<label for="newcode">Code :</label> <input id="newcode"
				class="form-control" name="newcode" type="text"
				value="${editPizza.code}">
		</div>

		<div class="form-group">
			<label for="ref">Nom :</label> <input id="ref" name="ref"
				class="form-control" type="text" value="${editPizza.nom}">
		</div>

		<div class="form-group">
			<label for="prix">Prix : </label> <input step="0.01" id="number"
				name="prix" class="form-control" type="number"
				value="${editPizza.prix}">
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

		<input class="btn btn-success" type="submit" value="Valider">
		<a href=<c:url value='/pizzas/list'/>><button type="button"
				class="btn btn-primary">Retour</button></a>
	</form>


</body>
</html>