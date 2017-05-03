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
			<br>
			<c:if test="${msg != null}">
				<div class="alert alert-danger" role="alert">${msg}</div>
			</c:if>
			<a class="btn btn-primary" href="./ajouter">Nouveau Dessert</a>
			<a class="btn btn-primary" href="../historiqueDesserts/liste">Historique Desserts</a>
			<br>
				<table class="table">
					<thead>
						<tr>
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
								<td>${ dessert.code }</td>
								<td>${ dessert.nom }</td>
								<td>${ dessert.prix } €</td>
								<td>
									<a href="<c:url value=" ./editer?id=${dessert.id}"/>" class="btn btn-warning">Editer</a>
								</td>
								<td>
									<form method="POST">
										<input type="hidden" name="code" value="${dessert.code}">
										<input type="hidden" name="action" value="supprimer">
										<button type="submit" class="btn btn-success">Archiver</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
<jsp:include page="../layout/footer.jsp"/>