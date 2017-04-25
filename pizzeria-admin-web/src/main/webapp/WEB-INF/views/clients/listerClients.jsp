<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Liste des Clients" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<div class="container">

	<h1>Liste des clients</h1>
	<a class="btn btn-primary" href="new">Nouveau client</a> <br>
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<table class="table">
		<thead>
			<tr>
				<th>Ref</th>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Adresse</th>
				<th>Email</th>
				<th>Hash du mot de passe</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="client" items="${clients}">
				<tr>
					<td>${client.id}</td>
					<td>${client.nom}</td>
					<td>${client.prenom}</td>
					<td>${client.adresse}</td>
					<td>${client.email}</td>
					<td>${client.motDePasse}</td>
					<td><a href="<c:url value="/clients/edit?id=${client.id}"/>"
						class="btn btn-warning">Editer</a></td>
					<td>
						<form method="POST"
							action="<c:url value='/clients/delete?id=${client.id}'/>">
							<button type="submit" class="btn btn-danger">Supprimer</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<jsp:include page="../layout/footer.html" />