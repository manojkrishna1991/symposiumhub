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
<title>Dashboard</title>

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
.fluid .visible a{
color:rgba(255,255,255,.9);
}

i.right.arrow.icon{
color:rgba(255,255,255,.9);
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

	<div class="ui main text container">
		
		<c:if test="${ empty event}">

			<div class="ui message" style="margin: auto;">
				<div class="header">
					Hi,
					<sec:authentication property="principal.username" />
				</div>
				<p>You have not posted any events so far.</p>
				<div class="fluid ui animated button brown" tabindex="0">
					<div class="visible content">
						<a href="/post-event/symposium">Post Symposium</a>
					</div>
					<a href="/post-event/symposium">
						<div class="hidden content">
							<i class="right arrow icon"></i>
						</div>
					</a>
				</div>
				<br>
				<div class="fluid ui animated button yellow" >
					<div class="visible content">
						<a href="/post-event/conference">Post Conference</a>
					</div>
					<a href="/post-event/conference">
						<div class="hidden content">
							<i class="right arrow icon"></i>
						</div>
					</a>
				</div>
				<br>
				<div class="fluid ui animated button red" >
					<div class="visible content">
						<a href="/post-event/workshop">Post WorkShop</a>
					</div>
					<a href="/post-event/workshop">
						<div class="hidden content">
							<i class="right arrow icon"></i>
						</div>
					</a>
				</div>
				
				<br>
				<div class="fluid ui animated button green" >
					<div class="visible content">
						<a href="/post-event/hackathon">Post Hackathon</a>
					</div>
					<a href="/post-event/hackathon">
						<div class="hidden content">
							<i class="right arrow icon"></i>
						</div>
					</a>
				</div>
				
				<br>
				<div class="fluid ui animated button orange" >
					<div class="visible content">
						<a href="/post-event/guest-lecture">Post Guest Lecture</a>
					</div>
					<a href="/post-event/guest-lecture">
						<div class="hidden content">
							<i class="right arrow icon"></i>
						</div>
					</a>
				</div>

			</div>
		</c:if>
		<c:if test="${not empty event}">
		<div class="ui segment">
          
			<div class="ui  yellow message">
				<i class="star icon"></i> Our new dashboard makes it easy To acess
				all the events at one place
			</div>
		
			
			
			
			<div class="ui divided items">
			
			<c:forEach var="event" items="${event}">
			
			
				<div class="item">
					<a class="ui tiny image"> <img
						src="/resources/assets/images/wireframe/image.png">
					</a>
					<div class="content">
						<a class="header">${event.name}</a>
						<div class="description">
							<!-- <p>
								Stevie Feliciano is a <a>library scientist</a> living in New
								York City. She likes to spend her time reading, running, and
								writing.
							</p> -->
						</div>
						<div class="extra">
							<a href="/registrations/${event.eventid}"  class="ui  primary button">
								Registrations <i class="right chevron icon"></i>
							</a>
							<a  href="/event/${event.eventid}/${event.name}" class="ui  primary button">
								View<i class="right chevron icon"></i>
							</a>
							<a href="/edit-event/${event.eventid}" class="ui  primary button">
								Edit <i class="right chevron icon"></i>
							</a>
							
								<a href="/registrationfields/${event.eventid}" class="ui  primary button">
								Event Form<i class="right chevron icon"></i>
							</a>
						</div>
					</div>
				</div>
			
		</c:forEach>
			</div>

		</div>
			</c:if>

	</div>



	

	<script src="/resources/assets/library/jquery.min.js"></script>
	<script src="/resources/dist/components/tab.min.js">
		
	</script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<script src="/resources/dist/components/checkbox.min.js">
		
	</script>
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

	<%@ include file="include/footer.jsp"%>
	<script src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

	<script type="text/javascript" src="/resources/slick/slick.min.js"></script>
</body>




</html>
