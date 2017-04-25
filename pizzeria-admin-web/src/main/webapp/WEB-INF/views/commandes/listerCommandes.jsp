<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Lister Commandes" name="title" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="container">

<h1> Commandes</h1>

<table class="table">
  <thead>
    <tr>
			<th>Numero</th>
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
			<td>${cmd.livreur}</td>
			<td>${cmd.client}</td>
			<td>${cmd.pizzas}</td>
			<td>
			<a href="<c:url value="/commandes/edit?code=${cmd.id}"/>" class="btn btn-primary">Editer</a>
			<br>
				<form method="POST">
					<input type="hidden" name="id" value="${cmd.id}">
					<input type="hidden" name="action" value="supprimer">
					<button type="submit" class="btn btn-danger">Supprimer</button>
				</form>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

</div>
<jsp:include page="../layout/footer.html"/>
