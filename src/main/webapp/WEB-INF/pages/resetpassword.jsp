
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
<title>SymposiumHub Login Page</title>
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

.headerDivider {
	border-left: 1px solid #38546d;
	border-right: 1px solid #16222c;
	height: 80px;
	position: absolute;
	right: 249px;
	top: 10px;
}
</style>

</head>
<body>
	<%@ include file="include/header.jsp"%>
	
	<c:if test="${password}">
	<div class="ui main  container ">
	
	<div class="alert error" style="text-align:center;">
	Invalid request please contact admin at symposiumhub@gmail.com
	</div>
	</div>
	</c:if>

]	<c:if test="${activation}">
	
	<div class="ui main text container ">
		
		<div class="ui segment">
			<c:if test="${error1}">
				<div style="text-align: center;" class="alert error">Username
				does not exists</div>
			</c:if>
			<div class="ui message">
				<p>Reset Password for ${user.userId}</p>
			</div>

			
			


			<form class="login_form" name='loginForm'
				action="<c:url value='/resetpassword' />" method='POST'>

				<div class="column">
					<div class="ui form">

						<div class="field">
							<label>Enter Password:</label>
							<div class="ui left icon input">
								<input type="text" class="textbox1" id="userid"
									 name="password" placeholder="Enter Password" required=""> 
									<i class="lock icon"></i>
							</div>
						</div>
						<div class="field">
							<label>Re-enter Password:</label>
							<div class="ui left icon input">
								<input type="text" class="textbox1" id="userid"
									name="userid" placeholder="Re-enter Password" required=""> 
									<i class="lock icon"></i>
							</div>
						</div>
						<input type="hidden" name="userId" value="${user.userId}">
						<button type="submit" class="ui blue submit button">Submit</button>
						<a style="text-decoration:underline" href="/login">Return to login page</a> 

					</div>
				</div>
			</form>

		</div>
	
		<div class="ui two column middle aligned very relaxed stackable grid">

		</div>




		<%-- 	<form class="login_form" name='loginForm' action="<c:url value='../j_spring_security_check' />" method='POST'>
					<h1>Login Into Your Account</h1>
					<ul>
						<li>
							<input type="text" class="textbox1" id="username" name="j_username" placeholder="Username" required="">
							<p><img src="../images/contact.png" alt=""></p>
						</li>
						<li>
							<input type="password" id="password" name="j_password" class="textbox2" placeholder="Password">
							<p><img src="../images/lock.png" alt=""></p>
						</li>
					</ul>
					<input type="submit" name="Sign In" value="Sign In">
					<div class="clear"></div>	
					<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Remember me</label>
					<div class="forgot">
						<a href="#">forgot password?</a>
					</div>	
					<div class="clear"></div>	
				</form> --%>

	</div>

	</c:if>

	<%-- 	<c:url value="../services/signup" var="signupurl" />`
			<div class="account">
				<h2><a href="${signupurl}">Don't have an account? Sign Up!</a></h2>
				<div class="span">
					<form name='facebookSocialloginForm'
            		  action="<c:url value='../auth/facebook?scope=email,user_about_me,user_birthday' />" method='POST'>
							<img src="../images/facebook.png" alt="">
							<button type="submit">
								<i>Sign In with Facebook</i>
							</button>	
							<div class="clear"></div>
					</form>		
				</div>	
				<div class="span1">
					<form name='TwitterSocialloginForm'
            		  action="<c:url value='../auth/twitter?scope=email,user_about_me,user_birthday' />" method='POST'>
						<img src="../images/twitter.png" alt="">
						<button type="submit">
							<i>Sign In with Twitter</i>
						</button>			
						<div class="clear"></div>
					</form>
				</div>
				<div class="span2">
					<form name='LinkedInSocialloginForm'
            		  action="<c:url value='../auth/linkedin' />" method='POST'>
						<img src="../images/linkedin.png" alt="">
						<button type="submit">
							<i>Sign In with Linkedin</i>
						</button>	
						<div class="clear"></div>
					</form>
				</div>
				<div class="span3">
                	<form name='GoogleSocialloginForm'
                      action="<c:url value='../auth/google' />" method='POST'>
                		<img src="../images/gmail2.jpg" alt="">
                		<button type="submit">
                			<i>Sign In with Google</i>
                		</button>
                		<input type="hidden" name="scope" value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/tasks https://www-opensocial.googleusercontent.com/api/people https://www.googleapis.com/auth/plus.login" />
                		<div class="clear"></div>
                	</form>
                				</div>
			</div>	
			<div class="clear"></div>	
		</div>

	</div>
 --%>

	<script src="/resources/assets/library/jquery.min.js"></script>
	<script src="/resources/dist/components/dimmer.min.js"></script>
	<script src="/resources/dist/components/modal.min.js"></script>
	<script src="/resources/dist/components/transition.min.js"></script>
	<script src="/resources/dist/components/form.min.js"></script>
	<script src="/resources/dist/components/sidebar.min.js"></script>

	<%@ include file="include/footer.jsp"%>

</body>
</html>
