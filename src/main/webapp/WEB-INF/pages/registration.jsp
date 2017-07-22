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
<title>SymposiumHub List Of Symposium</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/semantic.min.css' />">
<style type="text/css">
body {
	background-color: #FFFFFF;
}

.ui.menu .item img.logo {
	margin-right: 1.5em;
}

.main.container {
	margin-top: 7em;
}

.ui.footer.segment {
	margin: 5em 0em 0em;
	padding: 5em 0em;
}
</style>
</head>

<body>

	<%@ include file="include/header.jsp"%>

	<div class="ui main text container">

		<div class="row">

			<c:if test="${registraionFields==null}">
				<div class="ui message">
					<div class="header">Registrations not avaliable at symposiumhub please <a href="/event/${event.eventid}/${event.name}">contact organizers</a></div>
				</div>
			</c:if>

			<c:if test="${registraionFields!=null}">

				<form class="ui form segment" method="post"
					action="/eventregistration" modelAttribute="registration">

					<div class="ui message">
						<div class="header">Register for ${event.name}</div>

					</div>

					<c:if test="${registraionFields.fullName}">
						<div class="field">
							<label>Name</label> <input placeholder="Name" name="fullName"
								type="text">
						</div>
					</c:if>
					<c:if test="${registraionFields.phoneNumber}">

						<div class="field">
							<label>Phone Number</label> <input placeholder="phoneNumber"
								name="phoneNumber" type="text">
						</div>
					</c:if>
					<c:if test="${registraionFields.email}">

						<div class="field">
							<label>E-mail</label> <input placeholder="email" name="email"
								type="text">
						</div>
					</c:if>
					<c:if test="${registraionFields.collegeName}">

						<div class="field">
							<label>College Name</label> <input name="collegeName" type="text">
						</div>
					</c:if>
					<c:if test="${registraionFields.collegeId}">

						<div class="field">
							<label>CollegeId</label> <input name="collegeId" type="text">
						</div>
					</c:if>
					<input name="eventid" id="eventid" type="hidden"
						value="${event.eventid}">
					<button type="submit" class="ui blue submit button">Submit</button>
				</form>
				<c:if test="${isRegistered}">
					<div class="ui message">
						<div class="header">
							Thanks For registering.<a href="/">Click here to go home page</a>
						</div>

					</div>

				</c:if>
			</c:if>

		</div>


	</div>

	<%@ include file="include/footer.jsp"%>
</body>




</html>
