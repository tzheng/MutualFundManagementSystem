

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test="${ (empty errors) }"> 
	</c:when>
	
	<c:otherwise>
				<div class="alert alert-error">
					<c:forEach var="error" items="${errors}">
	   				${error} <br />
					</c:forEach>
	   			</div>
	</c:otherwise>
</c:choose>

