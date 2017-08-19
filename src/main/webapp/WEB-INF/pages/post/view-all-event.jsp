<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>


<!DOCTYPE html>
<html>

<head>
<!-- Standard Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="symposiumhub,internatioanl conference,hackathon,workshop,guest lecture,hackathon,college reviews,college symposiums,symposiums in chennai">
<meta name="keywords" content="symposiumhub ,symposiumhub  symposium 2010,college symposiums,college festivals,symposiums,technical events,college  symposiums,symposiums in chennai,symposiums 2016,symposiums 2017,hackathon,workshop,guest lecture,hackathon,college reviews">
<!-- Site Properties -->
<title>${eventType}-List of all ${eventType}</title>
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

.wireframe {
	margin-top: 2em;
}

.ui.footer.segment {
	margin: 5em 0em 0em;
	padding: 5em 0em;
}
</style>
<%request.setAttribute("eventName",StringUtils.capitalize((String)request.getAttribute("eventType")));%>

</head>

<body>

	<%@ include file="../include/header.jsp"%>
	<div class="ui main container">

      <c:if test="${not empty event}">
			<h4 class="ui header single_page_header">
				List of all ${eventName}s
			</h4>
			</c:if>
		<div class="ui grid" >
			

			<c:if test="${ empty event}">
				<div class="ui main text container" style="text-align: center;margin-top:0px;">
					
						<div class="row">

							<div class="ui centered message " style="margin: 0;">
								<div class="header">${eventName}</div>
								<p>No One has posted a ${eventName}
								<div style="text-align: center; padding: 10px;">
									<a class="ui green button" href="/post-event/${eventType}">Post
										A ${eventName}</a>
								</div>
								</p>
							</div>
						</div>
				</div>

			</c:if>

			<c:forEach var="event" items="${event}">

				<div style="cursor: pointer;" onclick="redirect(this)"
					data-href="/event/${event.eventid}/${event.name}"
					' class="sixteen wide mobile eight wide tablet four wide computer column">
					<h4 class="ui dividing header">
						Date of Event
						<fmt:formatDate type="date" value="${event.dateOfEvent}" />
					</h4>
					<div class="ui card">
						<div class="image dimmable"
							style="min-height: 238px; background: #fff;">
							<div class="ui blurring inverted dimmer transition hidden">
								<div class="content">
									<div class="center">
										<div class="ui teal button"></div>
									</div>
								</div>
							</div>
							<c:set var="imageUrl" value="${event.imageUrl}" />

							<c:choose>
								<c:when test="${not empty imageUrl}">
									<img class="lazy cardimage" data-original="${imageUrl}"
										src="/resources/assets/images/2.gif" style="width: 100%;">
								</c:when>
								<c:otherwise>
									<img class="cardimage" style="width: 100%;"
										src="/resources/assets/images/wireframe/image.png">
								</c:otherwise>
							</c:choose>

						</div>
						<div class="content">
							<div class="header">Name</div>
							<div class="meta">
								<a class="group">${event.name}</a>
							</div>
						</div>
						<div class="extra content">
							<a href="/event/${event.eventid}/${event.name}"
								class="right floated created">view more</a>
							<%--  <a href="/viewconference/${conference.id}/${conference.name}" class="friends">
        register</a> --%>
						</div>
					</div>
				</div>


			</c:forEach>


		</div>
	</div>

	<script src="/resources/assets/library/jquery.min.js"></script>
	<script src="/resources/dist/components/dimmer.min.js"></script>
	<script src="/resources/dist/components/modal.min.js"></script>
	<script src="/resources/dist/components/transition.min.js"></script>
	<script src="/resources/dist/components/form.min.js"></script>
	<script src="/resources/dist/components/sidebar.min.js"></script>
	<script src="/resources/assets/library/jquery.lazyload.min.js"></script>
	<%@ include file="../include/footer.jsp"%>
</body>
<script>
function redirect(obj){
	var link=$(obj).attr("data-href");
	var newlink=link.replace("''","");
	window.location.href=newlink;
	}
$(document).ready(function () {
     
	$(document).ready(function () {
	     
		$("img.lazy").lazyload({
			event : "sporty"
		});
		$(window).bind("load", function () {
	        var timeout = setTimeout(function () { $("img.lazy").trigger("sporty")
	        	
	        
	        }, 5000);
	    }); 

		  });

	  });
	
</script>

</html>
