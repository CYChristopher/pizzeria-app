<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/views/layout/entete.jsp">
	<jsp:param name="title" value="Liste Ingredients"/>
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="container">
	<div class="row">
		<div class="col-md-9">
			<h1>Liste des ingredients</h1>
			<a class="btn btn-primary" href="<c:url value=" ./ajouter"/>">Nouvel ingredient</a>
			<a class="btn btn-primary" href="../historiqueIngredients/liste">Historique Ingrédients</a>
			<br>
			<c:if test="${msg != null}">
				<div class="alert alert-danger" role="alert">${msg}</div>
			</c:if>
			<table class="table">
				<thead>
					<tr>
						<th>Nom</th>
						<th>Quantité</th>
						<th>Prix</th>
						<th>Archivé</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="ingredient" items="${listeIngredients}">
						<tr>
							<td>${ ingredient.nom }</td>
							<td>${ ingredient.quantite }</td>
							<td>${ ingredient.prix } €</td>
							<td>
								<c:if test="${ingredient.archive == true}">Oui</c:if>
								<c:if test="${ingredient.archive == false}">Non</c:if>
							</td>
							<td>
								<a href="<c:url value=" ./editer?id=${ingredient.id}"/>" class="btn btn-warning">Editer</a>							
								<form method="POST">
									<input type="hidden" name="id" value="${ingredient.id}">
									<input type="hidden" name="action" value="supprimer">
									<button type="submit" class="btn btn-danger">Archiver</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
<jsp:include page="../layout/footer.jsp"/>