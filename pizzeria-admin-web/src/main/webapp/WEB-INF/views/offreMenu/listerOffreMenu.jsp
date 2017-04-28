<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Liste des offres de menu" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<div class="container">

	<h1>Liste des offres de menu</h1>
	<a class="btn btn-primary" href="nouvelle">Nouvelle offre de menu</a> <br>

	<table class="table">
		<thead>
			<tr>
				<th>Code</th>
				<th>Nom</th>
				<th>Prix</th>
				<th>Pizzas</th>
				<th>Boissons</th>
				<th>Dessert</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="om" items="${listeOffreMenu}">
				<tr>
					<td>${om.code}</td>
					<td>${om.nom}</td>
					<td>${om.prix} €</td>
					<td>
						<c:forEach var="piz" items="${om.pizzas}">
							<a href="<c:url value="/pizzas/editer?id=${piz.id}"/>">${piz.nom}</a>
							<br>
						</c:forEach>
					</td>
					<td>
						<c:forEach var="boi" items="${om.boissons}">
							<a href="<c:url value="/boissons/editer?id=${boi.id}"/>">${boi.nom}</a>
							<br>
						</c:forEach>
					</td>
					<td>
						<c:forEach var="des" items="${om.desserts}">
							<a href="<c:url value="/desserts/editer?id=${des.id}"/>">${des.nom}</a>
							<br>
						</c:forEach>
					</td>
					<td><a href="<c:url value="/offre_menu/editer?id=${om.id}"/>"
						class="btn btn-warning">Editer</a></td>
					<td>
						<form method="POST"
							action="<c:url value='/offre-menu/supprimer?id=${om.id}'/>">
							<button type="submit" class="btn btn-danger">Supprimer</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<jsp:include page="../layout/footer.jsp" />