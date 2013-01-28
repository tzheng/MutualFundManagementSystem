	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
	   	<!--display path  -->	   		 		
		<div>
				<ul class="breadcrumb">
						<li><a href="customer-mainpanel.do"> <i class="icon-home"></i> Home</a></li>
				</ul>
		</div>	 
		
	   	<!--put your page content here 
		  ============================================
		 -->
		
		<div class="span11">
				
				<p>Your current account balance is: <b>$  ${currentBalance} </b> </p>
				
				<c:choose>
						<c:when test ="${ empty lastTradingDate }">
							<h5>You don't have any transactions currently. </h5>
						</c:when>
						<c:otherwise>
							<p>Your Last trading date is: <b>${lastTradingDate }</b>
							<p>During the last trading day:
							<p><b >${processedNumber}</b> of your transactions have been processed. </p>
							<p><b>${rejectedNumber}</b> of your transactions have been rejected. </p>
							<a href="customerhistory.do" >View Transaction History</a>
						</c:otherwise>
				</c:choose>
				
				
				<!-- 
				<hr>
				
				<h4>Your funds: </h4>
				<table class="table">
						<thead>
								<tr>
									<th>#</th>
									<th>Fund</th>
									<th>Purchase Date</th>
									<th>Purchase Price</th>
									<th>Current Price</th>
									<th>Shares</th>
									<th></th>
									<th></th>
								</tr>
						</thead>
						<tbody>
								<tr class="success">
									<td>1</td>
									<td>Good Fund</td>
									<td>01-01-2013</td>
									<td>$100.00</td>
									<td>$120.00</td>
									<td>100</td>
									<td>
										<a href="#" class="btn">Buy More</a>
									</td>
									<td>
										<a href="#" class="btn">SELL</a>
									</td>
								</tr>
								
								<tr >
									<td>2</td>
									<td>Normal Fund</td>
									<td>01-01-2013</td>
									<td>$100.00</td>
									<td>$100.00</td>
									<td>100</td>
									<td>
										<a href="#" class="btn">Buy More</a>
									</td>
									<td>
										<a href="#" class="btn">SELL</a>
									</td>
								</tr>
								
								<tr class="error">
									<td>3</td>
									<td>Bad Fund</td>
									<td>01-01-2013</td>
									<td>$100.00</td>
									<td>$80.00</td>
									<td>100</td>
									<td>
										<a href="#" class="btn">Buy More</a>
									</td>
									<td>
										<a href="#" class="btn">SELL</a>
									</td>
								</tr>
						</tbody>
				</table>
				-->
		</div>
		
		
		
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		