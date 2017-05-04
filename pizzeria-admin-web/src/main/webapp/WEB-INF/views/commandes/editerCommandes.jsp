<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Edition Commande" name="title" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />


<div class="container">
	<div class="row">
		<div class="col-md-9">

			<h1>Edition</h1>
			<c:if test="${msg != null}">
				<div class="alert alert-danger" role="alert">${msg}</div>
			</c:if>

			<form method="post">

				<div class="form-group">
					<label>Statut :</label> <select class="form-control" name="statut"
						required>
						<c:forEach var="status" items="${statusPossible}">
							<option <c:if test="${commande.statut == status}">selected</c:if>>${status}</option>
						</c:forEach>
					</select>
				</div>


				<div class="form-group">
					<label class="col-md-4 control-label" for="radios"> Type de
						commande : </label>
					<div class="col-md-4">
						<div class="radio-inline">
							<label for="radios-0"> <input type="radio" name="radios"
								id="radios-0" value="LIV" checked="checked"> Livraison
							</label>
						</div>
						<div class="radio-inline">
							<label for="radios-1"> <input type="radio" name="radios"
								id="radios-1" value="EMP"> A emporter
							</label>
						</div>
					</div>
				</div>


				<div class="form-group">
					<c:choose>
						<c:when test="${adresse == null}">
							<label>Adresse : (obligatoire pour livraison)</label>
							<input class="form-control" name="adresse" type="text">
						</c:when>
						<c:otherwise>
							<label>Adresse :</label>
							<input class="form-control" name="adresse" type="text"
								value="${adresse }">
						</c:otherwise>
					</c:choose>
				</div>

				<div class="form-group">
					<label>Livreur : (obligatoire pour livraison)</label> <select
						class="form-control" name="livreur">
						<c:forEach var="liv" items="${listeLivreur}">
							<c:choose>
								<c:when test="${idLivreur == null || liv.id != idLivreur }">
									<option value="${liv.id}">${liv.nom}${liv.prenom}</option>
								</c:when>
								<c:when test="${idLivreur != null && liv.id == idLivreur }">
									<option value="${liv.id}">${liv.nom}${liv.prenom}</option>
								</c:when>
							</c:choose>
						</c:forEach>
						<option selected="selected" value="noLiv"></option>
					</select>
				</div>


				<div class="form-group">
					<label>Client :</label> <select class="form-control" name="client"
						required>
						<c:forEach var="cli" items="${listeClient}">
							<option value="${cli.id}"
								<c:if test="${commande.client.id == cli.id}">selected</c:if>>${cli.nom}
								${cli.prenom}</option>
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
									<td><label>${piz.nom}</label></td>
									<!-- <td><input class="form-control" name="test" type="number"></td> Un jour on pourra commander plusieurs fois la même pizza, mais c'est pour la v2 -->
									<td><input type="checkbox" name="pizzaCommandeId"
										value="${piz.id}"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<input class="btn btn-success" type="submit" value="Valider">
				<a href=<c:url value='/commandesNonLivrees/liste'/>><button type="button"
						class="btn btn-primary">Retour</button></a> <a href=<c:url value=''/>>
					<button type="button" class="btn btn-primary">Reset</button>
				</a>
			</form>

		</div>
		<jsp:include page="../layout/footer.jsp" />