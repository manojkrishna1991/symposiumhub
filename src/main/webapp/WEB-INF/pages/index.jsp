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
<title>SymposiumHub Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/dist/semantic.min.css' />">


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

.pinkbackground {
	background-color: #e03997 !important;
	color: rgba(255, 255, 255, 0.9) !important;
}
</style>

</head>
<script src="/resources/dist/sockjs-0.3.4.js"></script>
<script src="/resources/dist/stomp.js"></script>




<script type="text/javascript">
	var stompClient = null;

	function setConnected(connected) {
		/*          document.getElementById('connect').disabled = connected;
		         document.getElementById('disconnect').disabled = !connected;
		         document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden'; */
		document.getElementById('response').innerHTML = '';
	}

	function connect() {
		var socket = new SockJS("http://localhost:8080/hello");
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/greetings/${roomName}', function(
					greeting) {
				showGreeting(JSON.parse(greeting.body).content,JSON.parse(greeting.body).name);
			});
		});
	}

	function disconnect() {
		if (stompClient != null) {
			stompClient.disconnect();
		}
		setConnected(false);
		console.log("Disconnected");
	}

	function sendName() {
		var name = document.getElementById('name').value;
		var roomName = "<c:out value='${roomName}' />";
		var userName = "<c:out value='${userName}' />";
		stompClient.send('/app/hello', {}, JSON.stringify({
			'roomName' : roomName,
			'name' : name,
			'userName' : userName
		}));
	}

	function showGreeting(message,name) {
		var response = document.getElementById('response');
		/* var p = document.createElement('p');
		p.style.wordWrap = 'break-word';
		p.appendChild(document.createTextNode(message)); */
		
		
		
		$( "#response" ).append("<p style=\"word-wrap: break-word;\"><b>"+name+"</b>:"+message+"</p>");
	}

	function addclass(obj) {

		$(".username").removeClass("active");
		$(obj).addClass("active");
	}
</script>


<body onload="connect()">

	<%@ include file="include/header.jsp"%>


	<div class="">

		<noscript>
			<h2 style="color: #ff0000">Seems your browser doesn't support
				Javascript! Websocket relies on Javascript being enabled. Please
				enable Javascript and reload this page!</h2>
		</noscript>


	<%-- 	<div class="ui visible right sidebar inverted vertical menu ">
			<div class="ui middle aligned selection list">
				<c:forEach var="users" items="${users}">

					<div class="item">
						<img class="ui avatar image" src="/images/avatar/small/helen.jpg" />
						<div class="content">
							<div style="color: white;" onclick="addclass(this);"
								class="username header" username="${users.getUsername()}">${users.getUsername()}</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div> --%>
		<!-- <div>
        <button id="connect" >Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
    </div> -->

		<!-- <div id="conversationDiv">
			<label>What is your name?</label><input type="text" id="name" />
			<button >Send</button>
			<p id="response"></p>
		</div> -->
	</div>

	<div class="ui main  container ">

		<div class="ui  grid">
		
		

			<div class="four wide column">
			
			<h4 class="ui dividing header">Online Users</h4>

				<div class="ui middle aligned divided list" style="max-height: 300px; overflow-y: scroll;">
					<c:forEach var="users" items="${users}">

					<div class="item">
						<img class="ui avatar image" src="/images/avatar/small/helen.jpg" />
						<div class="content">
							<div  onclick="addclass(this);"
								class="username header" username="${users.getUsername()}">${users.getUsername()}</div>
						</div>
					</div>
				</c:forEach>
				</div>

			</div>

			<div class="eight wide column">
				<div class="ui comments">
					<h3 class="ui dividing header">Chat</h3>
					<div style="max-height: 300px; overflow-y: scroll;">
						<p id="response">

							<c:forEach var="chatHistory" items="${chatHistory}">

								<p style="word-wrap: break-word;"><b>${chatHistory.userName}</b>:${chatHistory.name}</p>

							</c:forEach>
						</p>
					</div>
					<form class="ui reply form">
						<div class="field">
							<textarea id="name"  style="height:4em !important;min-height:4em !important;"></textarea>
						</div>
						<div id="sendName" onclick="sendName();"
							class="ui blue labeled submit icon button">
							<i class="icon edit"></i> Add Reply
						</div>
					</form>
				</div>
			</div>
			</div>
			
		</div>

</body>

<script src="/resources/assets/library/jquery.min.js"></script>
<script src="/resources/dist/components/dimmer.min.js"></script>
<script src="/resources/dist/components/modal.min.js"></script>
<script src="/resources/dist/components/transition.min.js"></script>
<script src="/resources/dist/components/form.min.js"></script>
<script src="/resources/dist/components/sidebar.min.js"></script>

<%@ include file="include/footer.jsp"%>
</html>