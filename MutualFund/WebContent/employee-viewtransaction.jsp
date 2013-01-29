	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	    
	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
			   		
		<div>
				<ul class="breadcrumb">
						<li><a href="employee-mainpanel.jsp"><i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">Transaction History</li>
				</ul>
		</div>
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
                                          <th>Date</th>
    										<th>Operation</th>
    										<th>Fund Name</th>
    										<th>Share Number</th>
    										<th>Share Price</th>
    										<th>Dollar Amount</th>
    										<th>Status</th>
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
  							   			
  							   			<c:choose>
  							   				<c:when test="${ empty history.transactionDate }">
  							   					<td style="text-align: center">-</td> 
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td>${history.transactionDate} </td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<td>${history.operation} </td>
  							   			<c:choose>
  							   					<c:when test = "${empty history.fundName }">
  							   							<td style="text-align: center">-</td>
  							   					</c:when>
  							   					<c:otherwise>
  							   						<td>${history.fundName} </td>
  							   					</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<c:set var="shares" value="${history.shareNumber}"></c:set>
  							   			<c:set var="zero" value = "0.000"></c:set> 
  							   			<c:choose>
  							   				<c:when test= "${shares eq zero }">
  							   						<td style="text-align: center">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 20px;">
  							   						<fmt:formatNumber type="number"  pattern="#,##0.000" value="${history.shareNumber} " />
  							   					</td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<c:set var="price" value="${history.sharePrice}"></c:set>
  							   			<c:set var="zero1" value = "0.00"></c:set> 
  							   			<c:choose>
  							   				<c:when test= "${price eq zero1 }">
  							   						<td style="text-align: center">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 20px;">
  							   						<fmt:formatNumber type="number"  pattern="#,##0.00" value="${history.sharePrice}  " />
  							   					</td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<c:set var="dollar" value="${history.dollarAmount}"></c:set>
  							   			<c:choose>
  							   				<c:when test= "${dollar eq zero1 }">
  							   						<td style="text-align: center;">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 20px;">
  							   						<fmt:formatNumber type="number"  pattern="#,##0.00" value="${history.dollarAmount}  " />
  							   					</td>
  							   				</c:otherwise>
  							   			</c:choose>
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
		
