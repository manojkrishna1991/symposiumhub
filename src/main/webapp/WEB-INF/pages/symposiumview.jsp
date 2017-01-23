<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<!-- Site Properties -->
<title>${symposiumview.name}</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/semantic.css' />">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link href="/resources/dist/components/jquery.tagit.css"
	rel="stylesheet" type="text/css">
<link href="/resources/dist/components/tagit.ui-zendesk.css"
	rel="stylesheet" type="text/css">
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

.wireframe {
	margin-top: 2em;
}

.ui.footer.segment {
	margin: 5em 0em 0em;
	padding: 5em 0em;
}

label {
	color: black;
	font-weight: bold;
}

.hiddenfile {
	width: 0px;
	height: 0px;
	overflow: hidden;
}

.ui.form.step1 {
	padding: 20px;
}

.ui-widget-content.ui-autocomplete-input {
	color: #333333 ! important;
	background: none ! important;
	outline: none ! important;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	-moz-box-shadow: none;
	-webkit-box-shadow: none;
	box-shadow: none ! important;
	border: none ! important;
	margin: 0 ! important;
	padding: 0 ! important;
	width: inherit ! important;
	background-color: inherit ! important;
	outline: none ! important;
}
</style>

</head>
<body>

	<%@ include file="include/header.jsp"%>



	<div class="ui grid" style="margin: 100px 13px;">
		<h1 class="ui horizontal divider header" style="color: #21ba45;">
			<i class="tag icon"></i>
			${symposiumview.name}(${symposiumview.department})
		</h1>
		<div
			class="sixteen wide mobile eight wide tablet sixteen wide computer column">
			<h4 class="ui dividing header">
				Starts From
				<fmt:formatDate type="date" value="${symposiumview.dateOfEvent}" />
				<c:if
					test="${not empty authenticated and symposiumview.userId eq personid}">
					<a class="ui primary button "
						href="/editsymposium/${symposiumview.symposiumid}"
						style="margin-left: 10px;"> EDIT </a>
				</c:if>
			</h4>
			<div class="ui three item menu">
				<a data-tab="one" class="item ">Photo</a> <a data-tab="two"
					class="item active">Venue</a> <a data-tab="fifth" class="item">Register</a>
			</div>
			<div class="ui tab segment " data-tab="one">


				<div class="ui special cards">
					<c:if test="${symposiumview.imageUrl ==null }">
						<div style="width: 100%;" class="card">
							<c:if test="${ empty authenticated }">

								<div class="image">
									<img style="width: 100%;"
										src="/resources/assets/images/wireframe/image.png">
								</div>
							</c:if>
							<c:if
								test="${not empty authenticated and symposiumview.userId eq personid}">
								<div class="blurring dimmable image1">
									<div class="ui dimmer">
										<div class="content">


											<div class="center backgroundimage"
												style="vertical-align: top !important;">
												<div class="ui inverted button">Add Photo</div>
											</div>


										</div>
									</div>
									<img id="backgroundimagedisplay" style="width: 100%;"
										src="/resources/assets/images/wireframe/image.png">
								</div>
							</c:if>

							<c:if
								test="${not empty authenticated and symposiumview.userId ne personid}">
								<div class="image">
									<img style="width: 100%;"
										src="/resources/assets/images/wireframe/image.png">
								</div>
							</c:if>

							<div class="content">
								<div class="header">Event Name</div>
								<div class="meta">
									<a class="group">${symposiumview.name}</a>
								</div>
								<%--  <div class="description">${symposiumview.impDescription}</div> --%>
							</div>
							<div class="extra content">
								<a class="right floated created"></a> <a class="friends"> </a>
							</div>
						</div>
					</c:if>

					<c:if test="${symposiumview.imageUrl !=null }">
						<div style="width: 100%;" class="card">
							<c:if test="${ empty authenticated}">

								<div class="image">
									<img style="width: 100%;" src="${symposiumview.imageUrl}">
								</div>
							</c:if>
							<c:if
								test="${not empty authenticated and (symposiumview.userId eq personid)}">
								<div class="blurring dimmable image1">
									<div class="ui dimmer">
										<div class="content">


											<div class="center backgroundimage"
												style="vertical-align: top !important;">
												<div class="ui inverted button">Change Photo</div>
											</div>


										</div>
									</div>
									<img id="backgroundimagedisplay" style="width: 100%;"
										src="${symposiumview.imageUrl}">
								</div>
							</c:if>
							<c:if
								test="${not empty authenticated and (symposiumview.userId ne personid) }}">
								<div class="image">
									<img style="width: 100%;" src="${symposiumview.imageUrl}">
								</div>
							</c:if>

							<div class="content">
								<div class="header">Department</div>
								<div class="meta">
									<a class="group">${symposiumview.department}</a>
								</div>
								<div class="description">${symposiumview.impDescription}</div>
							</div>
							<div class="extra content">
								<a class="right floated created"></a> <a class="friends"> </a>
							</div>
						</div>
					</c:if>
				</div>
			</div>
			<div class="ui tab segment  active" data-tab="two">


				<div class="ui list">
					<div class="item">
						<i class="users icon"></i>
						<div class="content">
							<label>CollegeName:</label> ${symposiumview.collegeName}
						</div>
					</div>
					<div class="item">
						<i class="marker icon"></i>
						<div class="content">
							<label>Address:</label> ${symposiumview.address}

						</div>
					</div>
					<div class="item">
						<i class="mail icon"></i>
						<div class="content">
							<a style="color: black;" href="mailto:${symposiumview.regEmail}">
								<label>Email: </label> ${symposiumview.regEmail}
							</a>

						</div>
					</div>
					<c:if test="${not empty symposiumview.events}">
						<div class="item">
							<i class="tasks icon"></i>
							<div class="content">
								<a style="color: black;" href="#"> <label>Events:</label>${symposiumview.events}</a>

							</div>
						</div>
					</c:if>
					<c:if test="${not empty symposiumview.symposiumPapers}">

						<div class="item">
							<i class="file text icon"></i>
							<div class="content">
								<a style="color: black;" href="#"> <label> Symposium
										Papers:</label> ${symposiumview.symposiumPapers}
								</a>

							</div>
						</div>
					</c:if>
					<c:if test="${not empty symposiumview.mobileNo}">
						<div class="item">
							<i class="file telephone icon"></i>
							<div class="content">
								<a style="color: black;" href="#"> <label> Phone:</label>
									${symposiumview.mobileNo}
								</a>

							</div>
						</div>
					</c:if>

					<div class="item">
						<i class="linkify icon"></i>
						<div class="content">
							<a style="color: black;" href="${symposiumview.webAddress}">
								<label>Website Address:</label> ${symposiumview.webAddress}
							</a>

						</div>
					</div>
					<c:if test="${not empty symposiumview.paymentDetail}">
						<div class="item">
							<i class="file payment icon"></i>
							<div class="content">
								<a style="color: black;" href="#"> <label>
										Registration Details:</label> ${symposiumview.paymentDetail}
								</a>

							</div>
						</div>
					</c:if>
					<c:if test="${not empty symposiumview.fromDate}">
						<div class="ui positive message">

							<div class="header">Last Date for Full Paper</div>
							<p>
								<fmt:formatDate type="date" value="${symposiumview.fromDate}" />

							</p>
						</div>
					</c:if>
					<c:if test="${not empty symposiumview.toDate}">
						<div class="ui positive message">

							<div class="header">Date of Intimation for Accepted paper</div>
							<p>
								<fmt:formatDate type="date" value="${symposiumview.toDate}" />

							</p>
						</div>
					</c:if>

					<c:if test="${not empty symposiumview.impDescription}">
						<div class="ui positive message">

							<div class="header">Important Information</div>
							<p>${symposiumview.impDescription}</p>
						</div>
					</c:if>
					<c:if test="${not empty symposiumview.coordinators}">
						<table class="ui single line table">
							<thead>
								<tr>
									<th>Coordinator Name</th>
									<th>Coordinator Email</th>
									<th>Coordinator phone</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${symposiumview.coordinators}" var="item">



									<tr>
										<td>${item.name}</td>
										<td>${item.emailid}</td>
										<td>${item.phoneno}</td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
				<div class="ui message">
					<div class="header">Not Interested View Other Symposiums</div>
					<p>
						<a href="/">click here</a>.
					</p>
				</div>

			</div>
			<div class="ui tab segment " data-tab="fifth">
				<form class="ui form segment" method="post"
					action="/registerforsymposium" modelAttribute="regsymposium">

					<c:if test="${symposiumview.fields.fullName}">
						<div class="field">
							<label>Name</label> <input placeholder="Name" name="fullName"
								type="text">
						</div>
					</c:if>
					<c:if test="${symposiumview.fields.phoneNumber}">

						<div class="field">
							<label>Phone Number</label> <input placeholder="phoneNumber"
								name="phoneNumber" type="text">
						</div>
					</c:if>
					<c:if test="${symposiumview.fields.email}">

						<div class="field">
							<label>E-mail</label> <input placeholder="email" name="email"
								type="text">
						</div>
					</c:if>
					<c:if test="${symposiumview.fields.collegeName}">

						<div class="field">
							<label>College Name</label> <input name="collegeName" type="text">
						</div>
					</c:if>
					<c:if test="${symposiumview.fields.collegeId}">

						<div class="field">
							<label>CollegeId</label> <input name="CollegeId" type="text">
						</div>
					</c:if>

					<input name="symposiumIddoto" id="symposiumIddoto" type="hidden"
						value="${symposiumview.symposiumid}">
						<c:choose>
					<c:when  test="${symposiumview.fields.fullName || symposiumview.fields.phoneNumber || symposiumview.fields.email || symposiumview.fields.collegeName || symposiumview.fields.collegeId }">
						<button type="submit" class="ui blue submit button">Submit</button>
					</c:when>
					 <c:otherwise>
						<div class="ui message">
							<div class="header">Note</div>
							<p>Registrations are not available for this Event at SymposiumHub.Please contact Event Organizers in venue Tab. </p>
						</div>

			       </c:otherwise>
					</c:choose>
				</form>
			</div>
		</div>
	</div>


	<script src="<c:url value='/resources/assets/library/jquery.min.js' />"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<script src="<c:url value='/resources/dist/components/tab.min.js' />">
		
	</script>
	<script src="<c:url value='/resources/dist/components/form.min.js' />">
		
	</script>
	<script
		src="<c:url value='/resources/dist/components/transition.min.js' />">
		
	</script>
	<script
		src="<c:url value='/resources/dist/components/dimmer.min.js' />">
		
	</script>
	<script src="/resources/dist/components/modal.min.js"></script>
	<script
		src="<c:url value='/resources/dist/components/dropdown.min.js' />">
		
	</script>
	<script src="/resources/dist/components/checkbox.min.js"></script>
	<script src="/resources/dist/tag-it.js" type="text/javascript"
		charset="utf-8"></script>
	<%@ include file="include/footer.jsp"%>
	<div class="hiddenfile">
		<input type="file" id="backgroundimageupload" />
	</div>
</body>
<script>
	$('.menu .item').tab();
</script>
<script>
	$('.ui.form').form({
		fields : {
			fullName : 'empty',
			phoneNumber : [ 'number', 'exactLength[10]', 'empty' ],
			email : [ 'email', 'empty' ],
			collegeName : 'empty'
		},
		inline : true,
		on : 'blur'
	});

	$(document).ready(function() {
		$('.ui.selection.dropdown').dropdown();
		$('.ui.menu .ui.dropdown').dropdown({
			on : 'hover'
		});
	});

	$('.image1').dimmer({
		on : 'hover'
	});

	$(".backgroundimage").click(function() {
		$('#backgroundimageupload').trigger('click');

	});

	$('#backgroundimageupload').change(function() {

		var $input = $(this);
		var inputFiles = this.files;
		var inputFile = inputFiles[0];
		alert("avatar upload");
		var oMyForm = new FormData();
		oMyForm.append("file", inputFile);
		oMyForm.append("symposiumId", $("#symposiumIddoto").val());
		uploadImage(oMyForm, '/upload1', "#backgroundimagedisplay");

	});
	function uploadImage(oMyForm, controllerUrl, divIdtodisplayimage) {
		jQuery.ajax({
			url : controllerUrl,
			data : oMyForm,
			dataType : 'text',
			processData : false,
			contentType : false,
			type : 'POST',
			success : function(data) {
				$(divIdtodisplayimage).attr("src",
						data + "?" + new Date().getTime());
			}
		});

	}
</script>

<!-- edit popup functionality  -->

<jsp:include page="editpopup.jsp" />

</html>
