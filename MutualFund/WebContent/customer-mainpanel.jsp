	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		
		<div class="span11">
				<h4>Your current account balance is: $10000.00 </h4>
				<p>Maybe some more general Information  </p>
				
				<hr>
				
				<h4>Your funds: </h4>
				<table class="table">
						<thead>
								<tr>
									<th>#</th>
									<th>Fund</th>
									<th>Purchase Time</th>
									<th>Purchase Price</th>
									<th>Current Price</th>
									<th>Shares</th>
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
									<td><div class="btn-group">
												  <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
												    Action
												    <span class="caret"></span>
												  </a>
												  <ul class="dropdown-menu">
												    	<li><a href="#">Buy More</a></li>
												    	<li><a href="#">Sell</a></li>
												  </ul>
											</div>
									</td>
								</tr>
								
								<tr >
									<td>2</td>
									<td>Normal Fund</td>
									<td>01-01-2013</td>
									<td>$100.00</td>
									<td>$100.00</td>
									<td>100</td>
									<td><div class="btn-group">
												  <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
												    Action
												    <span class="caret"></span>
												  </a>
												  <ul class="dropdown-menu">
												    	<li><a href="#">Buy More</a></li>
												    	<li><a href="#">Sell</a></li>
												  </ul>
											</div>
									</td>
								</tr>
								
								<tr class="error">
									<td>3</td>
									<td>Bad Fund</td>
									<td>01-01-2013</td>
									<td>$100.00</td>
									<td>$80.00</td>
									<td>100</td>
									<td><div class="btn-group">
												  <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
												    Action
												    <span class="caret"></span>
												  </a>
												  <ul class="dropdown-menu">
												    	<li><a href="#">Buy More</a></li>
												    	<li><a href="#">Sell</a></li>
												  </ul>
											</div>
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
		