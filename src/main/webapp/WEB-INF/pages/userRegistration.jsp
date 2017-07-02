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
<title>SymposiumHub Registration</title>
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



	<div class="ui main text container ">
		<div class="ui segment">
		
		<div class="ui message">
  <div class="header">
  Welcome To SymposiumHub !!!
  </div>
  <p>Join SymposiumHub to connect with college students just like you</p>
</div>

		<div class="column">
					<form name='facebookSocialloginForm'
						action="<c:url value='/auth/facebook?scope=email,user_about_me,user_birthday' />"
						method='POST'>
						<button type="submit" style="width: 100%; margin: 2px;"
							class="ui facebook button">
							<i class="facebook icon"></i>Facebook
						</button>
						<div class="clear"></div>
					</form>
					<form name='GoogleSocialloginForm'
						action="<c:url value='/auth/google' />" method='POST'>
						<button type="submit" style="margin: 2px; width: 100%;"
							class="ui google plus button">
							<i class="google plus icon"></i>Google
						</button>
						<input type="hidden" name="scope"
							value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/tasks https://www-opensocial.googleusercontent.com/api/people https://www.googleapis.com/auth/plus.login" />
						<div class="clear"></div>
					</form>


				</div>
				
								<div class="ui horizontal divider">Or</div>
				
		<form class="login_form" name='loginForm' action="<c:url value='/user/register' />" method='POST'>
				<div class="column">
					<div class="ui form" >
					  <div class="two fields">
					
						<div class="field">
							<label>Username</label>
							<div class="ui left icon input">
								<input type="text" id="userId" name="userId" placeholder="Username"  /> <i
									class="user icon"></i>
							</div>
						</div>
						<div class="field">
							<label>EmailId</label>
							<div class="ui left icon input">
								<input type="text" id="email" name="email" placeholder="EmailId"  />
								<i class="mail icon"></i>
							</div>
						</div>
						
						
						</div>
						  <div class="two fields">
						  
						  <div class="field">
							<label>Password</label>
							<div class="ui left icon input">
								<input type="password" id="password" name="password" 
									class="textbox2" placeholder="Password"> <i
									class="lock icon"></i>
							</div>
						</div>
						
						<div class="field">
							<label>Confirm Password</label>
							<div class="ui left icon input">
								<input type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm Password"> <i
									class="lock icon"></i>
							</div>
						</div>
						
						</div>
												  <div class="two fields">
						
						<div class="field">
							<label>FirstName</label>
							<div class="ui left icon input">
								<input type="text" id="firstName" name="firstName" placeholder="FirstName" > 
								<i class="user icon"></i>

							</div>
						</div>
						<div class="field">
							<label>LastName</label>
							<div class="ui left icon input">
								<input type="text" id="lastName" name="lastName" placeholder="LastName"> 
								<i class="user icon"></i>

							</div>
						</div>
						</div>

					<!-- 	<div class="field">
							<div class="ui checkbox">
								<input type="checkbox" name="checkbox" checked="" tabindex="0"
									class="hidden"> <label>Remember me</label>
							</div>

						</div> -->
						<input type="hidden" id="socialProvider" name="socialProvider" value="NONE" />
						
						<button  class="ui blue submit button">Register</button>
						
						<span style="color:red"><c:out value="${param.message}" /></span>


					</div>
				</div>
				</form>
				
			</div>
			</div>
			

<!--        <div id="registrationForm">
       
       <TABLE border="0" cellspacing="5" cellpadding="3">
       <TR>
                   <th> UserId </th>
                   <td><input type="text" id="userId" name="userId" placeholder="UserId"  /></td>
              </TR>
       <TR>
            <th> EmailId </th>
            <td><input type="text" id="email" name="email" placeholder="EmailId"  /></td>
       </TR>
       <TR>
            <th> FirstName </th>
            <td><input type="text" id="firstName" name="firstName" placeholder="FirstName"  /></td>
       </TR>
       <TR>
            <th> LastName   </th>
            <td><input type="text" id="lastName" name="lastName" placeholder="LastName"  /></td>
       </TR>
       <TR>
            <th> Phone Number </th>
            <td><input type="text" id="phoneno" name="phoneno" placeholder="Phone Number"  /></td>
       </TR>
       <TR>
            <th> Password  </th>
            <td><input type="password" id="password" name="password" placeholder="Password" /></td>
       </TR>
       <TR>
            <th> Confirm Password </th>
            <td><input type="password" id="confirmpassword" name="confirmpassword" placeholder="Confirm Password" /></td>
       </TR>
       <TR>
            <th>
                <input type="hidden" id="socialProvider" name="socialProvider" value="NONE" />
                <button type="button" id="doRegister" onclick=" proceed()">Submit</button>
            </th>
       </TR>
       </TABLE>
       
       </div>
</div>

</div> -->

 <script src="/resources/assets/library/jquery.min.js"></script>
    <script src="/resources/dist/components/dimmer.min.js"></script>
   <script src="/resources/dist/components/modal.min.js"></script>
    <script src="/resources/dist/components/transition.min.js"></script>
     <script src="/resources/dist/components/form.min.js"></script>
      <script src="/resources/dist/components/sidebar.min.js"></script>

 <%@ include file="include/footer.jsp" %>


<script>

     function  proceed(){
            var person = {
                        userId: $("#userId").val(),
                        email:$("#email").val(),
                        firstName:$("#firstName").val(),
                        lastName: $("#lastName").val(),
                        phoneno:$("#phoneno").val(),
                        password: $("#password").val(),
                        socialProvider:$("#socialProvider").val()
                    }


            $.ajax({
              type: "post",
              url: '/user/register',
              data: JSON.stringify(person),
              dataType: 'json',
              contentType: "application/json; charset=utf-8",
              success: function () {
                alert('success');
              }
            });

        }

</script>

</body>
</html>