<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Lister Pizza" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />


<div class="container">
	<div class="row">
		<div class="col-md-9">
			<h1>Historique des pizzas</h1>
			<a class="btn btn-primary" href="../pizzas/liste">Retour</a> <br>
			<c:if test="${msg != null}">
				<div class="alert alert-danger" role="alert">${msg}</div>
			</c:if>
			<table class="table">
				<tr>
					<td>Image</td>
					<td>Informations</td>
					<td></td>
				</tr>		
				<c:forEach var="pizza" items="${listePizzas}">
					<tr>
						<td><img src="${pizza.urlImage}"></td>
						<td>
							<div class="row">
								<div class="col-md-6">
									Ref. ${pizza.id} 
									<br> 
									<b>${pizza.nom}</b>
									<br>
									${pizza.prix}€
									<br> 
									${pizza.versionPizza} 
									<br>
								</div>
								<div class="col-md-6">
									<c:if test="${pizza.archive}">
										<form method="POST">
											<input type="hidden" name="id" value="${pizza.id}"> 
											<input type="hidden" name="action" value="supprimer">
											<button type="submit" class="btn btn-success">Désarchiver</button>
										</form>
									</c:if>
									<c:if test="${!pizza.archive}">
										<form method="POST">
											<input type="hidden" name="id" value="${pizza.id}"> 
											<input type="hidden" name="action" value="supprimer">
											<button type="submit" class="btn btn-danger">Archiver</button>
										</form>
									</c:if>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>		
			</table>
		</div>

<jsp:include page="../layout/footer.jsp" />