<div class="ui main  container">


		<div class="ui segment">





			<h4 class="ui horizontal divider header">
				<i class="tag icon"></i>WorkShop
			</h4>

			<div class="ui grid">
				<c:forEach var="conference" items="${conference}">

					<div style="cursor: pointer;" onclick="redirect(this)"
						data-href="/viewconference/${conference.id}/${conference.name}"
						' class="sixteen wide mobile eight wide tablet four wide computer column">
						<h4 class="ui dividing header">
							Date of Event
							<fmt:formatDate type="date" value="${conference.dateOfEvent}" />
						</h4>
						<div class="ui card">
							<div class="image dimmable"
								style="min-height: 238px; background: #fff;">
								<div class="ui blurring inverted dimmer transition hidden">
									<div class="content">
										<div class="center">
											<div class="ui teal button"></div>
										</div>
									</div>
								</div>
								<c:set var="imageUrl" value="${conference.imageUrl[0]}" />
								<c:if test="${not empty conference.compressedPath }">
									<c:set var="imageUrl" value="${conference.compressedPath[0]}" />
								</c:if>


								<c:choose>
									<c:when test="${not empty imageUrl}">
										<img class="lazy cardimage" data-original="${imageUrl}"
											src="/resources/assets/images/2.gif" style="width: 100%;">
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
									<a class="group">${conference.name}</a>
								</div>
							</div>
							<div class="extra content">
								<a href="/viewconference/${conference.id}/${conference.name}"
									class="right floated created">view more</a>
								<%--  <a href="/viewconference/${conference.id}/${conference.name}" class="friends">
        register</a> --%>
							</div>
						</div>
					</div>


				</c:forEach>

			</div>
			<div style="text-align: center;">

				<a href="/viewconference"><button
						style="margin: 10px; text-align: center;"
						class="massive ui teal button">View All</button></a>
			</div>

		</div>
	</div>