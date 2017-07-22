<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<!-- Site Properties -->
<title>SymposiumHub ${event.name} Registration</title>
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

.bg-background-white {
	background: #ffffff;
}
</style>

</head>
<body>
	<%@ include file="include/header.jsp"%>
	<div class="ui main container">
		<%@ include file="include/message.jsp"%>

		<%-- <div class="ui  text container text-right">
			<a href="/step3/${eventId}" class="ui blue button">Proceed to next step <i
				class="arrow right icon"></i></a>
		</div> --%>

		<div class="ui text container mtop10 ">

			<c:if test="${preview}">
				<div class="ui segment bg-background-white">
					<div class="ui warning message">
						<i class="close icon"></i>
						<div class="header">This how your Form looks like to the user</div>
						 <a
							href="/registrationfields/${eventId}">Go back Symposiums Forms</a>
					</div>
				</div>
			</c:if>

			<div class="ui segment bg-background-white">
				<c:if test="${formexist eq false}">

					<h1 style="text-align: center">Register for ${event.name}</h1>
					<form class="ui form" id="" action="/eventregistration"
						method="POST">

						<c:forEach var="symposiumvar" items="${symposium}">

							<c:forEach var="symposiumInfo"
								items="${symposiumvar.symposiumFieldInfo}" varStatus="loop">
								<c:if test="${symposiumInfo.type eq 'text'}">

									<div class="field">
										<label style="font-size: 18px;">${symposiumInfo.name}
											: <i data-id="${symposiumInfo.id}"
											data-type="${symposiumInfo.type}"
											data-value="${symposiumInfo.name}" class="edit icon"
											style="display: none;"></i> <i style="display: none;"
											class="trash outline icon" data-id="${symposiumInfo.id}"></i>
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
												<input type="checkbox" id="${symposiumInfo.id}id"
													value="${symposiumInfo.name}" name="${symposiumvar.name}">

												<label for="${symposiumInfo.id}id">${symposiumInfo.name}
													: <i data-id="${symposiumInfo.id}"
													data-type="${symposiumInfo.type}"
													data-value="${symposiumInfo.name}" class="edit icon"
													style="display: none;"></i> <i style="display: none;"
													class="trash outline icon" data-id="${symposiumInfo.id}"></i>
												</label>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${symposiumInfo.type eq 'radio'}">
									<div class="grouped fields">

										<c:if test="${loop.first}">
											<label style="font-size: 18px; font-weight: bold;">${symposiumvar.name}</label>
										</c:if>
										<div class="field">
											<div class="ui radio checkbox">
												<input style="margin: 7px;" type="radio"
													id="${symposiumInfo.id}id" value="${symposiumInfo.name}"
													name="${symposiumvar.name}"> <label
													for="${symposiumInfo.id}id"
													style="font-size: 16px; font-weight: normal;">${symposiumInfo.name}

													<i data-id="${symposiumInfo.id}"
													data-type="${symposiumInfo.type}"
													data-value="${symposiumInfo.name}" class="edit icon"
													style="display: none;"></i> <i style="display: none;"
													class="trash outline icon" data-id="${symposiumInfo.id}"></i>
												</label>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${symposiumInfo.type eq 'textbox'}">

									<div class="field">
										<label style="font-size: 18px;">${symposiumInfo.name}
											: <i data-id="${symposiumInfo.id}"
											data-type="${symposiumInfo.type}"
											data-value="${symposiumInfo.name}" class="edit icon"
											style="display: none;"></i> <i style="display: none;"
											class="trash outline icon" data-id="${symposiumInfo.id}"></i>
										</label>
										<textarea id="fname" name="${symposiumInfo.name}"></textarea>
									</div>
								</c:if>
								<c:if test="${symposiumInfo.type eq 'dropdown'}">

									<div class="field">
										<label style="font-size: 18px;">${symposiumInfo.name}
											: <i data-id="${symposiumInfo.id}"
											data-type="${symposiumInfo.type}"
											data-value="${symposiumInfo.name}" class="edit icon"
											style="display: none;"></i> <i style="display: none;"
											class="trash outline icon" data-id="${symposiumInfo.id}"></i>
										</label><input type="text" id="fname" name="${symposiumInfo.name}">
									</div>
								</c:if>

							</c:forEach>
						</c:forEach>
						<input type="hidden" value="${eventId}" id="eventId"
							name="eventId">

						<div style="text-align: center;">
							<button style="font-size: 18px;" class="ui blue button "
								type="submit">Submit</button>
						</div>
					</form>
				</c:if>


				<c:if test="${formexist}">
					<div class="ui warning message">
						<i class="close icon"></i>
						<div class="header">Registration not available on
							Symposiumhub</div>
						Contact Organizers in view page <a
							href="/event/${eventId}/${event.name}">Click here to visit
							${event.name}</a>
					</div>

				</c:if>
			</div>
		</div>

		<div id="addfiedpopup" class="ui modal small modalwidth">
			<i class="close icon"></i>
			<div class="header">Add field</div>
			<div class="content">
				<div class="">
					<div class="column">
						<form class="ui form"
							action="<c:url value='/registrationfields' />" method="post">
							<div class="field">
								<label>Field Name</label> <input type="text" name="fieldName"
									placeholder="fieldName"><br> <label>Field
									Type</label> <select name="fieldType">
									<option value="">Choose</option>
									<option value="text">Text</option>
									<option value="checkbox">Checkbox</option>
									<option value="textbox">textbox</option>
									<option value="radio">radio</option>
									<option value="dropdown">dropdown</option>
								</select>

							</div>
							<input type="hidden" value="${eventId}" id="eventId"
								name="eventId">

							<button class="ui button" type="submit">Submit</button>
						</form>
					</div>

				</div>

			</div>
			<div class="actions"></div>
		</div>

		<div id="addoption" class="ui  small modal modalwidth">
			<i class="close icon"></i>
			<div class="header">addoption</div>
			<div class="content">
				<div class="">
					<div class="column">
						<form class="ui form"
							action="<c:url value='/registrationfields' />" method="post">
							<div class="field">
								<label>Option Name</label> <input id="optionName"
									autocomplete="off" type="text" name="optionName"
									placeholder="optionName">

							</div>

							<button class="ui button addoptionform" type="button">Submit</button>
						</form>
					</div>

				</div>

			</div>
			<div class="actions"></div>
		</div>

		<div id="editoption" class="ui  small modal modalwidth">
			<i class="close icon"></i>
			<div class="header">editoption</div>
			<div class="content">
				<div class="">
					<div class="column">
						<form class="ui form" action="<c:url value='/editoption' />"
							method="post">
							<div class="field">
								<label>Field Name</label> <input id="editname" type="text"
									name="name" placeholder="fieldName"><br> <label>Field
									Type</label> <select id="edittype" name="type">
									<option value="">Choose</option>
									<option value="text">Text</option>
									<option value="checkbox">Checkbox</option>
									<option value="textbox">textbox</option>
									<option value="radio">radio</option>
									<option value="dropdown">dropdown</option>
								</select>

							</div>
							<input type="hidden" id="editid" name="id">
							<button class="ui button" type="submit">Submit</button>
						</form>
					</div>

				</div>

			</div>

			<div class="actions"></div>

		</div>
	</div>
	<%@ include file="include/footer.jsp"%>

	<script src="<c:url value='/resources/assets/library/jquery.min.js' />"></script>
	<%-- <script
		src="<c:url value='/resources/dist/jquery-ui-1.12.1.custom/jquery-ui.min.js' />"></script> --%>
	<script src="<c:url value='/resources/dist/components/form.min.js' />">
		
	</script>

	<script
		src="<c:url value='/resources/dist/components/transition.min.js' />">
		
	</script>
	<script
		src="<c:url value='/resources/dist/components/dimmer.min.js' />">
		
	</script>
	<script src="/resources/dist/components/modal.min.js"></script>

	<script src="/resources/dist/components/checkbox.min.js"></script>
</body>
</html>