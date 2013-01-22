

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${ (empty successes) }">
	</c:when>

	<c:otherwise>
		<div class="alert alert-success">
			<c:forEach var="success" items="${successes}">
	   				${success} <br />
			</c:forEach>
		</div>
	</c:otherwise>
</c:choose>

