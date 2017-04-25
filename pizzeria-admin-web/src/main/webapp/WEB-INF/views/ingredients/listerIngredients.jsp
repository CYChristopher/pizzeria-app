<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/layout/entete.jsp">
	<jsp:param name="title" value="Lister Ingredienst" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="jumbotron">

	<div class="container">
	
		<h1>Liste des ingredients</h1>
		<a class="btn btn-primary" href="<c:url value="/ingredient/add"/>">Nouvelle ingredient</a>
		<br>
		<c:if test="${msg != null}">
			<div class="alert alert-danger" role="alert">${msg}</div>
		</c:if>
	
		<table class="table">
		
			<thead>
				<tr>
					<td>Nom</td>
					<td>Quantité</td>
					<td>Prix</td>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="ingedient" items="${listeIngredients}">
				<tr>
					<td><b>${ ingedient.nom }</b></td>
					<td>${ ingedient.quatite }</td>
					<td>${ ingedient.prix } €</td>
					<td>
						<a href="<c:url value="/ingredients/edit?id=${ingedient.id}"/>" class="btn btn-primary">Editer</a>
					</td>
					<td>						

						<form method="POST">
							<input type="hidden" name="id" value="${ingedient.id}">
							<input type="hidden" name="action" value="supprimer">
							<button type="submit" class="btn btn-danger">Supprimer</button>
						</form>
					</td>
				</tr>
				</c:forEach>
				
			</tbody>
			
		</table>
	</div>
</div>
<jsp:include page="../layout/footer.html"/>