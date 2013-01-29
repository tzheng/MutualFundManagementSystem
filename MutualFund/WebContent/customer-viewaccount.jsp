
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--include header -->
<jsp:include page="header-customer-panel.jsp" />

<!--display path  -->
<div>
	<ul class="breadcrumb">
		<li><a href="view-account.do"> <i class="icon-home"></i>
				Home
		</a> <span class="divider">/</span></li>
		<li class="active">View Account</li>
	</ul>
</div>


<!--put your page content here 
		  ============================================
		 -->

	<h4>Personal Information For: ${customer.firstName}&nbsp;${customer.lastName}</h4>
		<hr>
				<div class="row-fluid">
				<div class="span8" style="border-right: 1px solid #F5F5F5;">
				<p>Your current account balance is: <b>$  ${currentBalance} </b> </p>
				
				<c:choose>
						<c:when test ="${ empty lastTradingDate }">
							<h5>You don't have any transactions currently. </h5>
						</c:when>
						<c:otherwise>
							<p>Your last trading date is: <b>${lastTradingDate }</b>
							<h5>During the last trading day: </h5>
							<div class="span12" style="margin-top: 10px;">
								<div style="width: 80%">
									<div class="progress" style="height: 30px;">
											<div class="bar bar-success" style="width: ${pPercent}%; font-size: 1em;"> <b>${processedNumber}</b> processed</div>
											<div class="bar bar-danger"  style="width: ${rPercent}%; font-size: 1em;"> <b>${rejectedNumber}</b> rejected</div>
									</div> 
								</div>
							</div>
							
							<p><b style="color: rgb(83,170,60); font-size: 1.2em; margin-left: 20px;">${processedNumber}</b> of your transactions have been processed. </p>
							<p><b style="color: rgb(209,63,59); font-size: 1.2em; margin-left: 20px;" >${rejectedNumber}</b> of your transactions have been rejected. </p>
							<a href="customerhistory.do" class="btn"  style="margin-top: 10px;">View Transaction History</a>
						</c:otherwise>
				</c:choose>
				</div>
				
				<div class="span4">
				<h4>Address Information</h4>
						<p>
							${customer.addrLine1}</br>
							${customer.addrLine2}</br> 
							${customer.city}, &nbsp;${customer.state},&nbsp;${customer.zip} <br/>
						</p>
				</div>
		</div>
		
		<hr>
		
		<h4>Your Fund Portfolio</h4>
		<table class="table table-striped">
			<thead>
				<tr class="info" style="text-align: center;">
					<th>Fund Name</th>
					<th style="text-align: right;">Shares</th>
					<th style="text-align: right;">Last Trading Price</th>
					<th style="text-align: right;">Last Trading Date</th>
					<th style="text-align: right;">Value</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="value" items="${fundvalue}">
					<tr>
						<td>${value.fundName} </td>
						<td style="text-align: right;">${value.shares} </td>
						<td style="text-align: right;">${value.lastTradingPrice}</td>
						<td style="text-align: right;">${value.lastTradingDate} </td> 
						<td style="text-align: right;">${value.value}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="customer-buyfund.do" class="btn"  style="margin-top: 10px; width: 100px;">Buy Fund</a>
		<a href="customer-sellfund.do" class="btn"  style="margin-top: 10px; width: 100px;">Sell Fund</a>

	<hr>
	


	<!--end of page content 
		  ============================================
		 -->

	<!--include footer -->
	<jsp:include page="footer-panel.jsp" />
