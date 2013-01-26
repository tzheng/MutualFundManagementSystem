	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	<!--display path  -->	   		 		
		<div>
				<ul class="breadcrumb">
						<li><a href="customer-mainpanel.jsp"> <i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">View Account</li>  
				</ul>
		</div>	 
		
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		<div class="span11">
				<h4>Personal Information For: ${customer.firstName} &nbsp; ${customer.lastName} </h4>
				<form class="form-horizontal" method= "POST" action= "view-account.do">
				<table class="table table-striped" >
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
									<c:forEach var="value" items="${fundValue}">
									<c:choose>
  							   					<c:when test = "${empty value.lastTradingDate }">
  							   							<td style="text-align: center">-</td>
  							   					</c:when>
  							   					<c:otherwise>
  							   						<td style="text-align: right; padding-right: 50px;">${value.lastTradingDate} </td>
  							   					</c:otherwise>
  							   			</c:choose>
  							   			</c:forEach> 
								
				</table>
				</form>
				
				
				
				
				<hr>
				
				<h4>Fund Portfolio: </h4>
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
										
										
										<c:forEach var="value" items="${fundValue}">
							
										<td>#</td>
										<c:choose>
  							   					<c:when test = "${empty value.fundName }">
  							   							<td style="text-align: center">-</td>
  							   					</c:when>
  							   					<c:otherwise>
  							   						<td style="text-align: right; padding-right: 50px;">${value.fundName} </td>
  							   					</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<c:set var="shares" value="${value.shares}"></c:set>
  							   			<c:set var="zero" value = "0.000"></c:set>
  							   			<c:choose>
  							   				<c:when test= "${shares eq zero }">
  							   						<td style="text-align: center">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 50px;">${value.shares} </td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			
  							   			
  							   			<c:set var="price" value="${value.lastTradingPrice}"></c:set>
  							   			<c:set var="zero1" value = "0.00"></c:set> 
  							   			<c:choose>
  							   				<c:when test= "${price eq zero1 }">
  							   						<td style="text-align: center">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 50px;">${value.lastTradingPrice} </td>
  							   				</c:otherwise>
  							   			</c:choose>
	  							   	
								</c:forEach>
						</tbody>
				</table>
		</div>
		
		
		
		
	
		
		
		
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		
