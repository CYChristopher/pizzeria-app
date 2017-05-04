<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Liste Commandes" name="title" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>


<div class="container">
	<div class="row">
		<div class="col-md-9">

<h1> Toutes les commandes</h1>
<a class="btn btn-primary" href="../commandesNonLivrees/liste">Retour</a>

<table class="table">
  <thead>
    <tr>
			<th>Numéro</th>
     		<th>Statut</th>
			<th>Date</th>
			<th>Adresse</th>
			<th>Client</th>
			<th>Livreur</th>
			<th>Prix total</th>
			<th>Pizzas</th>
			<th>Quantité</th>
    </tr>
  </thead>
  <tbody>

 <c:forEach var="cmd" items="${listeCommandes}">
		<tr>
			<td>${cmd.numeroCommande}</td>
			<td>${cmd.statut}</td>
			<td>${cmd.dateCommande}</td>
			<td>${cmd.adresse}</td>
			<td>${cmd.client.nom} ${cmd.client.prenom}</td>
			<td>${cmd.livreur.nom} ${cmd.livreur.prenom}</td>
			
			
			<td>
			 	<c:set var="total" value="${0}" />
				<c:forEach var="commandesPizzas" items="${cmd.commandesPizzas}">
					<c:set var="total" value="${total + commandesPizzas.id.pizza.prix * commandesPizzas.quantite}" />
				</c:forEach>
				<c:out value="${total}"></c:out>
			</td>
			
			<td>
			<c:forEach var="commandesPizzas" items="${cmd.commandesPizzas}">
				${commandesPizzas.id.pizza.nom}
				<br>
			</c:forEach>
			</td>
			
			<td>
			<c:forEach var="commandesPizzas" items="${cmd.commandesPizzas}">
				${commandesPizzas.quantite}
				<br>
			</c:forEach>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

</div>
<jsp:include page="../layout/footer.jsp"/>
