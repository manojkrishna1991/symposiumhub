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
<title>${title}</title>

<link rel="stylesheet" type="text/css"
	href="/resources/dist/semantic.min.css">


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link href="/resources/dist/components/jquery.tagit.css"
	rel="stylesheet" type="text/css">
<link href="/resources/dist/components/tagit.ui-zendesk.css"
	rel="stylesheet" type="text/css">

<script src="/resources/dist/tinymce/tinymce.min.js"></script>
<script>
	tinymce
			.init({
				selector : 'textarea',
				height : 500,
				theme : 'modern',
				plugins : [
						'advlist autolink lists link image charmap print preview hr anchor pagebreak',
						'searchreplace wordcount visualblocks visualchars code fullscreen',
						'insertdatetime media nonbreaking save table contextmenu directionality',
						'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc' ],
				toolbar1 : 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
				toolbar2 : 'print preview media | forecolor backcolor emoticons | codesample',
				image_advtab : true

			});
</script>

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
</style>

</head>
<body>

	<%@ include file="../include/header.jsp"%>

	<div class="ui grid stackable main container ">
		<div class="row">
			<div class="ten wide column">

				<div class="ui segment">
					<div class="ui attached message" style="margin-bottom: 10px;">
						<div class="header alert success">
							<h3>Edit ${title}</h3>
						</div>
						<p>After editing the event please click save button</p>
					</div>
					<c:if test="${param.success}">
						<div class="ui green message">Event Updated Successfully</div>
					</c:if>
					<form class="ui form" action="<c:url value='/saveEvent' />"
						modelAttribute="event" method="post" enctype='multipart/form-data'>
						<input type="hidden" name="type" value="${type}">
						<div class="field">
							<label>Event Name</label> <input type="text" name="name"
								placeholder="Name" value="${event.name}">
						</div>
						<div class="field">
							<label>Event Date</label>
							<div class="ui icon input">
								<input type="text" id="datepicker" name="dateOfEvent"
									value="${eventDate}"> <i class="calendar icon"></i>
							</div>
						</div>
						<div class="field">
							<label>College/Organization Name</label>
							<div class="ui icon input">
								<input type="text" id="collegeName" name="organizationName"
									value="${event.organizationName}">

							</div>
						</div>
						<div class="field">
							<label>Department</label>
							<div class="ui icon input">
								<input type="text" id="department" name="department"
									value="${event.department}">
							</div>
						</div>
						<div class="field">

							<div class="ui form">
								<div class="three fields">
									<div class="field">
										<label>Contact Name(1)</label> <input type="text" id="cname"
											name="coordinatorsAsList[0].name"
											value="${event.coordinatorsAsList[0].name}"
											placeholder="Name"> <input type="hidden" id="cname"
											name="coordinatorsAsList[0].id"
											value="${event.coordinatorsAsList[0].id}" placeholder="Name">
									</div>
									<div class="field">
										<label>Contact Email(1)</label> <input type="text"
											id="cemailid" name="coordinatorsAsList[0].emailid"
											value="${event.coordinatorsAsList[0].emailid}"
											placeholder="Email">
									</div>
									<div class="field">
										<label>Contact Mobile No(1)</label> <input type="text"
											id="cphoneno" name="coordinatorsAsList[0].phoneno"
											value="${event.coordinatorsAsList[0].phoneno}"
											placeholder="Mobile no">
									</div>
								</div>
							</div>
						</div>
						<div class="field">

							<div class="ui form">
								<div class="three fields">
									<div class="field">
										<input type="hidden" id="cname"
											name="coordinatorsAsList[1].id"
											value="${event.coordinatorsAsList[1].id}" placeholder="Name">
										<label>Contact Name(2)</label> <input type="text" id="cname1"
											name="coordinatorsAsList[1].name"
											value="${event.coordinatorsAsList[1].name}"
											placeholder="Name">
									</div>
									<div class="field">
										<label>Contact Email(2)</label> <input type="text"
											id="cemailid1" name="coordinatorsAsList[1].emailid"
											placeholder="Email"
											value="${event.coordinatorsAsList[1].emailid}">
									</div>
									<div class="field">
										<label>Contact Mobile No(2)</label> <input type="text"
											id="cphoneno1" value="${event.coordinatorsAsList[1].phoneno}"
											name="coordinatorsAsList[1].phoneno" placeholder="Mobile no">
									</div>
								</div>
							</div>
						</div>
						<div class="field">
							<label>Send Registration Email To:</label> <input type="email"
								name="regEmail" value="${event.regEmail}">
						</div>
						<div class="field">
							<label>Other Details</label>
							<textarea name="content">
					${event.content}	
					</textarea>
						</div>
						<%-- <h4 class="ui dividing header">
							<div class="ui message green">
								<div class="header">Choose a Registration Form.check
									fields that you want to collect</div>
							</div>
						</h4>

						<div class="inline field">
							<div class="ui toggle checkbox">
								<input type="checkbox" name="fields.fullName"
									${event.fields.fullName !=null ? 'checked=true':''} tabindex="0" class="hidden">
								<label>fullName</label>
							</div>
						</div>
						<div class="inline field">
							<div class="ui toggle checkbox">
								<input type="checkbox" name="fields.phoneNumber"
									${event.fields.phoneNumber !=null ? 'checked=true':''}  tabindex="0"
									class="hidden"> <label>phoneNumber</label>
							</div>
						</div>
						<div class="inline field">
							<div class="ui toggle checkbox">
								<input type="checkbox" name="fields.email"
									${event.fields.email !=null ? 'checked=true':''}  tabindex="0" class="hidden">
								<label>email</label>
							</div>
						</div>
						<div class="inline field">
							<div class="ui toggle checkbox">
								<input type="checkbox" name="fields.collegeName"
									${event.fields.collegeName !=null ? 'checked=true':''}  tabindex="0"
									class="hidden"> <label>collegeName</label>
							</div>
						</div>
						<div class="inline field">
							<div class="ui toggle checkbox">
								<input type="checkbox" name="fields.collegeId"
									${event.fields.collegeId !=null ? 'checked=true':''}  tabindex="0" class="hidden">
								<label>collegeId</label>
							</div>
						</div>
						<input type="hidden" value="${event.eventid}"
							name="fields.eventId">
						<input type="hidden" value="${event.fields.id}"
							name="fields.id">
 --%>							
						<h4 class="ui dividing header">
							<div class="ui message green">
								<div class="header">Registration Details</div>
							</div>
						</h4>
						<div class="fields">
							<div class="sixteen wide field">
								<textarea name="paymentDetail" >${event.paymentDetail}</textarea>
							</div>
						</div>

						<div class="field">
							<div class="sixteen wide field">
								<label>Upload A Photo</label> <input
									style="border: 3px solid #1aa62a;" type="file" name="file">
							</div>

						</div>

						<br> <input type="hidden" value="${event.eventid}"
							name="eventid">
						<button class="ui button green" type="submit">Submit</button>
					</form>
				</div>
			</div>

			<div class="six wide column">
				<div class="ui segment">
					<div class="ui list">
						<a class="item"><h4>Many people like you have contributed
								to SymposiumHub thanks for your valuable contribution</h4></a>


					</div>
					<div class="ui accordion">
						<div class=" title">
							<i class="dropdown icon"></i> who we are ?
						</div>
						<div class="content">
							<p>SymposiumHub is a Education app that helps you to post
								your college symposiums,conference,workshops ,training events
								and get registrations on the fly in your DashBoard .</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="/resources/assets/library/jquery.min.js"></script>
	<script src="/resources/dist/components/tab.min.js">
		
	</script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<script src="/resources/dist/components/checkbox.min.js">
		
	</script>
	<script src="/resources/dist/components/transition.min.js">
		
	</script>
	<script src="/resources/dist/components/dropdown.min.js">
		
	</script>

	<script src="/resources/dist/tag-it.js" type="text/javascript"
		charset="utf-8"></script>

	<script src="/resources/dist/components/form.min.js"></script>
	<script src="/resources/dist/components/accordion.min.js">
		
	</script>
	<%@ include file="../include/footer.jsp"%>
</body>


<script>
	$(function() {

		$('.ui.form').form({
			fields : {
				name : 'empty',
				dateOfEvent : 'empty',
				cname : 'empty',
				cemailid : [ 'empty', 'email' ],
				cphoneno : [ 'empty', 'number' ],
				regEmail : 'empty'
			},
			inline : true,
			on : 'blur'
		});
		$("#datepicker").datepicker();
	});
	$(function() {
		$("#datepicker1").datepicker();
	});

	$('.ui.checkbox').checkbox();
	$(document).ready(function() {
		$('.ui.selection.dropdown').dropdown();
		$('.ui.menu .ui.dropdown').dropdown({
			on : 'hover'
		});
	});
	$('.ui.accordion').accordion();
</script>


</html>
