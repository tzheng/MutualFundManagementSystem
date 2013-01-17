

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="error" items="${errors}">
	   				<div class="alert alert-error">
	   					${error}
	   				</div>
</c:forEach>