a	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		
				          <div>
				             <div class = "row-fluid">
				             	<div class = "span9">
				             	<!-- title -->
				             	<h3>Account Information</h3> <br>
				             		<div class = "row-fluid">
				             			<!-- customer name -->
				             			<div class="span1" >
	   										<label class="customer-name">Name</label>
	   									</div>
	   									<div class="span3 offset1">
	   										<input type="text" id="inputName" placeholder="Name">
	   									</div>
	   									<div class="span2 offset2">
	   											<button type="submit" class="btn">Edit</button>	   										
	   									</div>		
				             		</div>
	   								
	   								<div class = "row-fluid">
				             			<!-- customer address -->
				             			<div class="span1" >
	   										<label class="customer-address1">AddressLine1</label>
	   									</div>
	   									<div class="span3 offset2">
	   										<input type="text" id="inputAddress1" placeholder="Address Line 1">
	   									</div>
	   									<div class="span2 offset2">
	   											<button type="submit" class="btn">Edit</button>	   										
	   									</div>		
				             		</div>
				             		
				             		<div class = "row-fluid">
				             			<!-- customer address -->
				             			<div class="span1" >
	   										<label class="customer-address2">AddressLine2</label>
	   									</div>
	   									<div class="span3 offset2">
	   										<input type="text" id="inputAddress2" placeholder="Address Line 2">
	   									</div>
	   									<div class="span2 offset2">
	   											<button type="submit" class="btn">Edit</button>	   										
	   									</div>		
				             		</div>
				             		
				             		<div class = "row-fluid">
				             			<!-- last trading date -->
				             			<div class="span1" >
	   										<label class="customer-lastTradeDate">LastTradeDate</label>
	   									</div>
	   									<div class="span3 offset2">
	   										<input type="text" id="inputLastTradeDate" placeholder="Last Trade Date">
	   									</div>
				             		</div>
				             		
				             		<div class = "row-fluid">
				             			<!-- balance -->
				             			<div class="span1" >
	   										<label class="customer-balance">Balance</label>
	   									</div>
	   									<div class="span3 offset2">
	   										<input type="text" id="inputBalance" placeholder="Balance">
	   									</div>
				             		</div>
	   								
	   								<div class = "row-fluid">
				             			<!-- fund -->
				             			<div class="span1" >
	   										<label class="customer-fund">FundName</label>
	   									</div>
	   									<div class="span1 offset2">
	   										<input type="hidden" value=" " name="fund">
	   										<select id="fund-name" name="fund_name">
	   											<option value="">select</option>
	   										</select>
	   									</div>
				             		</div>
				             		
				             		<div class = "row-fluid">
				             			<!-- number of shares -->
				             			<div class="span1" >
	   										<label class="customer-shares">ShareNumber</label>
	   									</div>
	   									<div class="span3 offset2">
	   										<input type="text" id="inputShares" placeholder="ShareNumber">
	   									</div>
				             		</div>
				             		<br>
				             		<div class = "row-fluid">
	   									<div class="span2 offset3">
	   											<button type="submit" class="btn">Save</button>	   										
	   									</div>
	   									<div class="span2">
	   											<button type="submit" class="btn">Cancel</button>	   										
	   									</div>				
				             		</div>		
				             	<br>
				             	</div>
				             </div>
				          </div>
				          
				          
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />