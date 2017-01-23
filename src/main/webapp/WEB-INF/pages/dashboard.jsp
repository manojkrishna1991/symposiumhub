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
<title>Symposium-Hub Dashboard</title>
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

.pinkbackground {
	background-color: #e03997 !important;
	color: rgba(255, 255, 255, 0.9) !important;
}
</style>

</head>
<body>

	<%@ include file="include/header.jsp"%>


	<div class="ui  grid" style="margin: 46px 13px;">
		<h4 class="ui horizontal divider header">
			<i class="tag icon"></i> DASH BOARD
		</h4>
		<c:if test="${empty symposiumname}">
			<div class="ui message" style="margin: auto;">
				<div class="header">
					Hi,
					<sec:authentication property="principal.username" />
				</div>
				<p>You have not posted any symposiums so far.</p>
				<div class="fluid ui animated button" tabindex="0">
					<div class="visible content">
						<a href="/symposium">Get Started</a>
					</div>
					<a href="/symposium">
						<div class="hidden content">
							<i class="right arrow icon"></i>
						</div>
					</a>
				</div>

			</div>
		</c:if>
		<c:if test="${not empty symposiumname}">


			<div
				class="sixteen wide mobile eight wide tablet three wide computer column">



				<div class="ui vertical accordion menu" style="width: 20rem;">
					<a class="pinkbackground item"> Menu </a>
					<div class="item" >
						<a class="active title "> <i class="dropdown icon"></i> Symposiums Registrations
						</a>
						<div class=" content">
						<div class="ui form">
							<div class="grouped fields">
								<c:forEach var="symposiumname" items="${symposiumname}"
									varStatus="i">
									<c:if test="${i.index==0}">
										<a class="active item "
											href="<c:url value='/dashboard/ ${symposiumname.symposiumid}' />">
											${symposiumname.name} </a>
									</c:if>
									<c:if test="${i.index>0}">
								    <a class=" item"
											href="<c:url value='/dashboard/ ${symposiumname.symposiumid}' />">
											${symposiumname.name} </a>
									</c:if>



								</c:forEach>
							</div>
							</div>
						</div>
					</div>
					<div class="item">
						<a class="title"> <i class="dropdown icon"></i> Edit Symposiums
						</a>
						<div class="content">
							<div class="ui form">
								<div class="grouped fields">
									<c:forEach var="symposiumname" items="${symposiumname}"
										varStatus="i">
										<c:if test="${i.index==0}">
											<a class="active item "
												href="<c:url value='/editsymposium/${symposiumname.symposiumid}' />">
												${symposiumname.name} </a>
										</c:if>
										<c:if test="${i.index>0}">
											<a class=" item"
												href="<c:url value='/editsymposium/${symposiumname.symposiumid}' />">
												${symposiumname.name} </a>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
					
					<div class="item">
						<a class="title"> <i class="dropdown icon"></i> View My Symposiums
						</a>
						<div class="content">
							<div class="ui form">
								<div class="grouped fields">
									<c:forEach var="symposiumname" items="${symposiumname}"
										varStatus="i">
										<c:if test="${i.index==0}">
											<a class="active item "
												href="<c:url value='/symposiumview/${symposiumname.symposiumid}/${symposiumname.name}' />">
												${symposiumname.name} </a>
										</c:if>
										<c:if test="${i.index>0}">
											<a class=" item"
												href="<c:url value='/symposiumview/${symposiumname.symposiumid}/${symposiumname.name}' />">
												${symposiumname.name} </a>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>


				<%-- <div class="ui large vertical menu">
					<a class="pinkbackground item"> Menu </a>
					<div class="ui dropdown item">
						Symposiums Registrations <i class="dropdown icon"></i>
						<div class="menu">
							<c:forEach var="symposiumname" items="${symposiumname}"
								varStatus="i">
								<c:if test="${i.index==0}">
									<a class="active item "
										href="<c:url value='/dashboard/ ${symposiumname.symposiumid}' />">
										${symposiumname.name} </a>
								</c:if>
								<c:if test="${i.index>0}">
									<a class=" item"
										href="<c:url value='/dashboard/ ${symposiumname.symposiumid}' />">
										${symposiumname.name} </a>
								</c:if>



							</c:forEach>
						</div>
					</div>

					<div class="ui dropdown item">
						Edit Symposiums <i class="dropdown icon"></i>
						<div class="menu">
							<c:forEach var="symposiumname" items="${symposiumname}"
								varStatus="i">
								<c:if test="${i.index==0}">
									<a class="active item "
										href="<c:url value='/editsymposium/${symposiumname.symposiumid}' />">
										${symposiumname.name} </a>
								</c:if>
								<c:if test="${i.index>0}">
									<a class=" item"
										href="<c:url value='/editsymposium/${symposiumname.symposiumid}' />">
										${symposiumname.name} </a>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div> --%>
			</div>
			<div
				class="sixteen wide mobile eight wide tablet ten wide computer column">


				<c:forEach var="symposium" items="${symposium}">
					<h2>${symposium.name}</h2>	<a  href="/downloadCSV/${symposium.symposiumid}" class="ui  primary button">
								Download as CSV<i class="right chevron icon"></i>
							</a>

					<table class="ui  single line table">
						<thead>

							<tr>
								<th>Name</th>
								<th>Registration Date</th>
								<th>E-mail address</th>
								<th>Phone Number</th>
								<th>CollegeName</th>
								<th>collegeId</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="symposiumregister"
								items="${symposium.registerForSymposium}">

								<tr>
									<td>${symposiumregister.fullName}</td>
									<td>${symposiumregister.registerDate}</td>
									<td>${symposiumregister.email}</td>
									<td>${symposiumregister.phoneNumber}</td>
									<td>${symposiumregister.collegeName}</td>
									<td>${symposiumregister.collegeId}</td>
								</tr>
							</c:forEach>
						</tbody>

					</table>

					<c:if test="${ empty symposium.registerForSymposium}">
						<div class="ui icon message">
							<i class="inbox icon"></i>
							<div class="content">
								<div class="header">
									hi ,
									<sec:authentication property="principal.username" />
								</div>
								<p>You Dont have any registration</p>
								<p>
									<strong>Things You can do to get more registrations.</strong>
								</p>
								<p>Share Your symposium link in social media to get
									registrations.</p>
								<p>Share via email.</p>
								<p>Share via whatsapp.</p>
							</div>
						</div>
					</c:if>


				</c:forEach>

			</div>
		</c:if>
	</div>


	<script src="<c:url value='/resources/assets/library/jquery.min.js' />"></script>
	<script src="<c:url value='/resources/dist/components/tab.min.js' />">
		
	</script>
	<script src="<c:url value='/resources/dist/components/form.min.js' />">
		
	</script>
	<script
		src="<c:url value='/resources/dist/components/transition.min.js' />">
		
	</script>
	<script
		src="<c:url value='/resources/dist/components/dropdown.min.js' />">
		
	</script>
	<script
		src="<c:url value='/resources/dist/components/accordion.min.js' />">
		
	</script>
	<script src="/resources/dist/components/sidebar.min.js"></script>
	<%@ include file="include/footer.jsp"%>

</body>
<script>
	$('.menu .item').tab();
</script>
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>
	$('.ui.form').form({
		fields : {
			name : 'empty',
			gender : 'empty',
			username : 'empty',
			password : [ 'minLength[6]', 'empty' ],
			skills : [ 'minCount[2]', 'empty' ],
			terms : 'checked'
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
	$('.item').on('click', function() {
		$('.item').removeClass('active');
		$(this).addClass('active');
	});

	$('.ui.accordion').accordion();
</script>

</html>
