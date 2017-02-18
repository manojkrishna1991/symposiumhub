
<div id="login" class="ui modal tiny modalwidth">
	<i class="close icon"></i>
	<div class="header">Login</div>
	<div class="content">
		<div class="">
			<div class="column">
				<form name='facebookSocialloginForm'
					action="<c:url value='/auth/facebook?scope=email,user_about_me,user_birthday' />"
					method='POST'>
					<button type="submit" style="width: 100%; margin: 2px;"
						class="ui facebook button">
						<i class="facebook icon"></i>Log In with Facebook
					</button>
					<div class="clear"></div>
				</form>
				<form name='GoogleSocialloginForm'
					action="<c:url value='/auth/google' />" method='POST'>
					<button type="submit" style="margin: 2px; width: 100%;"
						class="ui google plus button">
						<i class="google plus icon"></i> Log In with Google
					</button>
					<input type="hidden" name="scope"
						value="https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo#email https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/tasks https://www-opensocial.googleusercontent.com/api/people https://www.googleapis.com/auth/plus.login" />
					<div class="clear"></div>
				</form>


			</div>

		</div>

	</div>
	<div class="actions"></div>
</div>



<script src="/resources/dist/components/dropdown.min.js"></script>
<script>

$(document).on('click', '.order-button', function() { 
	
var obj=$(this);
var redirecturl = $(obj).attr("data-url");
<c:if test="${not empty authenticated}">
window.location.href = redirecturl;
</c:if>
	<c:if test="${empty authenticated}">
	$('#login')
	.modal('show')
	;
	</c:if>
	
	urlvalue = "/addcokieforredirection?redirecturl=" + redirecturl;
	var oMyForm = new FormData();
	jQuery.ajax({
		type : "GET",
		url : urlvalue,
		data :oMyForm,
		dataType : 'text',
		processData : false,
		contentType : false,
		success : function(result) {
		
			
		}
	});

});
$(document).on('click', '.subscribe-button', function() { 
	var obj=$(this);
	var email=$(".email:not(:hidden)").val();
	if(!isEmail(email))
		{
		alert("enter valid email");
		return ;
		}
	
	var oMyForm = new FormData();
	oMyForm.append("email",email);
	jQuery.ajax({
		type : "POST",
		url : "/subscribeemail",
		data :oMyForm,
		dataType : 'text',
		processData : false,
		contentType : false,
		success : function(result) {
		
			alert("Thanks for subscribing.you will receive regular update about upcoming symposiums.");
			$(".email:not(:hidden)").val('');
		}
	});

});

function isEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}

$('.ui.dropdown')
.dropdown({
    on: 'hover'
})
;

</script>


<script src="/resources/dist/jquery.slicknav.min.js"></script>
<script>
      $(document).ready(function() {
	$(function(){
		$('#menu2').slicknav();
		$('.slicknav_menu').prepend('<a href="/" class="header item" style=\"color: #00b5ad;\"><h1><i class="student icon"></i>SymposiumHub</h1></a>');
	});
      });
      
  	$(".close.icon").click(function(){
		  $(this).parent().hide();
		});
</script>



<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-82396405-1', 'auto');
  ga('send', 'pageview');

</script>






<div class="ui inverted vertical footer segment ">
	<div class="ui center aligned container">
		<!-- <div class="ui stackable inverted divided grid">
        <div class="three wide column">
          <h4 class="ui inverted header">Group 1</h4>
          <div class="ui inverted link list">
            <a href="#" class="item">Link One</a>
            <a href="#" class="item">Link Two</a>
            <a href="#" class="item">Link Three</a>
            <a href="#" class="item">Link Four</a>
          </div>
        </div>
        <div class="three wide column">
          <h4 class="ui inverted header">Group 2</h4>
          <div class="ui inverted link list">
            <a href="#" class="item">Link One</a>
            <a href="#" class="item">Link Two</a>
            <a href="#" class="item">Link Three</a>
            <a href="#" class="item">Link Four</a>
          </div>
        </div>
        <div class="three wide column">
          <h4 class="ui inverted header">Group 3</h4>
          <div class="ui inverted link list">
            <a href="#" class="item">Link One</a>
            <a href="#" class="item">Link Two</a>
            <a href="#" class="item">Link Three</a>
            <a href="#" class="item">Link Four</a>
          </div>
        </div>
        <div class="seven wide column">
          <h4 class="ui inverted header">Footer Header</h4>
          <p>Extra space for a call to action inside the footer that could help re-engage users.</p>
        </div>
      </div> -->
		<div class="ui inverted section divider"></div>
		<a class="item" href="/" style="color: #00b5ad;"> <!-- <img style="width: 200px; height: 46px;"
			src="/resources/assets/images/logo.png"
			class="ui centered mini image"> -->
			<h1>SymposiumHub</h1>
		</a>
		<div class="ui horizontal inverted small divided link list">
		<div class="pad10 ui action left icon input visible">
						<input class="email" type="email" required="required"
							placeholder="Enter Your Email">
						<div class="subscribe-button ui teal button">Subscribe</div>
					</div>
      <!--   <a class="item" href="/">Home</a>
        <a class="item" href="/">Login</a>
        <a class="item" href="/">Register</a>
        <a class="item" href="/"></a> -->
      </div> 
	</div>
</div>
