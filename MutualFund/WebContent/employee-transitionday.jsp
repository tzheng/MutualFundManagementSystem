
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--include header -->
<jsp:include page="header-employee-panel.jsp" />

<!--put your page content here 
		  ============================================
		 -->
		 <jsp:include page="error-list.jsp"></jsp:include>
		 
<form method="post" action="employee-transitionday.do">
<div class="row-fluid">
	<!-- customer name -->
	<h4>Transition Day</h4>
	<div class="span2">
		<label>Last Trading Day: ${ lastDate }</label>
	</div>
	<div class="span2">
		<label>Specify a date:</label>
		<c:choose>
			<c:when test="${ empty specifiedDate }">
				<input type="text"  class="dp span10" name = "specifiedDate" value=" ${ lastDate }" />
			</c:when>
			<c:otherwise>
				<input type="text"  class="dp span10" name = "specifiedDate" value=" ${ specifiedDate }" />
			</c:otherwise>
		</c:choose>
		
	</div>
</div>

<label>You should set closing prices to ${ fundListLength } funds.</label>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Fund Id</th>
			<th>Fund Name</th>
			<th>Fund Symbol
			<th>Last Trading Price</th>
			<th>Closing Price</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="fund" items="${ fundGeneralList }">
			<input type="hidden" name="fundId" value="${fund.fundId}" />
			<tr>
				<td>${ fund.fundId }</td>
				<td>${ fund.name }</td>
				<td>${ fund.symbol }</td>
				<td>${ fund.lastTradingPrice }</td>
				<td><input type="text" name="price" value="${ fund.lastTradingPrice }" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<input type="hidden" name="fundListLength" value="${ fundListLength }" />
<input type="submit" class="btn" name="button" value="Submit" />
</form>



<!--end of page content 
		  ============================================
		 -->

<!--include footer -->
<jsp:include page="footer-panel.jsp" />

