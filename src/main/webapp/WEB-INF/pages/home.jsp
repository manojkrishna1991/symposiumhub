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
   
 @media only screen and (max-width: 768px) {
    .customheight{
    	 height: 603px ! important;
    }
}
  
  </style>

</head>

<body>

<%@ include file="include/header.jsp" %>
 <div class="pusher customheight" >
 
 

<div class="ui stackable two column grid" style="margin-top:100px">
 
  <div class="column centered">
  
  <div class="ui message" >
  
  <div  class="header">
<h1 style="text-align:center; ">    <a href="/viewsymposium" style="color: #21ba45;">
Welcome to SymposiumHub </a> </h1>
  </div>
<p style="text-align:center;"> <a href="/viewsymposium" style="text-decoration: none;">
Its an Awesome Experience !!!!!</a></p>

					<sec:authorize access="isAnonymous()">


<div  style="text-align: center;">
<button  type="button" data-url="/symposium" class="ui primary button order-button home_button">
  Post A Symposium
</button>
<button  type="button" data-url="/post-a-conference" class="ui primary button order-button">
  Post A Conference
</button>
</div>
</sec:authorize>


					<sec:authorize access="isAuthenticated()">


<div  style="text-align: center;">
<a href="/symposium"><button  type="button" data-url="/symposium" class="ui primary button home_button">
  Post A Symposium
</button>
</a>
<a href="/post-a-conference"><button  type="button" data-url="/post-a-conference" class="ui primary button order-button">
  Post A Conference
</button>
</a>
</div>
</sec:authorize>
</div>
  </div>

</div>
		<c:if test="${empty authenticated}">

			<div class="ui stackable grid">
				<div class="fifteen wide column centered">
					<div class="ui three steps" style="height: 300px;">
						<div class="active step">
							<i style="color: #E03997; font-size: 6.5em;"
								class="write square icon"></i>
							<div class="content">
								<div style="" class="">Post Your
									Events</div>
							</div>
						</div>
						<div class="active step">
							<i style="color: #E03997; font-size: 6.5em;" class="users icon"></i>
							<div class="content">
								<div style="" class=" black">People Register
									For Events</div>
							</div>
						</div>
						<div class="active step">
							<i style="color: #E03997; font-size: 6.5em;"
								class="dashboard icon"></i>
							<div class="content">
								<div style="" class="">Get
									Registrations On the Fly</div>
							</div>
						</div>
					</div>
				</div>
				
		
			</div>
		
		</c:if>
		
		<c:if test="${not empty authenticated}">



			<div class="ui stackable sixteen column grid">
				<div class="twelve wide column centered">
					<div class="ui three steps" style="height: 300px;">
						<div class="active step">
							<i style="color: #E03997; font-size: 6.5em;"
								class="write square icon"></i>
							<div class="content">
								<div style="color: #21ba45;" class="title">Post Your
									Symposium</div>
							</div>
						</div>
						<div class="active step">
							<i style="color: #E03997; font-size: 6.5em;" class="users icon"></i>
							<div class="content">
								<div style="color: #21ba45;" class="title">People Register
									For Symposium</div>
							</div>
						</div>
						<div class="active step">
							<i style="color: #E03997; font-size: 6.5em;"
								class="dashboard icon"></i>
							<div class="content">
								<div style="color: #21ba45;" class="title">Get
									Registrations On the Fly in your Dashboard</div>
							</div>
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
