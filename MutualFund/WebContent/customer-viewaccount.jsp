
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--include header -->
<jsp:include page="header-customer-panel.jsp" />

<!--display path  -->
<div>
	<ul class="breadcrumb">
		<li><a href="customer-mainpanel.do"> <i class="icon-home"></i>
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
	
	

	<hr>
	<h4>Account Detail Information</h4>
		<table class="table table-striped">
			<thead>
				<tr class="info" style="text-align: right;">
					<th style="text-align: left;">Address Information</th>
					<th style="text-align: left;">Cash Balance</th>
					<!-- <th style="text-align: left;">Last Trading Price</th> -->
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<td style="text-align: left;">${customer.addrLine1}</br>
					${customer.addrLine2}</br> ${customer.city} &nbsp;,${customer.state}
					&nbsp;,&nbsp;Zipcode:${customer.zip} <br/>
				</td>
				<td>${cashBalance}</td>
		</tbody>
		</table>


	<!--end of page content 
		  ============================================
		 -->

	<!--include footer -->
	<jsp:include page="footer-panel.jsp" />
