<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Liste des Clients" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />


<div class="container">
	<div class="row">
		<div class="col-md-9">

			<h1>Liste des promotions</h1>
			<a class="btn btn-primary" href="new">Nouvelle promotion</a> <br>
			<c:if test="${msg != null}">
				<div class="alert alert-danger" role="alert">${msg}</div>
			</c:if>

			<table class="table">
				<thead>
					<tr>
						<th>Ref</th>
						<th>Code</th>
						<th>Date de fin de la promotion</th>
						<th>Réduction (en %)</th>
						<th>Pizza</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="promotion" items="${promotions}">
						<tr>
							<td>${promotion.id}</td>
							<td>${promotion.code}</td>
							<td>${promotion.dateFinPromotion}</td>
							<td>${promotion.reductionEnPourcentage}</td>
							<td>${promotion.pizza.nom}</td>
							<td><a href="<c:url value="/promotions/edit?id=${promotion.id}"/>"
								class="btn btn-warning">Editer</a></td>
							<td>
								<form method="POST"
									action="<c:url value='/promotions/delete?id=${promotion.id}'/>">
									<button type="submit" class="btn btn-danger">Supprimer</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<jsp:include page="../layout/footer.jsp" />