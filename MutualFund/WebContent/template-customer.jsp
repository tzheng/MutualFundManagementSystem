	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		<div class="span11">
				<h4>Personal Information for: ${customer.firstName} &nbsp;${customer.lastName}</h4>
				<form class="form-horizontal" method="POST" action="view-account.do">
				<table class="table table-condensed" >
					<thead>
								<tr>
									
									<th>Address</th>
									<th>Cash Balance</th>
									<th>Last Trading Day</th>
									<th></th>
									<th></th>
								</tr>
						</thead>
						<tbody>
								
									<td>${customer.addrLine1}</br>${customer.addrLine2}</br>${customer.city}&nbsp;,&nbsp;${customer.state}</br>Zipcode:${customer.zip}</td>
									<td>${customer.cash}</td>
									<td>01-01-2013</td>
									
								
				</table>
				</form>
				
				
				
				
				<hr>
				
				<h4>Fund Porfolio: </h4>
				<table class="table">
						<thead>
								<tr>
									<th>#</th>
									<th>Fund</th>
									<th>Purchase Date</th>
									<th>Shares</th>
									<th>Value</th>
									
									<th></th>
									<th></th>
								</tr>
						</thead>
						<tbody>
								<tr class="success">
									<td>1</td>
									<td>Good Fund</td>
									<td>01-01-2013</td>
									<td>100.00</td>
									<td>$120.00</td>
									
									<td>
										<a href="#" class="btn">Buy +</a>
									</td>
									<td>
										<a href="#" class="btn">Sell-</a>
									</td>
								</tr>
								
								<tr >
									<td>2</td>
									<td>Normal Fund</td>
									<td>01-01-2013</td>
									<td>100.00</td>
									<td>$100.00</td>
									
									<td>
										<a href="#" class="btn">Buy +</a>
									</td>
									<td>
										<a href="#" class="btn">Sell-</a>
									</td>
								</tr>
								
								<tr class="error">
									<td>3</td>
									<td>Bad Fund</td>
									<td>01-01-2013</td>
									<td>100.00</td>
									<td>$80.00</td>
									
									<td>
										<a href="#" class="btn">Buy +</a>
									</td>
									<td>
										<a href="#" class="btn">Sell-</a>
									</td>
								</tr>
						</tbody>
				</table>
		</div>
		
		
		
		
	
		
		
		
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		