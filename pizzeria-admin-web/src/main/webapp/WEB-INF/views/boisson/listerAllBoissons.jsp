<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Liste des Boissons" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

	
<div class="container">
	<div class="row">
		<div class="col-md-9">
			<h1>Liste des Boissons</h1>
			<a class="btn btn-primary" href="<c:url value="/boisson/nouvelle"/>">Nouvelle Boisson</a> 
			<br>
			<table class="table">
				<thead>
					<tr>
						<th>Image</th>
						<th>Code</th>
						<th>Nom</th>
						<th>Prix</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="boisson" items="${listeBoissons}">
					<tr>
						<td><img src="${boisson.urlImage}"></td>
						<td>${boisson.code}</td>
						<td>${boisson.nom}</td>
						<td>${boisson.prix} €</td>
						<td>
							<a href="<c:url value="/boissons/edit?id=${boisson.id}"/>" class="btn btn-primary">Editer</a>
						</td>
						<td> 
							<c:if test="${boisson.archive}">						
								<form method="POST">
									<input type="hidden" name="id" value="${boisson.id}">
									<input type="hidden" name="action" value="supprimer">
									<button type="submit" class="btn btn-danger">Désarchiver</button>
								</form>
							</c:if>	
							<c:if test="${!boisson.archive}">						
								<form method="POST">
									<input type="hidden" name="id" value="${boisson.id}">
									<input type="hidden" name="action" value="supprimer">
									<button type="submit" class="btn btn-success">Archiver</button>
								</form>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
<jsp:include page="../layout/footer.jsp" />
