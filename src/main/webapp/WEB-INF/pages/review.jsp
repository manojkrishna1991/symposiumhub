<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
<title>Review</title>

<link rel="stylesheet" type="text/css"
	href="/resources/dist/semantic.min.css">



<link rel="stylesheet" type="text/css" href="/resources/slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/slick/slick-theme.css" />




<style type="text/css">
body {
	background-color: #f3f4f5;
}

.slick-prev:before {
	content: ">";
	color: red;
	font-size: 30px;
}

.slick-next:before {
	content: "<";
	color: red;
	font-size: 30px;
}

.ui.menu .item img.logo {
	margin-right: 1.5em;
}

.main.container {
	margin-top: 7em ! important;
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
	
	
	
		<div class="row">
		
			<div class="ui segment">
			
								<button class="ui right floated orange  button ">${page} of 112</button>
				
			

				<h4 class="ui horizontal divider header">
					<i class="tag icon"></i>Reviews
				
				</h4>
				
				

				<div class="ui grid">


					<c:forEach var="college" items="${college}">

						<div
							class="sixteen wide mobile eight wide tablet four wide computer column">
							<div class="ui  card">
								<div class="image">
									<img src="/resources/assets/images/wireframe/image.png">
								</div>
								<div class="content">
									<a href="/page/${college.id}" class="header">${college.name}</a>
								</div>

								<div class="extra">
									Rating:
									<div class="ui star rating" data-rating="${college.rating}"
										data-max-rating="5"></div>
								</div>
							</div>
						</div>
					</c:forEach>






				</div>

			


			</div>
		</div>
	</div>

	<div class="hiddenfile">
		<input type="file" id="backgroundimageupload" />
	</div>
	<input type="hidden" id="conferenceId" value="${conference.id}">

	<script src="/resources/assets/library/jquery.min.js"></script>
	<script src="/resources/dist/components/tab.min.js">
		
	</script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
	<script src="/resources/dist/components/checkbox.min.js">
		
	</script>
	<script src="/resources/dist/components/modal.min.js"></script>

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
	<script src="/resources/dist/components/rating.min.js">	
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



<script>
	$('.ui.checkbox').checkbox();
	$(document).ready(function() {
		$('.ui.selection.dropdown').dropdown();
		$('.ui.menu .ui.dropdown').dropdown({
			on : 'hover'
		});
	});
	$('.ui.accordion').accordion();
	
	  $('.ui.rating')
	    .rating('disable'
	    );
	    	
</script>

<script type="text/javascript">
	$(document).ready(function() {

		$('.image1').slick({
			lazyLoad : 'ondemand',
			slidesToShow : 1,
			slidesToScroll : 1,
			arrows : true

		});

	});
</script>
</html>
