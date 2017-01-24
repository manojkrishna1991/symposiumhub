<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>

<head>
  <!-- Standard Meta -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

  <!-- Site Properties -->
  <title>Symposium Hub List Of Symposium</title>
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
  img.lazy.cardimage {
    min-height: 238px ! important;
}
  </style>

</head>

<body style="background-color: #EAEAEA;">

<%@ include file="include/header.jsp" %>
 <div class="pusher">
   
 
<div class="ui grid" style="margin:121px 13px;">

 <h4 class="ui horizontal divider header">
  <i class="tag icon"></i>Symposium
</h4>

<c:forEach var="symposium" items="${symposium}">

  <div  style="cursor:pointer; "  onclick="redirect(this)" data-href= "/symposiumview/${symposium.symposiumid}/${symposium.name}"' class="sixteen wide mobile eight wide tablet four wide computer column">
    <h4 class="ui dividing header">Date of Event <fmt:formatDate type="date" value="${symposium.dateOfEvent}" /></h4>
  <div class="ui card">
    <div class="image dimmable" style="min-height: 238px;background:#fff;">
      <div class="ui blurring inverted dimmer transition hidden">
        <div class="content">
          <div class="center">
            <div class="ui teal button"> </div>
          </div>
        </div>
      </div>
      <c:set var="imageUrl" value="${symposium.imageUrl}" />
      <c:if test="${not empty symposium.compressedPath }">
            <c:set var="imageUrl" value="${symposium.compressedPath}" />
      </c:if>


							<c:choose>
								<c:when test="${not empty imageUrl}">
									<img class="lazy cardimage" data-original="${imageUrl}"
										src="/resources/assets/images/2.gif" style="width: 100%;" >
								</c:when>
								<c:otherwise>
									<img class="cardimage" style="width: 100%;"
										src="/resources/assets/images/wireframe/image.png">
								</c:otherwise>
							</c:choose>

						</div>
    <div class="content">
      <div class="header">Name</div>
      <div class="meta">
        <a class="group">${symposium.name}</a>
      </div>
      <br>
      <div class="header"> Department</div>
      <div class="meta">
        <a class="group">${symposium.department}</a>
      </div>
            <br>
      <div class="header">College Name</div>
      <div class="meta">
        <a class="group">${symposium.collegeName}</a>
      </div>
    </div>
    <div class="extra content">
      <a href="/symposiumview/${symposium.symposiumid}/${symposium.name}" class="right floated created">view more</a>
      <a href="/symposiumview/${symposium.symposiumid}/${symposium.name}" class="friends">
        register</a>
    </div>
  </div>
  </div>
 

</c:forEach>
  
  </div>
  <!-- <div class="ui grid" style="margin:46px 13px;">
   <h4 class="ui horizontal divider header">
  <i class="tag icon"></i>
  Symposium
</h4>
  <div class="sixteen wide mobile eight wide tablet four wide computer column">
  
    <h4 class="ui dividing header">Starts From Dec 21-23, 2016</h2>
<div class="ui card">
    <div class="image dimmable">
      <div class="ui blurring inverted dimmer transition hidden">
        <div class="content">
          <div class="center">
            <div class="ui teal button"> </div>
          </div>
        </div>
      </div>
      <img style="width:100%;" src="/resources/assets/images/wireframe/image.png">
    </div>
    <div class="content">
      <div class="header">Venue</div>
      <div class="meta">
        <a class="group">Veltech Engineering College</a>
      </div>
      <div class="description">1st International Conference on Advanced Computing and Intelligent Engineering (ICACIE2016) from Dec 21-23, 2016 organized at C.V. Raman College of Engineering, Bhubaneswar, Odisha, India.</div>
    </div>
    <div class="extra content">
      <a class="right floated created">view</a>
      <a class="friends">
        register</a>
    </div>
  </div>
  </div>
  <div class="sixteen wide mobile eight wide tablet four wide computer column">
    <h4 class="ui dividing header">Starts From Dec 21-23, 2016</h2>
<div class="ui card">
    <div class="image dimmable">
      <div class="ui blurring inverted dimmer transition hidden">
        <div class="content">
          <div class="center">
            <div class="ui teal button"></div>
          </div>
        </div>
      </div>
      <img style="width:100%;" src="/resources/assets/images/wireframe/image.png">
    </div>
    <div class="content">
      <div class="header">Venue</div>
      <div class="meta">
        <a class="group">Veltech Engineering College</a>
      </div>
      <div class="description">1st International Conference on Advanced Computing and Intelligent Engineering (ICACIE2016) from Dec 21-23, 2016 organized at C.V. Raman College of Engineering, Bhubaneswar, Odisha, India.</div>
    </div>
    <div class="extra content">
      <a class="right floated created">view</a>
      <a class="friends">
        register</a>
    </div>
  </div>
  
  </div>
  <div class="sixteen wide mobile eight wide tablet four wide computer column">
    <h4 class="ui dividing header">Starts From Dec 21-23, 2016</h2>
 <div class="ui card">
    <div class="image dimmable">
      <div class="ui blurring inverted dimmer transition hidden">
        <div class="content">
          <div class="center">
            <div class="ui teal button"> </div>
          </div>
        </div>
      </div>
      <img style="width:100%;" src="/resources/assets/images/wireframe/image.png">
    </div>
    <div class="content">
      <div class="header">Venue</div>
      <div class="meta">
        <a class="group">Veltech Engineering College</a>
      </div>
      <div class="description">1st International Conference on Advanced Computing and Intelligent Engineering (ICACIE2016) from Dec 21-23, 2016 organized at C.V. Raman College of Engineering, Bhubaneswar, Odisha, India.</div>
    </div>
    <div class="extra content">
      <a class="right floated created">view</a>
      <a class="friends">
        register</a>
    </div>
  </div>
  </div>
  <div class="sixteen wide mobile eight wide tablet four wide computer column">
 
    <h4 class="ui dividing header">Starts From Dec 21-23, 2016</h2>
<div class="ui card">
    <div class="image dimmable">
      <div class="ui blurring inverted dimmer transition hidden">
        <div class="content">
          <div class="center">
            <div class="ui teal button"> </div>
          </div>
        </div>
      </div>
      <img style="width:100%;" src="/resources/assets/images/wireframe/image.png">
    </div>
    <div class="content">
      <div class="header">Venue</div>
      <div class="meta">
        <a class="group">Veltech Engineering College</a>
      </div>
      <div class="description">1st International Conference on Advanced Computing and Intelligent Engineering (ICACIE2016) from Dec 21-23, 2016 organized at C.V. Raman College of Engineering, Bhubaneswar, Odisha, India.</div>
    </div>
    <div class="extra content">
      <a class="right floated created">view</a>
      <a class="friends">
        register</a>
    </div>
  </div>
  </div>
</div> -->
 </div>
 
 <script src="/resources/assets/library/jquery.min.js"></script>
    <script src="/resources/dist/components/dimmer.min.js"></script>
   <script src="/resources/dist/components/modal.min.js"></script>
    <script src="/resources/dist/components/transition.min.js"></script>
     <script src="/resources/dist/components/form.min.js"></script>
      <script src="/resources/dist/components/sidebar.min.js"></script>
      <script src="/resources/assets/library/jquery.lazyload.min.js"></script>
    
 <%@ include file="include/footer.jsp" %>
</body>
<script>
function redirect(obj){
	var link=$(obj).attr("data-href");
	var newlink=link.replace("''","");
	window.location.href=newlink;
	}
$(document).ready(function () {
     
	$(document).ready(function () {
	     
		$("img.lazy").lazyload({
			event : "sporty"
		});
		$(window).bind("load", function () {
	        var timeout = setTimeout(function () { $("img.lazy").trigger("sporty")
	        	
	        
	        }, 5000);
	    }); 

		  });

	  });
	
</script>


</html>
