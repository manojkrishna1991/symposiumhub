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
<title>UploadPhotos</title>

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
.img{
height:60% !important;
}
</style>

</head>
<body>

	<%@ include file="include/header.jsp"%>

	<div class="ui main text container">
	
			<div class="ui segment">
			<form class="ui form" action="<c:url value='/addPhoto' />" method="post" enctype="multipart/form-data">
				<div class="field">
					<label>Image1</label> <input type="file" name="file"
						placeholder="image1">
				</div>
				<div class="field">
					<label>Image2</label> <input type="file" name="file"
						placeholder="image2">
				</div>
			   <input type="hidden" id="id" name="collegeid" value="${college.id}">
				
				<button class="ui button" type="submit">Submit</button>
			</form>
		</div>

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
  <div class="header">
    Modal Title
  </div>
  <div class="image content">
    <div class="image">
      An image can appear on left or an icon
    </div>
    <div class="description">
      A description can appear on the right
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



<script>
	$(function() {

		$('.ui.form.step1').form({
			fields : {
				name : 'empty',
				collegeName : 'empty',
				department : 'empty',
				dateOfEvent : 'empty',
				country : 'empty',
				address : 'empty',
				cname : 'empty',
				cemailid : [ 'empty', 'email' ],
				cphoneno : [ 'empty', 'number' ],
				regEmail : [ 'empty', 'email' ]
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
	$(function() {
		var sampleTags = [ 'c++', 'java', 'php', 'coldfusion', 'javascript',
				'asp', 'ruby', 'python', 'c', 'scala', 'groovy', 'haskell',
				'perl', 'erlang', 'apl', 'cobol', 'go', 'lua' ];
		$('#singleFieldTags').tagit({
			availableTags : sampleTags,
			// This will make Tag-it submit a single form value, as a comma-delimited field.
			singleField : true,
			singleFieldNode : $('#mySingleField')
		});

		// singleFieldTags2 is an INPUT element, rather than a UL as in the other 
		// examples, so it automatically defaults to singleField.
		$('#singleFieldTags2').tagit({
			availableTags : sampleTags
		});
		$('#singleFieldTags1').tagit({
			availableTags : sampleTags,
			// This will make Tag-it submit a single form value, as a comma-delimited field.
			singleField : true,
			singleFieldNode : $('#mySingleField1')
		});

		// singleFieldTags2 is an INPUT element, rather than a UL as in the other 
		// examples, so it automatically defaults to singleField.
		$('#singleFieldTags21').tagit({
			availableTags : sampleTags
		});
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

<script type="text/javascript">
    $(document).ready(function(){

    	$('.image1').slick({
    	  lazyLoad: 'ondemand',
    	  slidesToShow: 1,
    	  slidesToScroll: 1
    	});
    		
    });
  </script>
</html>
