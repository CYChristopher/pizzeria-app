<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Lister Commandes" name="title" />
</jsp:include>

<jsp:include page="../layout/navbar.jsp"/>

<div class="container">

<h1> Commandes</h1>

<table class="table">
  <thead>
    <tr>
			<th>Id</th>
			<th>Numero</th>
     		<th>Statut</th>
			<th>Date</th>
			<th>Livreur</th>
			<th>Client</th>
    </tr>
  </thead>
  <tbody>
<c:forEach var="cmd" items="${listeCommandes}">
		<tr>
			<td>${cmd.id}</td>
			<td>${cmd.numeroCommande}</td>
			<td>${cmd.statut}</td>
			<td>${cmd.dateCommande}</td>
			<td>${cmd.livreur}</td>
			<td>${cmd.client}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

</div>
<jsp:include page="../layout/footer.html"/>
