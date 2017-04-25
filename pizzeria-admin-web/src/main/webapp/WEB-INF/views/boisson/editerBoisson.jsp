<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Editer Boisson ${boisson.code} " />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>
	<div class="jumbotron">
		<div class="container">

	<h1>Editer Dessert ${boisson.code} </h1>
	
	<c:if test="${msg != null}">
		<div class="alert alert-danger" role="alert">${msg}</div>
	</c:if>

	<form method="post">

		<div class="form-group">
			<label>Code :</label> 
			<input 	class="form-control" name="code" type="text" value="${boisson.code}">
		</div>

		<div class="form-group">
			<label>Nom :</label> 
			<input  name="nom" class="form-control" type="text" value="${boisson.nom}">
		</div>

		<div class="form-group">
			<label>Prix : </label> <input step="0.01" id="number"
				name="prix" class="form-control" type="number"
				value="${boisson.prix}">
		</div>



		<input class="btn btn-success" type="submit" value="Valider">
		<a href=<c:url value='/boissons/list'/>><button type="button"
				class="btn btn-primary">Retour</button></a>
	</form>

	</div>

</div>

<jsp:include page="../layout/footer.html"/>