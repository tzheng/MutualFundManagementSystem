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
							<p>Your last trading date is: <b>${lastTradingDate }</b>
							<h5>During the last trading day: </h5>
							<div class="span12" style="margin-top: 10px;">
								<div class="span8">
									<div class="progress" style="height: 30px;">
											<div class="bar bar-success" style="width: ${pPercent}%; font-size: 1em;"> <b>${processedNumber}</b> processed</div>
											<div class="bar bar-danger"  style="width: ${rPercent}%; font-size: 1em;"> <b>${rejectedNumber}</b> rejected</div>
									</div> 
								</div>
							</div>
							
							<p><b style="color: rgb(83,170,60); font-size: 1.2em; margin-left: 20px;">${processedNumber}</b> of your transactions have been processed. </p>
							<p><b style="color: rgb(209,63,59); font-size: 1.2em; margin-left: 20px;" >${rejectedNumber}</b> of your transactions have been rejected. </p>
							<a href="customerhistory.do" class="btn"  style="margin-top: 20px;">View Transaction History</a>
						</c:otherwise>
				</c:choose>
				
		</div>
		
		
		
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		