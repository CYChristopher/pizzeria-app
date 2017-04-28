<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Ajouter un client" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />


<div class="container">
	<div class="row">
		<div class="col-md-9">
			<div class="row">
				<div class="col-md-9">
					<h1>Ajout d'un clients</h1>
					<c:if test="${msg != null}">
						<div class="alert alert-danger" role="alert">${msg}</div>
					</c:if>
					<form class="form-horizontal" method="POST"
						action="<c:url value='/promotions/edit?id=${promotion.id}'/>">
						<div class="form-group">
							<label for="code" class="col-sm-2 control-label">Code</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="code" id="code"
									placeholder="Code" value="${promotion.code}" required>
							</div>
						</div>
						<div class="form-group">
							<label for="dateFinPromotion" class="col-sm-2 control-label">Date
								de fin de promotion</label>
							<div class="col-sm-10">
								<input type="date" class="form-control" name="dateFinPromotion"
									id="dateFinPromotion" placeholder="dd/MM/yyyy" value="${promotion.dateFinPromotion}" required>
							</div>
						</div>
						<div class="form-group">
							<label for="reductionEnPourcentage"
								class="col-sm-2 control-label">Reduction (en %)</label>
							<div class="col-sm-10">
								<input type="number" class="form-control"
									name="reductionEnPourcentage" id="reductionEnPourcentage"
									placeholder="Reduction (en %)" min="1" max="100" step="1" value="${promotion.reductionEnPourcentage}" required>
							</div>
						</div>
						<div class="form-group">
							<label for="pizza" class="col-sm-2 control-label">Pizza</label>
							<div class="col-sm-10">
								<select class="form-control" name="pizza" id="pizza">
									<c:forEach var="pizza" items="${pizzas}">
										<option value="${pizza.id}"
										<c:if test="${pizza.id == promotion.pizza.id}">selected</c:if>
										>${pizza.nom}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Editer</button>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>

		<jsp:include page="../layout/footer.jsp" />