<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Editer Commandes" name="title" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />


<div class="container">
	<div class="row">
		<div class="col-md-9">

	<h1>Edition</h1>

	<form method="post">

		<div class="form-group">
			<label>Numero de Commande :</label> 
			<input class="form-control" name="numCommande" type="text" value="${commande.numeroCommande}" required>
		</div>

		<div class="form-group">
			<label>Statut :</label> 
			<select class="form-control" name="statut" required>
				<c:forEach var="status" items="${statusPossible}">
					<option <c:if test="${commande.statut == status}">selected</c:if>>${status}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			<label>Adresse :</label> 
			<input class="form-control" name="adresse" type="text" value="${commande.adresse}" required>
		</div>
		
		<div class="form-group">
			<label>Livreur :</label> 
			<select class="form-control" name="livreur" required>
				<c:forEach var="liv" items="${listeLivreur}">
					<option value="${liv.id}"<c:if test="${commande.livreur.id == liv.id}">selected</c:if>>${liv.nom} ${liv.prenom}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			<label>Client :</label> 
			<select class="form-control" name="client" required>
				<c:forEach var="cli" items="${listeClient}">
					<option value="${cli.id}" <c:if test="${commande.client.id == cli.id}">selected</c:if>>${cli.nom} ${cli.prenom}</option>
				</c:forEach>
			</select>
		</div>
		
		
		<div class="form-group">
			<label>Pizzas :</label>
			
				<table class="table">
				 <thead>
				    <tr>
						<th>Nom de la pizza</th>
						<th>Commande</th>
				   </tr>
				</thead>
					<tbody>
					<c:forEach var="piz" items="${listePizza}" varStatus="status">
						<tr>
							<td><label>${piz.nom}</label> </td>
							<!-- <td><input class="form-control" name="test" type="number"></td> Un jour on pourra commander plusieurs fois la même pizza, mais c'est pour la v2 -->
							<td> <input type="checkbox" name="pizzaCommandeId" value="${piz.id}" <c:if test="${commande.pizzas.contains(piz)}">checked</c:if>></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
		</div>

		<input class="btn btn-success" type="submit" value="Valider">
		<a href=<c:url value='/commandes/list'/>><button type="button"
				class="btn btn-primary">Retour</button></a>
	</form>

</div>
<jsp:include page="../layout/footer.jsp" />
