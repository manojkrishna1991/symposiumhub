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
<title>Symposium-Hub List Of Symposium</title>
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
			<form class="ui form segment" method="post"
				action="/eventregistration" modelAttribute="eventregistration">

				<div class="ui message">
					<div class="header">Register for ${event.name}</div>

				</div>
				<div class="field">
					<label>Name</label> <input placeholder="Name" name="fullName"
						type="text">
				</div>

				<div class="field">
					<label>Phone Number</label> <input placeholder="phoneNumber"
						name="phoneNumber" type="text">
				</div>

				<div class="field">
					<label>E-mail</label> <input placeholder="email" name="email"
						type="text">
				</div>

				<div class="field">
					<label>College Name</label> <input name="collegeName" type="text">
				</div>

				<div class="field">
					<label>CollegeId</label> <input name="CollegeId" type="text">
				</div>

				<input name="eventId" id="eventId" type="hidden" value="${event.id}">
				<button type="submit" class="ui blue submit button">Submit</button>
			</form>
			<c:if test="${isRegistered}">
			<div class="ui message">
					<div class="header">Thanks For registering.<a href="/">Click here to go home page</a></div>

				</div>

			</c:if>

		</div>
		
		<div class="row">
		<form class="ui form" id="" action="/dynamicRegistrationFormHandler"
				method="get">

				<c:forEach var="symposiumvar" items="${symposium}">

					<c:forEach var="symposiumInfo"
						items="${symposiumvar.symposiumFieldInfo}" varStatus="loop">
						<c:if test="${symposiumInfo.type eq 'text'}">

							<div class="field">
								<label style="font-size: 18px;">${symposiumInfo.name} : <i
									data-id="${symposiumInfo.id}" data-type="${symposiumInfo.type}"
									data-value="${symposiumInfo.name}" class="edit icon"
									style="display: none;"></i>
									<i style="display: none;" class="trash outline icon" data-id="${symposiumInfo.id}"></i>
								</label><input type="text" id="fname" name="${symposiumInfo.name}">
							</div>
						</c:if>
						<c:if test="${symposiumInfo.type eq 'checkbox'}">
						<div class="grouped fields">
							<c:if test="${loop.first}">
								<label style="font-size: 18px;">${symposiumvar.name}</label>
							</c:if>
							<div class="field">
							      <div class="ui  checkbox">
							       <input  type="checkbox" id="${symposiumInfo.id}id"
									value="${symposiumInfo.name}" name="${symposiumvar.name}">	
									
								<label for="${symposiumInfo.id}id" >${symposiumInfo.name} :
								
								 <i
									data-id="${symposiumInfo.id}" data-type="${symposiumInfo.type}"
									data-value="${symposiumInfo.name}" class="edit icon"
									style="display: none;"></i>
									
									<i style="display: none;" class="trash outline icon" data-id="${symposiumInfo.id}"></i>
									</label>
									</div>
							</div>
							</div>
							<c:if test="${loop.last}">
								<button data-id="${symposiumvar.id}" data-type="checkbox"
									onclick="dynobutton(this)" type="button"
									class="ui button dyno-button">Add CheckBox</button>
							</c:if>
						</c:if>
						<c:if test="${symposiumInfo.type eq 'radio'}">
							<div class="grouped fields">

								<c:if test="${loop.first}">
									<label style="font-size: 18px; font-weight: bold;">${symposiumvar.name}</label>
								</c:if>
								<div class="field">
									<div class="ui radio checkbox">
									 <input style="margin: 7px;" type="radio" id="${symposiumInfo.id}id"
											value="${symposiumInfo.name}" name="${symposiumvar.name}">
										<label for="${symposiumInfo.id}id" style="font-size: 16px; font-weight: normal;">${symposiumInfo.name}
											
											<i data-id="${symposiumInfo.id}"
											data-type="${symposiumInfo.type}"
											data-value="${symposiumInfo.name}" class="edit icon"
											style="display: none;"></i> <i style="display: none;"
											class="trash outline icon" data-id="${symposiumInfo.id}"></i>
										</label>
									</div>
								</div>
							</div>
							<c:if test="${loop.last}">
								<button data-id="${symposiumvar.id}" data-type="radio"
									onclick="dynobutton(this)" type="button"
									class="ui button dyno-button">Add Radio</button>
							</c:if>
						</c:if>
						<c:if test="${symposiumInfo.type eq 'textbox'}">

							<div class="field">
								<label style="font-size: 18px;">${symposiumInfo.name} : <i
									data-id="${symposiumInfo.id}" data-type="${symposiumInfo.type}"
									data-value="${symposiumInfo.name}" class="edit icon"
									style="display: none;"></i>
								   <i style="display: none;" class="trash outline icon" data-id="${symposiumInfo.id}"></i>
								</label>
								<textarea id="fname" name="${symposiumInfo.name}"></textarea>
							</div>
						</c:if>
						<c:if test="${symposiumInfo.type eq 'dropdown'}">

							<div class="field">
								<label style="font-size: 18px;">${symposiumInfo.name} : <i
									data-id="${symposiumInfo.id}" data-type="${symposiumInfo.type}"
									data-value="${symposiumInfo.name}" class="edit icon"
									style="display: none;"></i>
								    <i style="display: none;" class="trash outline icon" data-id="${symposiumInfo.id}"></i>
									</label><input type="text" id="fname"
									name="${symposiumInfo.name}">
							</div>
						</c:if>

					</c:forEach>
				</c:forEach>
				<input type="hidden" value="${event.id}" id="eventId" name="eventId" >
				
				<div style="text-align: center;">
				<button  style="font-size: 18px;" class="ui blue button " type="submit">Submit</button>
				</div>
			</form>
		</div>
		
	</div>

	<%@ include file="include/footer.jsp"%>
</body>




</html>
