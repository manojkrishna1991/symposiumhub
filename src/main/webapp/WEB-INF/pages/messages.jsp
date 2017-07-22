<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<!-- Site Properties -->
<title>Notes</title>

<link rel="stylesheet" type="text/css"
	href="/resources/dist/semantic.min.css">


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link href="/resources/dist/components/jquery.tagit.css"
	rel="stylesheet" type="text/css">
<link href="/resources/dist/components/tagit.ui-zendesk.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/resources/slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/slick/slick-theme.css" />


<style type="text/css">
body {
	background-color: #f3f4f5;
}

.ui.menu .item img.logo {
	margin-right: 1.5em;
}

.main.container {
	margin-top: 7em ! important;
	background-color: #FFFFFF;
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
</style>

</head>
<body>

	<%@ include file="include/header.jsp"%>
	<div class="ui main  container">
		<div class="ui segment">
			<h4 class="ui horizontal divider header">
				<i class="tag icon"></i>Notes
			</h4>
			<div class="ui grid">
				<c:if test="${not empty activity}">

					<c:forEach var="activity" items="${activity}">

						<div
							class="sixteen wide mobile eight wide tablet four wide computer column">
							<div class="ui  card">
								<div class="image">
									<img src="${activity.photo}">
								</div>


								<div class="content">
									<div class="header">${activity.name}</div>
								</div>

								<div class="content">
									<h4 class="ui sub header">Shared A Note</h4>
									<div class="ui small feed">
										<div class="event">
											<div class="content">
												<div class="summary">${activity.update}</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>

				</c:if>

				<c:if test="${ empty activity}">
					<div class="ui two column centered grid">
						<div
							class="six wide table column sixteen wide mobile column six wide computer column">
							<div class="row">

								<div class="ui centered message " style="margin: 0;">
									<div class="header">Notes</div>
									<p>No One has Shared An Note 
									<div style="text-align: center; padding: 10px;">
										<a class="ui green button" href="/addfriends">Add Freinds</a>
									</div>
									</p>
								</div>
							</div>
						</div>
					</div>

				</c:if>
			</div>
		</div>
	</div>
</body>

<script src="/resources/assets/library/jquery.min.js"></script>
<script src="/resources/dist/components/dimmer.min.js"></script>
<script src="/resources/dist/components/modal.min.js"></script>
<script src="/resources/dist/components/transition.min.js"></script>
<script src="/resources/dist/components/form.min.js"></script>
<script src="/resources/dist/components/sidebar.min.js"></script>

<%@ include file="include/footer.jsp"%>
</html>