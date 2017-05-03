<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Lister Commandes" name="title" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>


<div class="container">
	<div class="row">
		<div class="col-md-9">

<h1> Commandes</h1>
<a class="btn btn-primary" href="nouvelle">Nouvelle Commande</a>

<table class="table">
  <thead>
    <tr>
			<th>Numéro</th>
     		<th>Statut</th>
			<th>Date</th>
			<th>Adresse</th>
			<th>Livreur</th>
			<th>Client</th>
			<th>Pizzas</th>
			<th></th>
    </tr>
  </thead>
  <tbody>
<c:forEach var="cmd" items="${listeCommandes}">
		<tr>
			<td>${cmd.numeroCommande}</td>
			<td>${cmd.statut}</td>
			<td>${cmd.dateCommande}</td>
			<td>${cmd.adresse}</td>
			<td>${cmd.livreur.nom} ${cmd.livreur.prenom}</td>
			<td>${cmd.client.nom} ${cmd.client.prenom}</td>
			
			<td>
			<c:forEach var="piz" items="${cmd.commandesPizzas}">
				${piz.quantite}<br>

			</c:forEach>
			
			</td>
			<td>
			<a href="<c:url value="/commandes/edit?id=${cmd.id}"/>" class="btn btn-primary">Editer</a>
			<br>
				<form method="POST"
					action="<c:url value='/commandes/supprimer?id=${cmd.id}'/>">
					<button type="submit" class="btn btn-danger">Supprimer</button>
				</form>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

</div>
<jsp:include page="../layout/footer.jsp"/>
