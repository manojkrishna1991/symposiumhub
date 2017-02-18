

<c:set value="${param['message']}" var="message"></c:set>
<c:set value="${param['messagetext']}" var="messagetext"></c:set>




<c:if test="${message eq 'success'}">

<div class="ui success message alert success">
	<i class="close icon"></i>
	<div class="header"></div>
	<p style="color: white;"><i class="check circle icon"></i> ${messagetext}</p>
</div>

</c:if>

<c:if test="${message eq 'error'}">


<div class="ui positive message alert error">
	<i class="close icon"></i>
	<div class="header"></div>
	<p style="color: white;">
		${messagetext}</p>
	
</div>

</c:if>
