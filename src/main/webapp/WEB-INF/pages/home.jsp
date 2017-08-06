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
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/common.css' />">

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

</head>

<body>

	<%@ include file="include/header.jsp"%>
	<div class="ui fluid main container ">
		<div class="ui  container grid one column ">
			<div class="row">
				<div class="column">
					<h2 style="text-align: center; color: #1a653b">Symposium Hub
						is a great place to Organize your events</h2>

				</div>
			</div>
		</div>






		<div class="ui fluid mtop20 container custom-margin " style="margin-left: 0px !important;margin-right: 0px !important;">
			<div class="ui segment two column grid stackable"
				style="background: #eaf3ee ! important;">

				<div class="row">
					<div class=" column">

						<h2 style="text-align: center; color: #1a653b">How it works
							!!!!</h2>
						<div style="position: relative; height: 0; padding-bottom: 56.25%">
							<iframe src="https://www.youtube.com/embed/VHtPId-mzGI?ecver=2"
								width="640" height="360" frameborder="0"
								style="position: absolute; width: 100%; height: 100%; left: 0"
								allowfullscreen></iframe>
						</div>
					</div>
					<div class="column">
						<h2 style="text-align: center; color: #1a653b">Why Use
							SymposiumHub ?</h2>
						<ul
							style="line-height: 2; font-weight: normal; font-family: 'Lato', sans-serif; font-size: 18px;">
							<li>Symposium Hub is a place where you can post your Events
								on the fly</li>
							<li>We have Dynamic Forms Integrated to our site where
								everything is customizable and suites your Needs</li>
							<li>Our Dynamic forms are even better than Google forms</li>
							<li>Receive notifications for each participants
								registrations.</li>
							<li>Using Our unique Dash Board access all your Events in
								one Place.</li>
						</ul>
						<div style="text-align: center;">
							<a href="/selectEvent"><button class="ui green button"
									style="font-size: 18px;">I Want To Post My Event >></button></a>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="ui  container grid one column mtop30">
		<div class="row">
			<div class="column">
				<h2 style="text-align: center; color: #1a653b">How Event
					Registration Process Works</h2>

			</div>
		</div>
	</div>

	<div class="ui fluid container mtop30 " >
		<div class="ui three steps stackable customheight">
			<div class="active step">
				<i style="color: #00b5ad; font-size: 6.5em;"
					class="write square icon"></i>
				<div class="content">
					<div style="font-size: 18px;" class="">Post Your Events</div>
				</div>
			</div>
			<div class="active step">
				<i style="color: #00b5ad; font-size: 6.5em;" class="users icon"></i>
				<div class="content">
					<div style="font-size: 18px;" class=" black">People Register
						For Events</div>
				</div>
			</div>
			<div class="active step">
				<i style="color: #00b5ad; font-size: 6.5em;" class="dashboard icon"></i>
				<div class="content">
					<div style="font-size: 18px;" class="">Get Registrations On
						the Fly</div>
				</div>
			</div>
		</div>
	</div>

	<div class="ui  container grid one column mtop30 text-center">
		<div class="row">
			<div class="column">
				<a href="/selectEvent" class="positive massive ui button">I Want
					To Post My Event</a>
			</div>
		</div>
	</div>




	<script src="/resources/assets/library/jquery.min.js"></script>
	<script src="/resources/dist/components/dimmer.min.js"></script>
	<script src="/resources/dist/components/modal.min.js"></script>
	<script src="/resources/dist/components/transition.min.js"></script>
	<script src="/resources/dist/components/form.min.js"></script>
	<script src="/resources/dist/components/sidebar.min.js"></script>

	<%@ include file="include/footer.jsp"%>
</body>




</html>
