<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Ajouter Client" name="title" />
</jsp:include>

<body class="container">
	<h1>Ajout d'un clients</h1>
	<form class="form-horizontal" method="POST" action="<c:url value='/clients/edit?id=${client.id}'/>">
	  <div class="form-group">
	    <label for="nom" class="col-sm-2 control-label">Nom</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="nom" id="nom" placeholder="Nom" value="${client.nom}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="prenom" class="col-sm-2 control-label">Prenom</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" name="prenom" id="prenom" placeholder="Prenom" value="${client.prenom}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="email" class="col-sm-2 control-label">Email</label>
	    <div class="col-sm-10">
	      <input type="email" class="form-control" name="email" id="email" placeholder="Email" value="${client.email}">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="motDePasse" class="col-sm-2 control-label">Mot de passe <a href="#">(Mot de passe oublié)</a></label>
	    <div class="col-sm-10">
	   	  <input type="password" class="form-control" name="motDePasse" id="motDePasse" placeholder="Mot de passe">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">Editer</button>
	    </div>
	  </div>
	</form>
</body>
</html>