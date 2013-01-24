	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	    
	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
			   		
		<!--put your page content here 
		  ============================================
		 -->
		 <h4>Transaction History Filter</h4> <hr>
		 <div>  <!--  This is filter for Transaction History -->
		 		<jsp:include page="error-list.jsp" />
		 		 <form class="form-horizontal" method="post" action="employeehistory.do">
				 		 <div class="control-group">
							    <label class="control-label" for="scustomer">Customer Username</label>
							    <div class="controls">
							          	<input type="text" id="scustomer" name="username" placeholder="Customer Username">
							    </div>
						  </div>
						  
						  <div class = "control-group">
	   									<div class="controls">
	   											<button type="submit" class="btn">Search</button>	   										
	   									</div>
	   					  </div>
		 		 </form>	

		 </div>
		
		<hr>
		  <h4>Transaction History</h4>  <hr>
		 <div>  <!--  Here list the Transactions -->
		 		<table class="table table-striped">
		 				<thead>
	   								<tr class="info">
	   									<th>Customer</th>
                                          <th>Transaction Date</th>
    										<th>Operation</th>
    										<th>Fund Name</th>
    										<th>Share Number</th>
    										<th>Share Price</th>
    										<th>Dollar Amount</th>
    										<th>Transaction Status</th>
  									</tr>
  						</thead>
  						<tbody>
  							   <c:forEach var="history" items="${transactionHistory}">
  							   		<c:set var="status" value="${history.transactionStatus}"></c:set>
  							   		<c:set var="pending" value="Pending"> </c:set>
  							   		<c:set var="rejected" value = "Rejected"></c:set>
  							   		<c:choose>
	  							   		<c:when test="${status eq pending}">
	  							   			<tr class="info">
	  							   		</c:when>
	  							   		<c:when test="${status eq rejected}">
	  							   			<tr class="error">
	  							   		</c:when>
	  							   		<c:otherwise>
	  							   			<tr>
	  							   		</c:otherwise>
  							   		</c:choose>
  							   		
  							   		
  							   			<td>${history.username } </td>
  							   			<td>${history.transactionDate} </td>
  							   			<td>${history.operation} </td>
  							   			<td>${history.fundName} </td>
  							   			<td>${history.shareNumber} </td>
  							   			<td>${history.sharePrice} </td>
  							   			<td>${history.dollarAmount} </td>
  							   			<td>${history.transactionStatus} </td>
  							   		</tr>
  							   		
  							   </c:forEach>
  						</tbody>
  				</table>
		 </div>
		
		
		
		
		
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		
