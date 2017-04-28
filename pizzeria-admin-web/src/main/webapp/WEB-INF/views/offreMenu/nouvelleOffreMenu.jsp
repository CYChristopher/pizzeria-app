<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Nouvelle Offre de Menu" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<div class="container">

	<h1>Nouvelle Offre de Menu</h1>

	<form method="post">
		<div class="form-group">
			<label for="newcode">Code :</label> <input
				style="border-color:${erreur[0]}" class="form-control" name="code"
				type="text" placeholder="Code" value="${code}">
		</div>

		<div class="form-group">
			<label for="ref">Nom :</label> <input
				style="border-color:${erreur[1]}" class="form-control" name="nom"
				type="text" placeholder="Nom" value="${nom}">
		</div>

		<div class="form-group">
			<label for="prix">Prix : </label> <input
				style="border-color:${erreur[2]}" step="0.01" class="form-control"
				name="prix" type="number" placeholder="prix" value="${prix}">
		</div>

		<div class="form-group">
			<table class="table">
				<thead>
					<tr>
						<th><label>Pizza</label></th>
						<th>Prix</th>
						<th>Menu</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="piz" items="${listePizza}" varStatus="status">
						<tr>
							<td><label>${piz.nom}</label></td>
							<td>${piz.prix}</td>
							<td><input type="checkbox" name="pizzaCommande"
								value="${piz.nom}"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="form-group">
			<table class="table">
				<thead>
					<tr>
						<th><label>Boisson</label></th>
						<th>Prix</th>
						<th>Menu</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="boi" items="${listeBoisson}" varStatus="status">
						<tr>
							<td><label>${boi.nom}</label></td>
							<td>${boi.prix}</td>

							<td><input type="checkbox" name="pizzaCommande"
								value="${boi.nom}"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="form-group">
			<table class="table">
				<thead>
					<tr>
						<th><label>Dessert</label></th>
						<th>Prix</th>
						<th>Menu</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="des" items="${listeDessert}" varStatus="status">
						<tr>
							<td><label>${des.nom}</label></td>
							<td>${des.prix}</td>
							<td><input type="checkbox" name="pizzaCommande"
								value="${des.nom}"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<input class="btn btn-success" type="submit" value="Valider">
		<a href=<c:url value='/offre_menu/liste'/>>
			<button type="button" class="btn btn-primary">Retour</button>
		</a>
	</form>

</div>

<jsp:include page="../layout/footer.html" />
