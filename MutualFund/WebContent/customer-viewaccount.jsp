
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--include header -->
<jsp:include page="header-customer-panel.jsp" />

<!--display path  -->
<div>
	<ul class="breadcrumb">
		<li><a href="customer-mainpanel.jsp"> <i class="icon-home"></i>
				Home
		</a> <span class="divider">/</span></li>
		<li class="active">View Account</li>
	</ul>
</div>


<!--put your page content here 
		  ============================================
		 -->
<div class="span11">
	<h4>Personal Information For: ${customer.firstName} &nbsp;
		${customer.lastName}</h4>
	
		<table class="table table-striped">
			<thead>
				<tr class="info" style="text-align: right;">

					<th style="text-align: left;">Address</th>
					<th style="text-align: left;">Cash Balance</th>
					<th style="text-align: left;">Last Trading Day</th>


					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<td style="text-align: left;">${customer.addrLine1}</br>
					${customer.addrLine2}</br> ${customer.city} &nbsp;,${customer.state}
					&nbsp;, &nbsp;Zipcode:${customer.zip} <br/>
				</td>
				<td>${customer.cash}</td>
				


	<hr>

	<h4>Fund Portfolio:</h4>
	<div>
		<table class="table table-striped">
			<thead>
				<tr class="info" style="text-align: center;">
					<th>#</th>
					<th>Fund Name</th>
					<th>Shares</th>
					<th>Last Trading Price</th>
					<th>Value</th>

				</tr>
			</thead>
			<tbody>


				<c:forEach var="value" items="${fundvalue}">
						<tr class="info">
						<td>${value.fundId}</td>
						<td>${value.fundName} </td>
						<td>${value.shares} </td>
						<td>${value.lastTradingPrice}</td>
						<td>${value.lastTradingDate} </td> 
						<td>${value.value}</td>
						
					</tr>
				
				</c:forEach>>
			</tbody>
		</table>
	</div>







	<!--end of page content 
		  ============================================
		 -->

	<!--include footer -->
	<jsp:include page="footer-panel.jsp" />