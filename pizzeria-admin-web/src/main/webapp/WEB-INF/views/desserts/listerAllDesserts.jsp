<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Liste des Desserts"/>
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="container">
	<div class="row">
		<div class="col-md-9">
			<h1>Liste des Desserts</h1>
			<a class="btn btn-primary" href="../desserts/liste">Retour</a>
			<br>
			<c:if test="${msg != null}">
				<div class="alert alert-danger" role="alert">${msg}</div>
			</c:if>
			<br>
				<table class="table">
					<thead>
						<tr>
							<th>Image</th>
							<th>Code</th>
							<th>Nom</th>
							<th>Prix</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dessert" items="${listeDesserts}">
							<tr>
								<td><img src="${ dessert.urlImage }" width="250px" height="200px" alt="${dessert.nom}" title="${dessert.nom}"></td>
								<td>${ dessert.code }</td>
								<td>${ dessert.nom }</td>
								<td>${ dessert.prix } €</td>
								<td></td>
								<td>
									<c:if test="${dessert.archive}">	
										<form method="POST">
											<input type="hidden" name="id" value="${dessert.id}">
											<input type="hidden" name="action" value="supprimer">
											<button type="submit" class="btn btn-success">Désarchiver</button>
										</form>
									</c:if>
									<c:if test="${!dessert.archive}">	
										<form method="POST">
											<input type="hidden" name="id" value="${dessert.id}">
											<input type="hidden" name="action" value="supprimer">
											<button type="submit" class="btn btn-danger">Archiver</button>
										</form>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
<jsp:include page="../layout/footer.jsp"/>