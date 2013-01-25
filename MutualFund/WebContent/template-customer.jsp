	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		<div class="span11">
				<h4>Personal Information For: ${customer.firstName} &nbsp; ${customer.lastName} </h4>
				<form class="form-horizontal">
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
								
									<td>${customer.addrLine1}</br>
										${customer.addrLine2}</br>
										${customer.city} &nbsp;,${customer.state} &nbsp;, &nbsp;Zipcode:${customer.zip} <br />
										<a href="#" class="btn" onclick="showEdit();">Edit</a>
										
										<script>
											function showEdit() {
											    var d = document.getElementById( 'ddd' );
											    d.style.display='block';
											}
										</script>
										   
										<div id="ddd" style="display: none;">
										<hr>
										<h4>Edit Address</h4> <hr>
										<form class="form-horizontal" method="POST" action="">
										  <div class="control-group">
										    <label class="control-label" for="addr1">Address Line 1</label>
										    <div class="controls">
										      <input type="text" id="addr1" placeholder="Address Line 1">
										    </div>
										  </div>
										  <div class="control-group">
										    <label class="control-label" for="addr2">Address Line 1</label>
										    <div class="controls">
										      <input type="password" id="addr2" placeholder="Address Line 2">
										    </div>
										  </div>
										  <div class="control-group">
										    <label class="control-label" for="city">City</label>
										    <div class="controls">
										      <input type="password" id="city" placeholder="City">
										    </div>
										  </div>
										  <div class="control-group">
										    <label class="control-label" for="state">State</label>
										    <div class="controls">
										      <input type="password" id="state" placeholder="State">
										    </div>
										  </div>
										      
										  <div class="control-group">
										    <label class="control-label" for="zip">Zip Code</label>
										    <div class="controls">
										      <input type="password" id="zip" placeholder="Zip Code, e.g: 15213">
										    </div>
										  </div>
										  <div class="control-group">
										    <div class="controls">
										      <button type="submit" class="btn">Change</button>
										    </div>
										  </div>
										</form>
										</div>

									</td>
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
								 <c:forEach var="positionlist" items="${ positionList}">
	  							   	<tr>
									<td>1</td>
									<td>{positionlist.name}</td>
									<td>01-01-2013</td>
									<td>${positionlist.shares}</td>
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
		