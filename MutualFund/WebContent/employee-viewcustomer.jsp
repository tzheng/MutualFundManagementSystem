<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--include header -->
<jsp:include page="header-employee-panel.jsp" />


		<div>
				<ul class="breadcrumb">
						<li><a href="employee-mainpanel.jsp"><i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">View Customer Account</li>
				</ul>
		</div>

<!--put your page content here 
		  ============================================
		 -->
		 
		 

 <div class="row-fluid">
		  <h4>Search Customer</h4> <hr>
		 <div>  <!--  This is filter for Transaction History -->
		 		 <jsp:include page="error-list.jsp"></jsp:include>
		 		 <form class="form-horizontal" method="post" action="employeeviewaccount.do">
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
		
		 <c:choose>
		 	<c:when test="${empty customer}">
		 				 <h4>Customer List</h4> <hr>
						 <div>
						 	<c:choose>
						 		<c:when test="${empty customerlist }">
						 			<h5>There is no customer currently</h5>
						 		</c:when>
						 		<c:otherwise>
						 			<table class="table table-condensed">
						 				<thead>
					   							<tr class="info">
				    										<th>Username</th>
				    										<th>Name</th>
				    										<th></th>
				  								</tr>
				  						</thead>
				  						<tbody>
				  								<c:forEach var="customer" items="${customerlist}" >
				  								<tr>
				  									<td>${customer.userName }</td>
				  									<td>${customer.firstName }&nbsp;${customer.lastName}</td>
				  									<td><a href="employeeviewaccount.do?username=${customer.userName }" class="btn">View Detail</a></td>
				  								</tr>
				  								</c:forEach>
				  						</tbody>
				  					</table>
						 		</c:otherwise>
						 	</c:choose>
						 </div>
		 	</c:when>
		 		
		 	<c:otherwise>
		 		<a href="employeeviewaccount.do" class="btn btn-primary">See full customer list</a>
		 		<hr>
		 		<h4>Customer Information</h4> 
		 <div>  <!--  Here list the Transactions -->
		 		<table class="table table-condensed">
		 				<thead>
	   							<tr class="info">
    										<th>Name</th>
    										<th>Address Information</th>
    										<th>Cash Balance</th>
    										<th>Last Trading Date</th>
  								</tr>
  						</thead>
  						<tbody>
  								<c:set var="value" value="${fundvalue}" ></c:set>
  								<c:set var="customer" value="${customer }"></c:set>
  								<tr>
  									<td>${customer.firstName }&nbsp;${customer.lastName}</td>
  									<td>
										${customer.addrLine1 } <br />
										${customer.addrLine2 } <br />
										${customer.city }, ${customer.state }, ${customer.zip }
									</td>
  									<td>${cash }</td>
  									<td>${lastTradingDate}</td>
  								</tr>
  						</tbody>
  				</table>
  								
		 </div>
		 
		 <hr>
		<h4>Fund Portfolio</h4>
		<table class="table table-striped">
			<thead>
				<tr class="info" style="text-align: center;">
					<th>#</th>
					<th>Fund Name</th>
					<th>Shares</th>
					<th>Last Trading Price</th>
					<th>Last Trading Date</th>
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
				
				</c:forEach>
			</tbody>
		</table>
		 	</c:otherwise>
		 </c:choose>

		 

</div>
		 
		 
		 <!--end of page content 
		  ============================================
		 -->

<!--include footer -->
<jsp:include page="footer-panel.jsp" />
		 