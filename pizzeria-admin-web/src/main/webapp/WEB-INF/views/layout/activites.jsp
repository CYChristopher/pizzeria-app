<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-md-3">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Liste des activités</h3>
		</div>
		<table class="table table-striped table-condensed">
			<tr>
				<th>Object</th>
				<th>Action</th>
				<th>Date</th>
			</tr>
			<c:forEach var="action" items="${activites }">
				<tr>
					<td>${action.type}</td>
					<td>${action.action}</td>
					<td>${action.date}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</div>
</div>
<!-- row -->
</div>
<!-- container -->