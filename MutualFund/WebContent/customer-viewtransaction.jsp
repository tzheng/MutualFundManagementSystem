	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	   
	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	<!--display path  -->	   		 		
		<div>
				<ul class="breadcrumb">
						<li><a href="view-account.do"> <i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">Transaction History</li>  
				</ul>
		</div>	 
		
	 
		  <h4>Transaction History</h4>  <hr>
		 <div>  <!--  Here list the Transactions -->
		 		<table class="table table-striped" >
		 				<thead>
	   								<tr class="info" style="text-align: right;">
                                          <th style="text-align: center;">Date</th>
    										<th>Operation</th>
    										<th>Fund Name</th>
    										<th style="text-align: center;">Share Number</th>
    										<th style="text-align: center;">Share Price</th>
    										<th style="text-align: center;">Dollar Amount</th>
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
  							   		
  							   			<c:choose>
  							   				<c:when test="${ empty history.transactionDate }">
  							   					<td style="text-align: center">-</td> 
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: center;">${history.transactionDate} </td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<td>${history.operation} </td>
  							   			
  							   			<c:choose>
  							   					<c:when test = "${empty history.fundName}">
  							   							<td style="text-align: center">-</td>
  							   					</c:when>
  							   					<c:otherwise>
  							   						<td>${history.fundName} </td>
  							   					</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<c:set var="amount" value="${history.dollarAmount}"></c:set>
  							   			<c:set var="zero" value = "0.00"></c:set> 
  							   			
  							   			<c:set var="shares" value="${history.shareNumber}"></c:set>
  							   			<c:set var="zero" value = "0.000"></c:set> 
  							   			<c:choose>
  							   				<c:when test= "${shares eq zero }">
  							   						<td style="text-align: center">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 40px;">
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
  							   					<td style="text-align: right; padding-right: 40px;">
  							   						<fmt:formatNumber type="number" pattern="#,##0.00" value="${history.sharePrice} " ></fmt:formatNumber>
  							   					</td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<c:set var="dollar" value="${history.dollarAmount}"></c:set>
  							   			<c:choose>
  							   				<c:when test= "${dollar eq zero1 }">
  							   						<td style="text-align: center">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 40px;"> 
  							   						<fmt:formatNumber type="number" pattern="#,##0.00" value="${history.dollarAmount}" ></fmt:formatNumber>  
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
		
