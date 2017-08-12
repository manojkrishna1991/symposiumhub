
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<noscript>
	<meta http-equiv="refresh" content="0; url=http://www.google.com/" />
</noscript>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/common.css' />">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/slicknav.min.css' />">
	<link rel="shortcut icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">
<link rel="icon" href='<c:url value="/resources/images/favicon.ico" />' type="image/x-icon">
	
<style>
@media only screen and (min-width: 768px) {
	.modalwidth {
		width: 22% ! important;
	}
	.customheight {
		height: 300px ! important;
	}
}

@media only screen and (max-width: 768px) {
	
	.custom-margin {
		margin-left: 0 !important;
		margin-right: 0 !important
	}
}

iv.alert, .ui.fixed.menu, .slicknav_menu {
	color: #fff;
	background-color: #287bbc;
	border-color: #1b5480;
	filter: progid:DXImageTransform.Microsoft.gradient(gradientType=0,
		startColorstr='#FF287BBC', endColorstr='#FF23639A');
	background-repeat: no-repeat;
	background: -webkit-linear-gradient(top, #287bbc 0%, #23639a 100%);
	background: -moz-linear-gradient(top, #287bbc 0%, #23639a 100%);
	background: -o-linear-gradient(top, #287bbc 0%, #23639a 100%);
	background: linear-gradient(top, #287bbc 0%, #23639a 100%);
	-webkit-box-shadow: 0 0 0 1px #23639a, 0 1px 2px rgba(0, 0, 0, 0.45);
	-moz-box-shadow: 0 0 0 1px #23639a 1px 2px rgba(0, 0, 0, 0.45);
	box-shadow: 0 0 0 1px #23639a, 0 1px 2px rgba(0, 0, 0, 0.45);
	display: block;
	color: #fff;
	font-size: 15px;
	line-height: 20px;
	/*  position: relative; */
	/*     padding: 10px 20px 10px 50px;
 */
}

iv.alert, div.alert.error {
	background-color: #c9342f;
	background-repeat: no-repeat;
	background: -webkit-linear-gradient(top, #dd5959 0%, #c15252 100%);
	background: -moz-linear-gradient(top, #dd5959 0%, #c15252 100%);
	background: -o-linear-gradient(top, #dd5959 0%, #c15252 100%);
	background: linear-gradient(top, #dd5959 0%, #c15252 100%);
	-webkit-box-shadow: 0 0 0 1px #c15252, 0 1px 2px rgba(0, 0, 0, 0.45);
	-moz-box-shadow: 0 0 0 1px #c15252, 0 1px 2px rgba(0, 0, 0, 0.45);
	box-shadow: 0 0 0 1px #c15252, 0 1px 2px rgba(0, 0, 0, 0.45);
	overflow: hidden;
	display: block;
	color: #fff;
	font-size: 15px;
	line-height: 20px;
	position: relative;
	padding: 10px 20px 10px 50px;
}

div.alert.success {
	background-color: #5b912d;
	background-repeat: no-repeat;
	background: -webkit-linear-gradient(top, #63ae55 0%, #5a994e 100%);
	background: -moz-linear-gradient(top, #63ae55 0%, #5a994e 100%);
	background: -o-linear-gradient(top, #63ae55 0%, #5a994e 100%);
	background: linear-gradient(top, #63ae55 0%, #5a994e 100%);
	-webkit-box-shadow: 0 0 0 1px #5b9b4e, 0 1px 2px rgba(0, 0, 0, 0.45);
	-moz-box-shadow: 0 0 0 1px #5b9b4e, 0 1px 2px rgba(0, 0, 0, 0.45);
	box-shadow: 0 0 0 1px #5b9b4e, 0 1px 2px rgba(0, 0, 0, 0.45);
	overflow: hidden;
	display: block;
	color: #fff;
	font-size: 15px;
	line-height: 20px;
	position: relative;
	padding: 10px 20px 10px 50px;
}

.slicknav_btn {
	background: none;
}

.logos {
	float: left;
	color: #fff;
	padding: 1em;
}

.slicknav_menu {
	display: none;
}

@media screen and (max-width: 40em) {
	/* #menu is the original menu */
	.slicknav_menu {
		display: block;
	}
	.home_button {
		margin-bottom: 10px ! important;
	}
}

img[src="/resources/assets/images/2.gif"] {
	width: 100%;
	min-height: 40px ! important;
	position: absolute;
	margin: auto;
	top: 50%;
}
</style>
<sec:authorize access="isAnonymous()">
	<style>
.pad10 {
	padding: 10px;
}
</style>




	<div class="ui container computer tablet only grid ">
		<div class="ui inverted vertical masthead center aligned segment">

			<div class="ui fixed inverted  purple menu ">
				<div class="ui container">
					<a href="/" class="header item" style="color: #00b5ad;"> <!-- <img class="logo"
						style="width: 200px; height: 46px;"
						src="/resources/assets/images/logo.png"> -->
						<h1>
							<i class="student icon"></i>SymposiumHub
						</h1>

					</a> <a href="/messages" data-url="/messages" class="item">Notes</a>
					<div class="ui pointing dropdown item">

						<div class="texts">View</div>

						<div class="menu ">
							<a href="/view-event/symposium" data-url="/view-event/symposium"
								class="item">Symposium</a> <a href="/view-event/conference"
								data-url="/view-event/conference" class="item">Conference</a> <a
								href="/view-event/workshop" data-url="/view-event/workshop"
								class="item">Workshop</a> <a href="/view-event/guest-lecture"
								data-url="/view-event/guest-lecture" class="item">Guest
								Lecture</a> <a href="/view-event/hackathon"
								data-url="/view-event/hackathon" class="item">Hackathon</a> <a
								href="/review" data-url="/review" class="item">review</a>
						</div>

					</div>
					<div class="ui pointing dropdown item">
						<div class="texts">Post</div>

						<div class="menu ">
							<a href="/postmessage" data-url="/postmessage" class="item">Share
								an Note</a> <a href="/post-event/symposium"
								data-url="/post-event/symposium" class="item">Symposium</a> <a
								href="/post-event/conference" data-url="/post-event/conference"
								class="item">Conference</a> <a href="/post-event/workshop"
								data-url="/post-event/workshop" class="item">Workshop</a> <a
								href="/post-event/guest-lecture"
								data-url="/post-event/guest-lecture" class="item">Guest
								Lecture</a> <a href="/post-event/Hackathon"
								data-url="/post-event/Hackathon" class="item">Hackathon</a>

						</div>

					</div>

					<div class="ui dropdown item">
						<div class="texts">Friends</div>

						<div class="menu ">
							<a href="/addfriends" data-url="/addfriends" class="item">AddFriends</a>
							<a href="/myfriends" data-url="/myfriends" class="item">MyFriends</a>
							<a href="/friendrequest" data-url="/friendrequest" class="item">FriendRequest</a>
						</div>

					</div>


					<a href="/login" data-url="/dashboard" class="item">Login</a> <a
						href="/signup" data-url="/dashboard" class="item">Register</a> <a
						href="/chat" data-url="/chat" class="item">Chat</a> <a
						href="/writereview" data-url="/writereview" class="item">writeareview</a>


					<a href="/notification" data-url="/notification" class="item">
						<i class="alarm icon icon"></i>
						<div style="top: 1em" class="floating ui teal circular label">
							${notification == null ? '0':notification}</div>
					</a>


				</div>
			</div>
		</div>
	</div>



	<div class="ui container mobile only grid">
		<ul id="menu2" style="display: none;">
			<li><a href="/messages" data-url="/messages" class="item">Notes</a></li>
			<li>View
				<ul>
					<li><a href="/view-event/symposium"
						data-url="/view-event/symposium" class="item">Symposium</a></li>
					<li><a href="/view-event/conference"
						data-url="/view-event/conference" class="item">Conference</a></li>
					<li><a href="/review" data-url="/review" class="item">review</a></li>
					<li><a href="/view-event/workshop"
						data-url="/view-event/workshop" class="item">Workshop</a></li>
					<li><a href="/view-event/guest-lecture"
						data-url="/view-event/guest-lecture" class="item">Guest
							Lecture</a></li>
					<li><a href="/view-event/hackathon"
						data-url="/view-event/hackathon" class="item">Hackathon</a></li>
				</ul>
			</li>

			<li>Post
				<ul>
					<li><a href="/postmessage" data-url="/postmessage"
						class="item">Share an Note</a></li>
					<li><a href="/post-event/symposium"
						data-url="/post-event/symposium" class="item">Symposium</a></li>
					<li><a href="/post-event/conference"
						data-url="/post-event/conference" class="item">Conference</a></li>
					<li><a href="/post-event/workshop"
						data-url="/post-event/workshop" class="item">Workshop</a></li>
					<li><a href="/post-event/guest-lecture"
						data-url="/post-event/guest-lecture" class="item">Guest
							Lecture</a></li>
					<li><a href="/post-event/Hackathon"
						data-url="/post-event/Hackathon" class="item">Hackathon</a></li>

				</ul>
			</li>

			<li>Friends

				<ul>
					<li><a href="/addfriends" data-url="/addfriends" class="item">AddFriends</a></li>
					<li><a href="/myfriends" data-url="/myfriends" class="item">MyFriends</a></li>
					<li><a href="/friendrequest" data-url="/friendrequest" class="item">FriendRequest</a></li>
				</ul>

			</li>


			<li><a href="/login" data-url="/dashboard" class="item">Login</a></li>
			<li><a href="/signup" data-url="/dashboard" class="item">Register</a></li>
			<li><a href="/chat" data-url="/chat" class="item">Chat</a></li>
			<li><a href="/writereview" data-url="/writereview" class="item">writeareview</a></li>
			<li><a href="/notification" data-url="/notification"
				class="item"><i class="alarm icon icon"></i><span
					class=" ui teal circular label">${notification == null ? '0':notification}
				</span></a></li>
		</ul>


	</div>
</sec:authorize>





<sec:authorize access="isAuthenticated()">


	<div class="ui container mobile only grid">
		<ul id="menu2" style="display: none;">
			<li><a href="/messages" data-url="/messages" class="item">Notes</a></li>
			<li>View
				<ul>
					<li><a href="/view-event/symposium"
						data-url="/view-event/symposium" class="item">Symposium</a></li>
					<li><a href="/view-event/conference"
						data-url="/view-event/conference" class="item">Conference</a></li>
					<li><a href="/view-event/workshop"
						data-url="/view-event/workshop" class="item">Workshop</a></li>
					<li><a href="/view-event/guest-lecture"
						data-url="/view-event/guest-lecture" class="item">Guest
							Lecture</a></li>
					<li><a href="/view-event/hackathon"
						data-url="/view-event/hackathon" class="item">Hackathon</a></li>
					<li><a href="/review" data-url="/review" class="item">review</a></li>
				</ul>
			</li>

			<li>Post
				<ul>
					<li><a href="/postmessage" data-url="/postmessage"
						class="item">Share an Note</a></li>
					<li><a href="/post-event/symposium"
						data-url="/post-event/symposium" class="item">Symposium</a></li>
					<li><a href="/post-event/conference"
						data-url="/post-event/conference" class="item">Conference</a></li>
					<li><a href="/post-event/workshop"
						data-url="/post-event/workshop" class="item">Workshop</a></li>
					<li><a href="/post-event/guest-lecture"
						data-url="/post-event/guest-lecture" class="item">Guest
							Lecture</a></li>
					<li><a href="/post-event/Hackathon"
						data-url="/post-event/Hackathon" class="item">Hackathon</a></li>
				</ul>
			</li>

			<li>Friends

				<ul>
					<li><a href="/addfriends" data-url="/addfriends" class="item">AddFriends</a></li>
					<li><a href="/myfriends" data-url="/myfriends" class="item">MyFriends</a></li>
					<li><a href="/friendrequest" data-url="/friendrequest"
						class="item">FriendRequest</a></li>
				</ul>

			</li>
			<li><a class="item" href="<c:url value="/dashboard" />">Dashboard</a></li>
			<li><a href="/profile" data-url="/profile" class="item">Profile</a></li>
			<li><a href="/chat" data-url="/chat" class="item">Chat</a></li>
			<li><a href="/writereview" data-url="/writereview" class="item">writeareview</a>
			<li>
			<li><a href="/notification" data-url="/notification"
				class="item"><i class="alarm icon icon"></i><span
					class=" ui teal circular label">${notification == null ? '0':notification}
				</span></a></li>
			<li><a class="item"
				href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
		</ul>
	</div>


	<div class="ui container computer tablet only grid">
		<div class="ui fixed inverted menu ">
			<div class="ui container">
				<a href="/" class="header item" style="color: #00b5ad;">
					<h1>
						<i class="student icon"></i>SymposiumHub
					</h1>
				</a> <a href="/messages" data-url="/messages" class="item">Notes</a>
				<div class="ui pointing dropdown item">
					<div class="text">View</div>

					<div class="menu ">
						<a href="/view-event/symposium" data-url="/view-event/symposium"
							class="item">Symposium</a> <a href="/view-event/conference"
							data-url="/view-event/conference" class="item">Conference</a> <a
							href="/view-event/workshop" data-url="/view-event/workshop"
							class="item">Workshop</a> <a href="/view-event/guest-lecture"
							data-url="/view-event/guest-lecture" class="item">Guest
							Lecture</a> <a href="/view-event/hackathon"
							data-url="/view-event/hackathon" class="item">Hackathon</a> <a
							href="/review" data-url="/review" class="item">review</a>
					</div>

				</div>
				<div class="ui pointing dropdown item">
					<div class="text">Post</div>

					<div class="menu ">
						<a href="/postmessage" data-url="/postmessage" class="item">Share
							an Note</a> 
						<a href="/post-event/symposium" data-url="/post-event/symposium"
							class="item">Symposium</a> <a href="/post-event/conference"
							data-url="/post-event/conference" class="item">Conference</a><a
							href="/post-event/workshop" data-url="/post-event/workshop"
							class="item">Workshop</a> <a href="/post-event/guest-lecture"
							data-url="/post-event/guest-lecture" class="item">Guest
							Lecture</a> <a href="/post-event/Hackathon"
							data-url="/post-event/Hackathon" class="item">Hackathon</a>

					</div>

				</div>

				<a class="item" href="<c:url value="/dashboard" />">Dashboard</a>

				<div class="ui dropdown item">
					<div class="texts">Friends</div>

					<div class="menu ">
						<a href="/addfriends" data-url="/addfriends" class="item">AddFriends</a>
						<a href="/myfriends" data-url="/myfriends" class="item">MyFriends</a>
						<a href="/friendrequest" data-url="/friendrequest" class="item">FriendRequest</a>
					</div>

				</div>


				<!-- <a href="/registrationfields"
					data-url="/registrationfields" class=" item">Registration Form</a> -->
				<div class="ui  dropdown item">
					<sec:authentication property="principal.username" />
					<i class="dropdown icon"></i>
					<div class="menu">
						<a href="/profile" data-url="/profile" class="item">Profile</a> <a
							class="item" href="<c:url value="/j_spring_security_logout" />">Logout</a>
					</div>

				</div>
				<a href="/chat" data-url="/chat" class="item">Chat</a> <a
					href="/writereview" data-url="/writereview" class="item">writeareview</a>
				<a href="/notification" data-url="/notification" class="item"><i
					class="alarm icon icon"></i>
					<div style="top: 1em" class="floating ui teal circular label">${notification == null ? '0':notification}
					</div></a>

			</div>
		</div>
	</div>





</sec:authorize>

