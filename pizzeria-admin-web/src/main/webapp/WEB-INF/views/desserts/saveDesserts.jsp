<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param name="title" value="Nouveau Dessert"/>
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="container">
	<div class="row">
		<div class="col-md-9">

			<h1>Nouveau Dessert</h1>

			<form method="post">
				<label for="newcode">Code :</label>
				<input style="border-color:${erreur[0]}" class="form-control" id="newcode" name="newcode" type="text" placeholder="Code" value="${newcode}">

					<label for="ref">Nom :</label>
					<input style="border-color:${erreur[1]}" class="form-control" id="ref" name="ref" type="text" placeholder="Nom" value="${ref}">

						<label for="prix">Prix :</label>
						<input style="border-color:${erreur[2]}" step="0.01" class="form-control" id="prix" name="prix" type="number" placeholder="prix" value="${prix}">

							<br>
								<button class="btn btn-success" type="submit">Valider</button>

								<a href='./liste'>
									<button type="button" class="btn btn-primary">Retour</button>
								</a>
							</form>

						</div>

						<jsp:include page="../layout/footer.jsp"/>
