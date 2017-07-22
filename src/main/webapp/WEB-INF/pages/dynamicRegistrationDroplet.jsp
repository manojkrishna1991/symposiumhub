<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<!-- Site Properties -->
<title>SymposiumHub Registrations ${event.name}</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/common.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/semantic.min.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/jquery-ui-1.12.1.custom/jquery-ui.min.css' />">




<style type="text/css">
body {
	background-color: #EAEAEA;
}

.ui.menu .item img.logo {
	margin-right: 1.5em;
}

.main.container {
	margin-top: 7em;
}

.wireframe {
	margin-top: 2em;
}

.ui.footer.segment {
	margin: 5em 0em 0em;
	padding: 5em 0em;
}

.pinkbackground {
	background-color: #e03997 !important;
	color: rgba(255, 255, 255, 0.9) !important;
}

.custContainer {
	padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto
}

@media ( min-width :768px) {
	.custContainer {
		width: 750px
	}
}

@media ( min-width :992px) {
	.custContainer {
		width: 900px
	}
}

@media ( min-width :1200px) {
	.custContainer {
		width: 992px
	}
}
</style>
</head>
<body>
	<%@ include file="include/header.jsp"%>

	<div class="ui main container">

		<table class="ui celled table" id="example">
			<thead>
				<tr>
					<c:forEach var="symposiumvar" items="${symposium}">
						<c:forEach var="symposiumInfo"
							items="${symposiumvar.symposiumFieldInfo}" varStatus="loop">
							<th>${symposiumInfo.name}</th>
						</c:forEach>
					</c:forEach>
					<th>EventId</th>
				</tr>
				
			</thead>
			<tbody>

				<c:forEach var="symposiumregister" items="${DynamicFormForm}">
					<tr>
						<c:forEach var="symposiumregdata"
							items="${symposiumregister.values}">
							<td>${symposiumregdata.value}</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
		
</body>
	<script src="/resources/assets/library/jquery.min.js"></script>

	<script src="/resources/dist/components/transition.min.js">
		
	</script>
	<script src="/resources/dist/components/dropdown.min.js">
		
	</script>
	
	<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

<%@ include file="include/footer.jsp"%>
	
	<script>
	$(document).ready(function() {
	    $('#example').DataTable();
	} );
	</script>
</html>