<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Statistiques" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />


<div class="container">
	<div class="row">
		<div class="col-md-9">
			<h1>Statistiques</h1>
			<h2>Top 5 des pizzas</h2>
			<table class="table">
				<tr>
					<td>Image</td>
					<td>Informations</td>
				</tr>
				<c:forEach var="pizza" items="${topFivePizzas}">
					<tr>
						<td><img src="${pizza.urlImage}" width="250px" height="200px"
							alt="${pizza.nom}" title="${pizza.nom}"></td>
						<td>
							<div class="row">
								<div class="col-md-12">
									Ref. ${pizza.id} <br> <b>${pizza.nom}</b> <br>
									${pizza.prix}€ <br> ${pizza.versionPizza} <br>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>

			<h2>Top 5 des clients</h2>
			<table class="table">
				<thead>
					<tr>
						<th>Ref</th>
						<th>Nom</th>
						<th>Prénom</th>
						<th>Adresse</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="client" items="${topFiveClients}">
						<tr>
							<td>${client.id}</td>
							<td>${client.nom}</td>
							<td>${client.prenom}</td>
							<td>${client.adresse}</td>
							<td>${client.email}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<jsp:include page="../layout/footer.jsp" />