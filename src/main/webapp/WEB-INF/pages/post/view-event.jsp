<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<!-- Site Properties -->
<title>${title}</title>

<link rel="stylesheet" type="text/css"
	href="/resources/dist/semantic.min.css">



<link rel="stylesheet" type="text/css" href="/resources/slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/slick/slick-theme.css" />




<style type="text/css">
body {
	background-color: #f3f4f5;
}

.slick-prev:before {
	content: ">";
	color: red;
	font-size: 30px;
}

.slick-next:before {
	content: "<";
	color: red;
	font-size: 30px;
}

.ui.menu .item img.logo {
	margin-right: 1.5em;
}

.main.container {
	margin-top: 7em ! important;
}

.wireframe {
	margin-top: 2em;
}

.ui.footer.segment {
	margin: 5em 0em 0em;
	padding: 5em 0em;
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

.ui.message .list:not (.ui ) li:before {
	content: close-quote !important;
}

.hiddenfile {
	width: 0px;
	height: 0px;
	overflow: hidden;
}

.img {
	height: 60% !important;
}

::selection {
	/* background-color: #cce2ff; */
	/* color: rgba(0,0,0,.87); */
	color: white;
}
</style>

</head>
<body>

	<%@ include file="../include/header.jsp"%>

	<div class="ui main  container">
		<div class="row">
			<div class="row">
				<div class="ui segment">
					<div class="ui list">
						<a class="item"><h2>${event.name}</h2></a>
					</div>

					<div class="ui right floated horizontal list">
						<c:if test="${event.coordinatorsAsList.isEmpty() eq false}">
							<div class="item" href="#">
								<div class="ui green horizontal label">Phone</div>
								<a href="tel:${event.coordinatorsAsList[0].phoneno}">${event.coordinatorsAsList[0].phoneno}
								</a>
							</div>
							<div class="item" href="#">
								<div class="ui blue horizontal label">Email</div>
								<a href="mailto:${event.coordinatorsAsList[0].emailid}">${event.coordinatorsAsList[0].emailid}</a>
							</div>
						</c:if>

					</div>
					<div class="ui horizontal list">
						<a class="item" href="#">
							<div class="ui red horizontal label">Event Date</div> <fmt:formatDate
								type="date" value="${event.dateOfEvent}" />
						</a>
					</div>
				</div>
			</div>

			<div class="row">

				<div class="ui segment">

					<!-- photo starts here  -->


					<div class="ui fluid image image1">
						<div class="ui black ribbon label">
							<i class="hotel icon"></i> <a style="color: white; opacity: 1;"
								href="/registerForEvent/${event.eventid}"> Register For this
								Event</a>
						</div>
						<div class="ui bottom right attached label">
							<a class="ui label"> <img
								class="ui right spaced avatar image"
								src="/images/avatar/small/elliot.jpg"> Posted by
							</a>

						</div>

						<c:set var="imageUrl" value="${event.imageUrl}" />

						<c:choose>
							<c:when test="${not empty imageUrl }">
								<img class="img" data-original="${imageUrl}" src="${imageUrl}"
									style="width: 100%;">
							</c:when>
							<c:otherwise>
								<img class="img" style="width: 100%;"
									src="/resources/assets/images/wireframe/image.png">
							</c:otherwise>
						</c:choose>

					</div>
					<!-- photo ends here  -->
				</div>

				<c:if test="${not empty event.coordinatorsAsList}">
					<div class="ui segment">
						<table class="ui single line table">
							<thead>
								<tr>
									<th>Coordinator Name</th>
									<th>Coordinator Email</th>
									<th>Coordinator phone</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${event.coordinatorsAsList}" var="item">
									<tr>
										<td>${item.name}</td>
										<td>${item.emailid}</td>
										<td>${item.phoneno}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
				<c:if test="${not empty event.organizationName}">
					<div class="ui red message">College/Organization Name :
						${event.organizationName}</div>
				</c:if>
				<c:if test="${not empty event.department}">
					<div class="ui green message">Department
						Name:${event.department}</div>
				</c:if>
				<c:if test="${not empty event.paymentDetail}">
					<div class="ui orange message">${event.paymentDetail}</div>
				</c:if>

				<c:if test="${not empty event.content}">
					<div class="ui segment">${event.content}</div>
				</c:if>
			</div>
		</div>
	</div>

	<div class="hiddenfile">
		<input type="file" id="backgroundimageupload" />
	</div>
	<input type="hidden" id="workshopid" value="${event.eventid}">

	<script src="/resources/assets/library/jquery.min.js"></script>
	<script src="/resources/dist/components/tab.min.js">
		
	</script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<script src="/resources/dist/components/checkbox.min.js">
		
	</script>
	<script src="/resources/dist/components/modal.min.js"></script>

	<script
		src="<c:url value='/resources/dist/components/transition.min.js' />">
		
	</script>
	<script
		src="<c:url value='/resources/dist/components/dimmer.min.js' />">
		
	</script>
	<script src="/resources/dist/components/dropdown.min.js">
		
	</script>

	<script src="/resources/dist/tag-it.js" type="text/javascript"
		charset="utf-8"></script>

	<script src="/resources/dist/components/form.min.js"></script>
	<script src="/resources/dist/components/accordion.min.js">
		
	</script>

	<div class="ui modal modal1">
		<i class="close icon"></i>
		<div class="header">Modal Title</div>
		<div class="image content">
			<div class="image">An image can appear on left or an icon</div>
			<div class="description">A description can appear on the right
			</div>
		</div>
		<div class="actions">
			<div class="ui button">Cancel</div>
			<div class="ui button">OK</div>
		</div>
	</div>

	<%@ include file="../include/footer.jsp"%>
	<script src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

	<script type="text/javascript" src="/resources/slick/slick.min.js"></script>
</body>



<script>
	$('.ui.checkbox').checkbox();
	$(document).ready(function() {
		$('.ui.selection.dropdown').dropdown();
		$('.ui.menu .ui.dropdown').dropdown({
			on : 'hover'
		});
	});
	$('.ui.accordion').accordion();
</script>

<script type="text/javascript">
	
</script>
</html>
