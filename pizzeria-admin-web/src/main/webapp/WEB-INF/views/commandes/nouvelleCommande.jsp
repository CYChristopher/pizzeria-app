<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Enregistrer Commande" name="title" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />


<div class="container">
	<div class="row">
		<div class="col-md-9">

			<h1>Ajout de commande</h1>
			<c:if test="${msg != null}">
				<div class="alert alert-danger" role="alert">${msg}</div>
			</c:if>

			<form method="post">
			
				<div class="form-group">
					<label>Statut :</label> <select class="form-control" name="statut"
						required>
						<c:forEach var="cmd" items="${statusPossible}">
							<c:choose>
								<c:when test="${statut == null}">
									<option>${cmd}</option>
								</c:when>
								<c:when test="${statut == cmd}">
									<option selected>${cmd}</option>
								</c:when>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<c:choose>
						<c:when test="${adresse == null}">
							<label>Adresse :</label>
							<input class="form-control" name="adresse" type="text" required>
						</c:when>
						<c:otherwise>
							<label>Adresse :</label>
							<input class="form-control" name="adresse" type="text"
								value="${adresse }" required>
						</c:otherwise>
					</c:choose>
				</div>

				<div class="form-group">
					<label>Livreur :</label> <select class="form-control"
						name="livreur" required>
						<c:forEach var="liv" items="${listeLivreur}">
							<c:choose>
								<c:when test="${idLivreur == null || liv.id != idLivreur }">
									<option value="${liv.id}">${liv.nom}${liv.prenom}</option>
								</c:when>
								<c:when test="${idLivreur != null && liv.id == idLivreur }">
									<option selected="selected" value="${liv.id}">${liv.nom}
										${liv.prenom}</option>
								</c:when>
							</c:choose>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<label>Client :</label> <select class="form-control" name="client"
						required>
						<c:forEach var="cli" items="${listeClient}">
							<c:choose>
								<c:when test="${idClient == null || idClient != cli.id }">
									<option value="${cli.id}">${cli.nom}${cli.prenom}</option>
								</c:when>
								<c:when test="${idClient != null && idClient == cli.id }">
									<option selected="selected" value="${cli.id}">${cli.nom}${cli.prenom}</option>
								</c:when>
							</c:choose>
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
								<th> Prix Unitaire </th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="piz" items="${listePizza}" varStatus="status">
								<tr>
									<td><label>${piz.key.nom}</label></td>
									<!-- <td><input class="form-control" name="test" type="number"></td> Un jour on pourra commander plusieurs fois la même pizza, mais c'est pour la v2 -->
									<td><c:choose>
											<c:when test="${piz.value }">
												<input checked type="checkbox" name="pizzaCommandeId"
													value="${piz.key.id}">
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="pizzaCommandeId"
													value="${piz.key.id}">
											</c:otherwise>
										</c:choose></td>
									<td> ${piz.key.prix} </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>


				<input class="btn btn-success" type="submit" value="Valider">
				<a href=<c:url value='/commandesNonLivrees/liste'/>><button type="button"
						class="btn btn-primary">Retour</button></a>
			</form>

		</div>
		<jsp:include page="../layout/footer.jsp" />