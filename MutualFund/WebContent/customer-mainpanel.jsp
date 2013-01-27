	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	<!--display path  -->	   		 		
		<div>
				<ul class="breadcrumb">
						<li><a href="customer-mainpanel.jsp"> <i class="icon-home"></i> Home</a></li>
				</ul>
		</div>	 
		
	   	<!--put your page content here 
		  ============================================
		 -->
		
		<div class="span11">
				<h4>Your current account balance is: $10,000.00 </h4>
				<p>Maybe some more general Information  </p>
				
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
		</div>
		
		
		
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		