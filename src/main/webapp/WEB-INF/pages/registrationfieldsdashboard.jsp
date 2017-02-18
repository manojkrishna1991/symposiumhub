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
<title>SymposiumHub Registration Fields</title>
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
	background: #ffffff;
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



	<div class="ui main text container ">
		<div class="ui segment">

			<button class="ui green button addfiedbut"><i class="plus icon"></i>Add Field</button>
			<h1 style="text-align: center">Sortable form elements</h1>
			<form class="ui form" id="" action="/dynamicRegistrationFormHandler"
				method="get">

				<c:forEach var="symposiumvar" items="${symposium}">

					<c:forEach var="symposiumInfo"
						items="${symposiumvar.symposiumFieldInfo}" varStatus="loop">
						<c:if test="${symposiumInfo.type eq 'text'}">

							<div class="field">
								<label style="font-size: 18px;">${symposiumInfo.name} : <i
									data-id="${symposiumInfo.id}" data-type="${symposiumInfo.type}"
									data-value="${symposiumInfo.name}" class="edit icon"
									style="display: none;"></i>
									<i style="display: none;" class="trash outline icon" data-id="${symposiumInfo.id}"></i>
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
							       <input  type="checkbox" id="${symposiumInfo.id}id"
									value="${symposiumInfo.name}" name="${symposiumvar.name}">	
									
								<label for="${symposiumInfo.id}id" >${symposiumInfo.name} :
								
								 <i
									data-id="${symposiumInfo.id}" data-type="${symposiumInfo.type}"
									data-value="${symposiumInfo.name}" class="edit icon"
									style="display: none;"></i>
									
									<i style="display: none;" class="trash outline icon" data-id="${symposiumInfo.id}"></i>
									</label>
									</div>
							</div>
							</div>
							<c:if test="${loop.last}">
								<button data-id="${symposiumvar.id}" data-type="checkbox"
									onclick="dynobutton(this)" type="button"
									class="ui button dyno-button">Add CheckBox</button>
							</c:if>
						</c:if>
						<c:if test="${symposiumInfo.type eq 'radio'}">
							<div class="grouped fields">

								<c:if test="${loop.first}">
									<label style="font-size: 18px; font-weight: bold;">${symposiumvar.name}</label>
								</c:if>
								<div class="field">
									<div class="ui radio checkbox">
									 <input style="margin: 7px;" type="radio" id="${symposiumInfo.id}id"
											value="${symposiumInfo.name}" name="${symposiumvar.name}">
										<label for="${symposiumInfo.id}id" style="font-size: 16px; font-weight: normal;">${symposiumInfo.name}
											
											<i data-id="${symposiumInfo.id}"
											data-type="${symposiumInfo.type}"
											data-value="${symposiumInfo.name}" class="edit icon"
											style="display: none;"></i> <i style="display: none;"
											class="trash outline icon" data-id="${symposiumInfo.id}"></i>
										</label>
									</div>
								</div>
							</div>
							<c:if test="${loop.last}">
								<button data-id="${symposiumvar.id}" data-type="radio"
									onclick="dynobutton(this)" type="button"
									class="ui button dyno-button">Add Radio</button>
							</c:if>
						</c:if>
						<c:if test="${symposiumInfo.type eq 'textbox'}">

							<div class="field">
								<label style="font-size: 18px;">${symposiumInfo.name} : <i
									data-id="${symposiumInfo.id}" data-type="${symposiumInfo.type}"
									data-value="${symposiumInfo.name}" class="edit icon"
									style="display: none;"></i>
								   <i style="display: none;" class="trash outline icon" data-id="${symposiumInfo.id}"></i>
								</label>
								<textarea id="fname" name="${symposiumInfo.name}"></textarea>
							</div>
						</c:if>
						<c:if test="${symposiumInfo.type eq 'dropdown'}">

							<div class="field">
								<label style="font-size: 18px;">${symposiumInfo.name} : <i
									data-id="${symposiumInfo.id}" data-type="${symposiumInfo.type}"
									data-value="${symposiumInfo.name}" class="edit icon"
									style="display: none;"></i>
								    <i style="display: none;" class="trash outline icon" data-id="${symposiumInfo.id}"></i>
									</label><input type="text" id="fname"
									name="${symposiumInfo.name}">
							</div>
						</c:if>

					</c:forEach>
				</c:forEach>
				<input type="hidden" value="${eventId}" id="eventId" name="eventId" >
				
				<div style="text-align: center;">
				<button  style="font-size: 18px;" class="ui blue button " type="submit">Submit</button>
				</div>
			</form>
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
							<label>Field Name</label> <input id="editname" type="text" name="name"
								placeholder="fieldName"><br> <label>Field
								Type</label> <select id="edittype" name="type">
								<option value="">Choose</option>
								<option value="text">Text</option>
								<option value="checkbox">Checkbox</option>
								<option value="textbox">textbox</option>
								<option value="radio">radio</option>
								<option value="dropdown">dropdown</option>
							</select>

						</div>
						<input type="hidden" id="editid" name="id" >
						<button class="ui button" type="submit">Submit</button>
					</form>
				</div>

			</div>

		</div>
		<div class="actions"></div>
	</div>

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
	

	<script type="text/javascript">
	$('.ui.checkbox').checkbox();

		$(".addfiedbut").click(function(event) {
			$('#addfiedpopup').modal('show');
		});

		var formId;
		var type;
		function dynobutton(obj) {
			formId = $(obj).attr("data-id");
			type = $(obj).attr("data-type");

			$('#addoption').modal('show');
		}

		$(".addoptionform").click(function(event) {

			var name = $("#optionName").val();

			var data1 = {
				formId : formId,
				fieldType : type,
				name : name
			};

			$.ajax({
				type : "POST",
				url : "/addField",
				data : JSON.stringify(data1),
				contentType : "application/json",
				success : function(data) {
					console.log("SUCCESS: ", data);
					window.location.href = "/registrationfields"
				},
				error : function(e) {
					console.log("ERROR: ", e);

				},
				done : function(e) {
					console.log("DONE");
				}
			});

		});

		$(document).ready(
				function() {
					$(".edit-content").hide();
					//on hover over the a tag with atribute href='#games' 
					$('label').on(
							{
								mouseenter : function() {
									$(this).find(".edit.icon").show();
									$(this).find(".trash.outline.icon").show();
									
									$(this).find(".edit.icon").click(
											function() {
												$('#editoption').modal('show');
												$('#editname').val($(this).attr("data-value"));
												$('#edittype').val($(this).attr("data-type"));
												$('#editid').val($(this).attr("data-id"));
												
											});
									
									$(this).find(".trash.outline.icon").click(
											function() {
												var id= $(this).attr("data-id");
												$.ajax({
													type : "POST",
													url : "/deleteoption?id="+id+"",
													success : function(data) {
														window.location.href = "/registrationfields"
													},
													error : function(e) {
														console.log("ERROR: ", e);

													},
													done : function(e) {
														console.log("DONE");
													}
												});
											});

									$(this).find(".cancel").click(
											function() {
												$(this).parent().parent()
														.parent().prev(
																".view-mode")
														.fadeIn(400);
												$(this).parent().parent()
														.parent().fadeOut(400);
											});

									/* $( ".edit-container .fa-edit" ).click(function() {
										$(".edit-content").fadeIn(400);
										$(".view-mode").fadeOut(400);
										$(".edit-container .fa-edit").hide();
									}); */

								},
								mouseleave : function() {
									$(this).find(".trash.outline.icon").css("display",
									"none");
									$(this).find(".edit.icon").css("display",
											"none");
								}

							});
				});
	</script>


</body>
</html>