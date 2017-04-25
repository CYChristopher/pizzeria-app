<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Editer Commandes" name="title" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp" />

<div class="container">

	<h1>Edition</h1>

	<form method="post">

		<div class="form-group">
			<label for="numCmd">Numero de Commande :</label> <input id="numCmd"
				class="form-control" name="newcode" type="text"
				value="${editCommande.numeroCommande}">
		</div>

		<div class="form-group">
			<label for="statut">Statut :</label> <select class="form-control"
				id="statut">
				<c:forEach var="cmd" items="${editCommande.statut}">
					<option>1</option>
				</c:forEach>
			</select>
		</div>

		<input class="btn btn-success" type="submit" value="Valider">
		<a href=<c:url value='/commandes/list'/>><button type="button"
				class="btn btn-primary">Retour</button></a>
	</form>

</div>
<jsp:include page="../layout/footer.html" />
