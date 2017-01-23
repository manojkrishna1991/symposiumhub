
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<noscript>
	<meta http-equiv="refresh" content="0; url=http://www.google.com/" />
</noscript>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/common.css' />">
	 <link rel="stylesheet" type="text/css" href="<c:url value='/resources/dist/slicknav.min.css' />">
<style>
@media only screen and (min-width: 768px) {
	.modalwidth {
		width: 22% ! important;
	}
}
.slicknav_menu{
    background-color: #e03997;
}
.slicknav_btn{
 background: none;
}
.logos {
    float:left;
    color:#fff;
    padding:1em;
}
.slicknav_menu {
	display:none;
}

@media screen and (max-width: 40em) {
	/* #menu is the original menu */
	
	.slicknav_menu {
		display:block;
	}
	.home_button{
	margin-bottom:10px ! important;
	}
}
img[src="/resources/assets/images/2.gif"] {
    width: 100%;
    height: 40px ! important;
    position: absolute;
    margin: auto;
    top: 50%;
}

</style>
<c:if test="${empty authenticated}">
	<style>
.pad10 {
	padding: 10px;
}
</style>

	


	<div class="ui container computer only grid ">
		<div class="ui inverted vertical masthead center aligned segment">

			<div class="ui fixed inverted menu pink ">
				<div class="ui container">
					<a href="/" class="header item"> <img class="logo"
						style="width: 200px; height: 46px;"
						src="/resources/assets/images/logo.png">

					</a>

					<div class="ui dropdown item">
						<div class="texts">View</div>

						<div class="menu ">
							<a href="/viewsymposium" data-url="/viewsymposium" class="item">Symposium</a>
							<a href="/viewconference" data-url="/viewconference" class="item">Conference</a>

						</div>

					</div>
					<div class="ui dropdown item">
						<div class="texts">Post</div>

						<div class="menu ">
							<a href="#" data-url="/symposium" class="order-button item">Symposium</a>
							<a href="#" data-url="/post-a-conference"
								class="order-button item">Conference</a>

						</div>

					</div>


					<a href="#" data-url="/dashboard" class="order-button item">Login</a>
					<a href="#" data-url="/dashboard" class="order-button item">Register</a>
					<div class="pad10 ui action left icon input visible">
						<input class="email" type="email" required="required"
							placeholder="Enter Your Email">
						<div class="subscribe-button ui teal button">Subscribe</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="ui container tablet only grid">
		<div class="ui fixed inverted menu pink">
			<div class="ui container">
				<a href="/" class="header item"> <img class="logo"
					style="width: 200px; height: 46px;"
					src="/resources/assets/images/logo.png">

				</a>
				<div class="ui dropdown item">
					<div class="text">View</div>

					<div class="menu ">
						<a href="/viewsymposium" data-url="/viewsymposium" class="item">Symposium</a>
						<a href="/viewconference" data-url="/viewconference" class="item">Conference</a>

					</div>

				</div>
				<div class="ui dropdown item">
					<div class="text">Post</div>

					<div class="menu ">
						<a href="#" data-url="/symposium" class="order-button item">Symposium</a>
						<a href="#" data-url="/post-a-conference"
							class="order-button item">Conference</a>

					</div>

				</div>

				<a href="#" data-url="/dashboard" class="order-button item">Login</a>
				<a href="#" data-url="/dashboard" class="order-button item">Register</a>
				<div class="pad10 ui action left icon input visible">
					<input class="email" type="email" required="required"
						placeholder="Enter Your Email">
					<div class="subscribe-button ui teal button">Subscribe</div>
				</div>

			</div>
		</div>
	</div>
	<div class="ui container mobile only grid">
		
		
		<ul id="menu2" style="display:none;" >

            <li>View 
                <ul>
                    <li><a href="/viewsymposium" data-url="/viewsymposium" >Symposium</a></li>
                    <li><a href="/viewconference" data-url="/viewconference" >Conference</a></li>
                </ul>
            </li>
            
               <li>Post 
                <ul>
                    <li><a href="#" data-url="/symposium" class="order-button item">Symposium</a></li>
                    <li><a href="#" data-url="/post-a-conference" class="order-button item">Conference</a></li>
                </ul>
            </li>
   
  
			<li>	<a href="#" data-url="/dashboard" class="order-button item">Login</a></li>
			<li>	<a href="#" data-url="/dashboard" class="order-button item">Register</a></li>
				<li><div class="pad10 ui action left icon input visible">
					<input class="email" type="email" required="required"
						placeholder="Enter Your Email">
					<div class="subscribe-button ui teal button">Subscribe</div>
				</div>
				</li>
</ul>
		

	</div>
</c:if>

<c:if test="${not empty authenticated}">

	
	<div class="ui container mobile only grid">
			<ul id="menu2" style="display:none;" >
            <li>View 
                <ul>
                    <li><a href="/viewsymposium" data-url="/viewsymposium" >Symposium</a></li>
                    <li><a href="/viewconference" data-url="/viewconference" >Conference</a></li>
                </ul>
            </li>
            
               <li>Post 
                <ul>
                    <li><a href="#" data-url="/symposium" class="order-button item">Symposium</a></li>
                    <li><a href="#" data-url="/post-a-conference" class="order-button item">Conference</a></li>
                </ul>
            </li>
   
                <li>Dashboard 
                <ul>
                    <li><a class="item" href="<c:url value="/dashboard" />">Symposium</a></li>
                    <li><a class="item" href="<c:url value="/dash" />">Conference</a></li>
                </ul>
            </li>
            <li><a class="item" href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
				<li><div class="pad10 ui action left icon input visible">
					<input class="email" type="email" required="required"
						placeholder="Enter Your Email">
					<div class="subscribe-button ui teal button">Subscribe</div>
				</div>
				</li>
				
</ul>
	</div>


	<div class="ui container computer only grid">
		<div class="ui fixed inverted menu pink">
			<div class="ui container">
				<a href="/" class="header item"> <img class="logo"
					style="width: 200px; height: 46px;"
					src="/resources/assets/images/logo.png">
				</a>
				<div class="ui dropdown item">
					<div class="text">View</div>

					<div class="menu ">
						<a href="/viewsymposium" data-url="/viewsymposium" class="item">Symposium</a>
						<a href="/viewconference" data-url="/viewconference" class="item">Conference</a>

					</div>

				</div>
				<div class="ui dropdown item">
					<div class="text">Post</div>

					<div class="menu ">
						<a href="#" data-url="/symposium" class="order-button item">Symposium</a>
						<a href="#" data-url="/post-a-conference"
							class="order-button item">Conference</a>

					</div>

				</div>

				<!-- <a href="/registrationfields"
					data-url="/registrationfields" class=" item">Registration Form</a> -->
				<div class="ui simple dropdown item">
					<sec:authentication property="principal.username" />
					<i class="dropdown icon"></i>
					<div class="menu">

						<div class="item">
							<i class="dropdown icon"></i> Dashboard
							<div class="menu">
								<a class="item" href="<c:url value="/dashboard" />">Symposium</a>
								<a class="item" href="<c:url value="/dash" />">Conference</a>

							</div>

						</div>
					</div>

				</div>
				<a class="item" href="<c:url value="/j_spring_security_logout" />">Logout</a>
			</div>
		</div>
	</div>

	<div class="ui container tablet only grid">
		<div class="ui fixed inverted menu pink">
			<div class="ui container">
				<a href="/" class="header item"> <img class="logo"
					style="width: 200px; height: 46px;"
					src="/resources/assets/images/logo.png">
				</a>
				<div class="ui dropdown item">
					<div class="text">View</div>

					<div class="menu ">
						<a href="/viewsymposium" data-url="/viewsymposium" class="item">Symposium</a>
						<a href="/viewconference" data-url="/viewconference" class="item">Conference</a>

					</div>

				</div>

				<div class="ui dropdown item">
					<div class="text">Post</div>

					<div class="menu ">
						<a href="#" data-url="/symposium" class="order-button item">Symposium</a>
						<a href="#" data-url="/post-a-conference"
							class="order-button item">Conference</a>

					</div>

				</div>

				<!-- <a href="/registrationfields"
					data-url="/registrationfields" class=" item">Registration Form</a> -->
				<div class="ui simple dropdown item">
					<sec:authentication property="principal.username" />
					<i class="dropdown icon"></i>
					<div class="menu">

						<div class="item">
							<i class="dropdown icon"></i> Dashboard
							<div class="menu">
								<a class="item" href="<c:url value="/dashboard" />">Symposium</a>
								<a class="item" href="<c:url value="/dash" />">Conference</a>

							</div>

						</div>
						<a class="item" href="<c:url value="/j_spring_security_logout" />">Logout</a>
					</div>
				</div>
			</div>
		</div>
	</div>



</c:if>

