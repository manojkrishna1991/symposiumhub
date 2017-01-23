<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>

<head>
  <!-- Standard Meta -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

  <!-- Site Properties -->
  <title>Symposium-Hub List Of Symposium</title>
 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/dist/semantic.min.css' />">

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

<%@ include file="include/header.jsp" %>
 <div class="pusher">

		<c:if test="${empty authenticated}">

			<div class="ui grid" style="margin: 100px 13px;">



				<div style="cursor: pointer;"
					
					data-url="/symposium" class="order-button sixteen wide mobile eight wide tablet eight wide computer column">

					<div class="ui card" style="width: 100%;">
						<div class="image dimmable">
							<div class="ui blurring inverted dimmer transition hidden">
								<div class="content">
									<div class="center">
										<div class="ui teal button"></div>
									</div>
								</div>
							</div>
							<img style="width: 100%; max-height: 500px;"
								src="/resources/images/POST YOUR SYMPOSIUM.jpg">
						</div>

					</div>
				</div>
				<div style="cursor: pointer;"
					
					data-url="/symposium" class="order-button sixteen wide mobile eight wide tablet eight wide computer column">

					<div class="ui card" style="width: 100%;">
						<div class="image dimmable">
							<div class="ui blurring inverted dimmer transition hidden">
								<div class="content">
									<div class="center">
										<div class="ui teal button"></div>
									</div>
								</div>
							</div>
							<img style="width: 100%; max-height: 500px;"
								src="/resources/images/WORKSHOP.jpg">
						</div>

					</div>
				</div>
				<div style="cursor: pointer;"
					
					data-url="/symposium" class="order-button sixteen wide mobile eight wide tablet eight wide computer column">

					<div class="ui card" style="width: 100%;">
						<div class="image dimmable">
							<div class="ui blurring inverted dimmer transition hidden">
								<div class="content">
									<div class="center">
										<div class="ui teal button"></div>
									</div>
								</div>
							</div>
							<img style="width: 100%; max-height: 500px;"
								src="/resources/images/TRAINING.jpg">
						</div>

					</div>
				</div>

				<div style="cursor: pointer;"
				
					data-url="/symposium" class="order-button sixteen wide mobile eight wide tablet eight wide computer column">

					<div class="ui card" style="width: 100%;">
						<div class="image dimmable">
							<div class="ui blurring inverted dimmer transition hidden">
								<div class="content">
									<div class="center">
										<div class="ui teal button"></div>
									</div>
								</div>
							</div>
							<img style="width: 100%; max-height: 500px;"
								src="/resources/images/POSTYOURCONFERENCE.jpg">
						</div>

					</div>
				</div>


			</div>
		</c:if>
		
		<c:if test="${not empty authenticated}">

			<div class="ui grid" style="margin: 100px 13px;">



				<div style="cursor: pointer;"
					onclick='window.location.href = "/symposium"'
					class="sixteen wide mobile eight wide tablet eight wide computer column">

					<div class="ui card" style="width: 100%;">
						<div class="image dimmable">
							<div class="ui blurring inverted dimmer transition hidden">
								<div class="content">
									<div class="center">
										<div class="ui teal button"></div>
									</div>
								</div>
							</div>
							<img style="width: 100%; max-height: 500px;"
								src="/resources/images/POST YOUR SYMPOSIUM.jpg">
						</div>

					</div>
				</div>
				<div style="cursor: pointer;"
					onclick='window.location.href = "/symposium"'
					class="sixteen wide mobile eight wide tablet eight wide computer column">

					<div class="ui card" style="width: 100%;">
						<div class="image dimmable">
							<div class="ui blurring inverted dimmer transition hidden">
								<div class="content">
									<div class="center">
										<div class="ui teal button"></div>
									</div>
								</div>
							</div>
							<img style="width: 100%; max-height: 500px;"
								src="/resources/images/WORKSHOP.jpg">
						</div>

					</div>
				</div>
				<div style="cursor: pointer;"
					onclick='window.location.href = "/symposium"'
					class="sixteen wide mobile eight wide tablet eight wide computer column">

					<div class="ui card" style="width: 100%;">
						<div class="image dimmable">
							<div class="ui blurring inverted dimmer transition hidden">
								<div class="content">
									<div class="center">
										<div class="ui teal button"></div>
									</div>
								</div>
							</div>
							<img style="width: 100%; max-height: 500px;"
								src="/resources/images/TRAINING.jpg">
						</div>

					</div>
				</div>

				<div style="cursor: pointer;"
					onclick='window.location.href = "/symposium"'
					class="sixteen wide mobile eight wide tablet eight wide computer column">

					<div class="ui card" style="width: 100%;">
						<div class="image dimmable">
							<div class="ui blurring inverted dimmer transition hidden">
								<div class="content">
									<div class="center">
										<div class="ui teal button"></div>
									</div>
								</div>
							</div>
							<img style="width: 100%; max-height: 500px;"
								src="/resources/images/POSTYOURCONFERENCE.jpg">
						</div>

					</div>
				</div>


			</div>
		</c:if>
		
		
		
		
		
		
		

	</div>
 
 <script src="/resources/assets/library/jquery.min.js"></script>
    <script src="/resources/dist/components/dimmer.min.js"></script>
   <script src="/resources/dist/components/modal.min.js"></script>
    <script src="/resources/dist/components/transition.min.js"></script>
     <script src="/resources/dist/components/form.min.js"></script>
      <script src="/resources/dist/components/sidebar.min.js"></script>
 <%@ include file="include/footer.jsp" %>
</body>




</html>
