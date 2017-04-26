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
				<th>Id</th>
				<th>Code</th>
				<th>Nom</th>
				<th>Prix</th>
				<th>Pizzas</th>
				<th>Boissons</th>
				<th>Dessert</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="om" items="${listeOffreMenu}">
				<tr>
					<td>${om.id}</td>
					<td>${om.code}</td>
					<td>${om.nom}</td>
					<td>${om.prix}€</td>
					<c:forEach var="piz" items="${om.pizzas}">
				${piz.nom}<br>
					</c:forEach>
					<td>${om.pizzas}</td>
					<td>${om.boissons}</td>
					<td>${om.desserts}</td>
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

<jsp:include page="../layout/footer.html" />