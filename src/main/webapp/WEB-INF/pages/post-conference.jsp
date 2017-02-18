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
<title>Post A Conference</title>

<link rel="stylesheet" type="text/css"
	href="/resources/dist/semantic.min.css">


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link href="/resources/dist/components/jquery.tagit.css"
	rel="stylesheet" type="text/css">
<link href="/resources/dist/components/tagit.ui-zendesk.css"
	rel="stylesheet" type="text/css">

  <script src="/resources/dist/tinymce/tinymce.min.js"></script>
  <script>tinymce.init({
	  selector: 'textarea',
	  height: 500,
	  theme: 'modern',
	  plugins: [
	    'advlist autolink lists link image charmap print preview hr anchor pagebreak',
	    'searchreplace wordcount visualblocks visualchars code fullscreen',
	    'insertdatetime media nonbreaking save table contextmenu directionality',
	    'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc'
	  ],
	  toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
	  toolbar2: 'print preview media | forecolor backcolor emoticons | codesample',
	  image_advtab: true
	 
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
	margin-top: 7em  ! important;
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

	<%@ include file="include/header.jsp"%>

	<div class="ui grid stackable main container ">
		<div class="row">
	  <div class="ten wide column">
	
	<div class="ui segment">
	<div class="ui attached message" style="margin-bottom:10px;">
  <div class="header">
    Welcome to our site!
  </div>
  <p>Fill out the form below to post your Event</p>
</div>
		<form class="ui form" action="<c:url value='post-a-conference' />"
					modelAttribute="event" method="post">
					<input type="hidden" name="type"
						value="${type}">
				<div class="field">
					<label>Event Name</label> <input type="text" name="name"
						placeholder="Name">
				</div>
				<div class="field">
					<label>Event Date</label>
					<div class="ui icon input">
						<input type="text" id="datepicker" name="dateOfEvent"> <i
							class="calendar icon"></i>
					</div>
				</div>
				<div class="field">
					<label>Other Details</label>
					<textarea name="content">
					<p>1. About Your Event</p>
					<p>&nbsp;</p>

					<p>2. College Details&nbsp;</p>
					<p>&nbsp;</p>

					<p>4. Important Dates :</p>
					<p>&nbsp;</p>

					<p>5. Who Can attend? (UG/PG/Research Scholars)</p>
					<p>&nbsp;</p>

					<p>7. Registration Details.(Payment Details)</p>
					<p>&nbsp;</p>

					<p>8. Address for communication</p>
					<p>&nbsp;</p>				
					</textarea>
				</div>

				<br>
			<button class="ui button green" type="submit">Submit</button>
		</form>
</div>
</div>

<div class="six wide column">
<div class="ui segment">
<div class="ui list">
  <a class="item"><h4>Many people like you have contributed to SymposiumHub thanks for your valuable contribution</h4></a>

  
</div>
  <div class="ui accordion">
  <div class=" title">
    <i class="dropdown icon"></i>
   who we are ?
  </div>
  <div class="content">
    <p>
SymposiumHub is a Education  app that helps you to post your college symposiums,conference,workshops ,training events
 and get registrations on the fly in your DashBoard .
</p>
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
	<%@ include file="include/footer.jsp"%>
</body>


<script>
	$(function() {

		$('.ui.form').form({
			fields : {
				name : 'empty',
				dateOfEvent : 'empty',
			},
			inline : true,
			on : 'blur'
		});
		$("#datepicker").datepicker();
	});
	$(function() {
		$("#datepicker1").datepicker();
	});
	$(function() {
		$("#datepicker2").datepicker();
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
