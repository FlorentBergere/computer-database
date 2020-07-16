<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.applicationName"/> </title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"> <spring:message code="label.applicationName"/> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${ nbComputer } <spring:message code="dashboard.computerFound" /></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="<spring:message code="dashboard.searchName"/>" /> <input
							type="submit" id="searchsubmit" value="<spring:message code="dashboard.searchNameButton"/>"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="dashboard.addComputerButton"/></a> 
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="dashboard.editComputerButton"/></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a href="dashboard?orderBy=computerName"> <spring:message code="dashboard.computerName"/> </a></th>
						<th><spring:message code="dashboard.computerIntroduced"/></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="dashboard.computerDiscontinued"/></th>
						<!-- Table header for Company -->
						<th><spring:message code="dashboard.computerCompany"/></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="computer" items="${ computerDTOCollection }">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value=${ computer.id }></td>
							<td><a href="editComputer?computerId=${ computer.id }&computerName=${ computer.name }&introduced=${computer.introduced}&discontinued=${computer.discontinued}&companyId=${computer.getCompany().id}" onclick="">${ computer.name }</a>
							</td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.getCompanyName()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${ pageNumber != 0}">
					<li><a
						href="dashboard?nbEntryPerPage=<c:set var="nbEntryPerPage" value="${(empty nbEntryPerPage) ? 10 : nbEntryPerPage}"/>${ nbEntryPerPage }&pageNumber=${ pageNumber-1 }"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:forEach var="i" items="${ listPage }">
					<li <c:if test="${i == pageNumber}"> class="active"</c:if>><a
						href="dashboard?nbEntryPerPage=<c:set var="nbEntryPerPage" value="${(empty nbEntryPerPage) ? 10 : nbEntryPerPage}"/>${ nbEntryPerPage }&pageNumber=${ i }">${ i }</a></li>
				</c:forEach>
				<c:if test="${ pageNumber != nbPage }">
					<li><a
						href="dashboard?nbEntryPerPage=<c:set var="nbEntryPerPage" value="${(empty nbEntryPerPage) ? 10 : nbEntryPerPage}"/>${ nbEntryPerPage }&pageNumber=${pageNumber+1 }"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">

				<a href="dashboard?nbEntryPerPage=10"><button type="button" class="btn btn-default">10</button></a> 
				<a href="dashboard?nbEntryPerPage=50"><button type="button" class="btn btn-default">50</button></a> 
				<a href="dashboard?nbEntryPerPage=100"><button type="button" class="btn btn-default">100</button></a>


			</div>
		</div>

	</footer>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/dashboard.js"></script>

</body>
</html>