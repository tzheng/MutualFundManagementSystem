	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
				  
				             	
				             	<!-- title -->
				             	<h4>Account Information</h4> <hr/>
				             	<form class="form-horizontal">
				             			<!-- customer name -->
				             			<div class="control-group">
	   										<label class="control-label" for="inputCustomerName">Name</label>
	   										<div class="controls">
	   											<div class="row-fluid">
									      			<div class="span4">
									      					<input type="text" id="inputCustomerName" placeholder="${customer.firstName} ${customer.lastName}" > 
									      			</div>
									      			<div class="span6">
											      		 <button class="btn">Edit</button>
									      			</div>
							      				</div>   										
	   										</div>
	   									</div>
	   								
				             			<!-- customer address1 -->
				             			<div class="control-group">
	   										<label class="control-label" for="inputAddress1">Address Line1</label>

	   										<div class="controls">
	   											<div class="row-fluid">
									      			<div class="span4">
									      					<input type="text" id="inputAddress1" placeholder="${customer.addrLine1}" > 
									      			</div>
									      			<div class="span6">
											      		 <button class="btn">Edit</button>
									      			</div>
							      				</div>   										
	   										</div>
	   									</div>		
				             		
				             		
				             			<!-- customer address2 -->
				             			<div class="control-group">
	   										<label class="control-label" for="inputAddress2">Address Line2</label>

	   										<div class="controls">
	   											<div class="row-fluid">
									      			<div class="span4">
									      					<input type="text" id="inputAddress2" placeholder="${customer.addrLine2}" > 
									      			</div>
									      			<div class="span6">
											      		 <button class="btn">Edit</button>
									      			</div>
							      				</div>   										
	   										</div>
	   									</div>				
				             		
				             			<!-- last trading date -->
				             			<div class="control-group" >
	   										<label class="control-label" for="inputLastTradeDate">Last Trade Date</label>
	   										<div class="controls">
	   										<input type="text" id="inputLastTradeDate" placeholder="${customer.lastTradeDate}" disabled>
	   										</div>
	   									</div>
				             		
				             			<!-- balance -->
				             			<div class="control-group" >
	   										<label class="control-label" for="inputBalance">Balance</label>
	   										<div class="controls">
	   										<input type="text" id="inputBalance" placeholder="100" disabled>
	   										</div>
	   									</div>
	   								
				             			<!-- fund -->
				             			<div class="control-group" >
	   										<label class="control-label" for="fund-name">Fund Name</label>
	   										<div class="controls">
	   										<input type="hidden" value=""  name="fund">
	   										<select id="fund-name" name="fund_name">
	   											<option value="">select</option>
	   										</select>
	   										</div>
	   									</div>
				             		
				             			<!-- number of shares -->
				             			<div class="control-group" >
	   										<label class="control-label" for="inputShares">Share Number</label>
	   										<div class="controls">
	   										<input type="text" id="inputShares" placeholder="ShareNumber" disabled>
	   										</div>
	   									</div>
				             		<br>
				             		<div class = "control-group">
	   									<div class="controls">
	   											<button type="submit" class="btn">Save</button>	   										
	   									</div>
	   									<div class="controls">
	   											<button type="submit" class="btn">Cancel</button>	   										
	   									</div>				
				             		</div>		
				             	<br>
				             	</form>
				             	
				            
				          
				          
				          
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
