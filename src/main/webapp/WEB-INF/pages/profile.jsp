<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Profile</title>

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

.fileContainer {
	overflow: hidden;
	position: relative;
}

.fileContainer [type=file] {
	cursor: inherit;
	display: block;
	font-size: 999px;
	filter: alpha(opacity = 0);
	min-height: 100%;
	min-width: 100%;
	opacity: 0;
	position: absolute;
	right: 0;
	text-align: right;
	top: 0;
}

/* Example stylistic flourishes */
.fileContainer {
	background: blue;
	border-radius: .5em;
	float: left;
	padding: .5em;
}

.fileContainer [type=file] {
	cursor: pointer;
}

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

.user-card .user-card-name {
	color: #0C0D0E !important;
	font-weight: 700;
	font-size: 28px !important;
	margin: 10px 0 6px 0 !important;
	line-height: 28px;
	word-break: break-all;
}
</style>

</head>
<body>

	<%@ include file="include/header.jsp"%>



	<div class="ui main  container">

		<%@ include file="include/message.jsp"%>


		<div class="ui segment">
			<div class="ui top attached tabular menu">
				<a class=" item ${message ne 'error' ? 'active':''}" data-tab="first">Profile Information</a> <a
					class=" item ${message eq 'error' ? 'active':''}" data-tab="second">Edit Profile & Settings</a>
			</div>
			<div class="ui bottom attached ${message ne 'error' ? 'active':''} tab segment" data-tab="first">
				<div class="ui grid">

					<div
						class="sixteen wide mobile eight wide tablet four wide computer column">
						<div class="ui medium image">
							<c:if test="${empty profile.photo}">

								<img src="/resources/assets/images/wireframe/image.png">
							</c:if>
							<c:if test="${not empty profile.photo}">

								<img src="${profile.photo}">


							</c:if>
						</div>

					</div>

					<div
						class="sixteen wide mobile eight wide tablet six wide computer column">
						<h2 class="ui header user-card-name">About me</h2>
						<p style="text-align: justify; word-break: break-word;">${profile.aboutMe}.</p>
					</div>

					<div
						class="sixteen wide mobile eight wide tablet four wide computer column">
						<h2 class="ui header user-card-name">${user.getUsername()}</h2>
						<p>
							Lives in <b>${profile.place}</b>
						</p>
						<p>
							Gender <b>${profile.gender}</b>
						</p>
					</div>

				</div>

			</div>
			<div class="ui bottom attached ${message eq 'error' ? 'active':''} tab segment" data-tab="second">

				<div class="ui grid">

					<div
						class="sixteen wide mobile eight wide tablet four wide computer column">
						<div class="ui medium image">
							<c:if test="${empty profile.photo}">

								<img src="/resources/assets/images/wireframe/image.png">
							</c:if>
							<c:if test="${not empty profile.photo}">

								<img src="${profile.photo}">


							</c:if>
						</div>

					</div>

					<div
						class="sixteen wide mobile eight wide tablet six wide computer column">

						<form class="ui form" action="<c:url value='/profile' />"
							method="post">
							<div class="field">
								<label>Aboutme</label>
								<textarea type="text" name="aboutMe" placeholder="aboutMe">${profile.getAboutMe()}</textarea>
							</div>

							<div class="field">
								<label>Gender</label> <select name="gender" class="ui dropdown">
									<option value="Male">Male</option>
									<option value="Female">Female</option>
								</select>
							</div>


							<div class="field">
								<label>Where You Live</label> <input
									value="${profile.getPlace()}" type="text" name="place"
									placeholder="Where You Live">
							</div>

							<input type="hidden" name="userId" value="${user.getUserId()}">
							<input type="hidden" name="id" value="${profile.getId()}">


							<button class="ui teal button" type="submit">Save</button>
						</form>


					</div>

					<div
						class="sixteen wide mobile eight wide tablet four wide computer column">

						<form class="ui form" action="<c:url value='/profile' />"
							method="post" enctype="multipart/form-data">
							<div class="field">
								<label>Upload A Photo</label> <input type="file"
									name="photoFile" placeholder="Upload A Photo">


							</div>


							<input type="hidden" name="userId" value="${user.getUserId()}">
							<input type="hidden" name="id" value="${profile.getId()}">

							<button class="ui teal button" type="submit">Save
								Picture</button>

						</form>

					</div>


				</div>






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

	<script>
		$('.menu .item').tab();
		
		$(function() {

			$('.ui.form').form({
				fields : {
					aboutMe : 'empty',
					gender : 'empty',
					place : 'empty',
					photoFile : 'empty'
				},
				inline:true,
				on:'blur'
				
			});
		});
	</script>

</body>
</html>