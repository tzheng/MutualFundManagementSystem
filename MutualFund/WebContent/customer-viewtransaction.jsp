	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		 <div class="row-fluid">
		  <h4>Transaction History Filter</h4> <hr>
		 <div>  <!--  This is filter for Transaction History -->
		 		 <form class="form-horizontal">
				 		 <div class="control-group">
							    <label class="control-label" for="dateRange">Date Range</label>
							    <div class="controls">
							    		<div class="row-fluid">
							          		<div class="span5">From: <input type="text" id="dateRange" placeholder="MM/DD/YYYY"></div>
							          		 <div class="span5">To: <input type="text" id="dateRange" placeholder="MM/DD/YYYY"> </div>
							    		</div>
							    </div>
						  </div>
						  
						  <div class="control-group">
							    <label class="control-label" for="dateRange">Amount Range</label>
							    <div class="controls">
							    		<div class="row-fluid">
							          		<div class="span5">From: <input type="text" id="dateRange" placeholder="amount"></div>
							          		 <div class="span5">To: <input type="text" id="dateRange" placeholder="amount"> </div>
							    		</div>
							    </div>
						  </div>
						  
						  
						  <div class="control-group" >
						  		<div class="span-group">
											    <label>Cash Operation</label>
											      	<select class="select-mini">
													  <option>Check</option>
													  <option>Deposit</option>
													</select>
						  		</div>
						  		
						  		<div class="span-group">
											    <label>Status</label>
											      	<select class="select-mini">
													  <option>Processed</option>
													  <option>Pending</option>
													</select>
						  		</div>
						  		
						  		<div class="span-group">
										    <label>Fund Operation</label>
										      	<select class="select-mini">
												  <option>Buy</option>
												  <option>Sell</option>
												</select>
						  		</div>
						  </div>
						  
			
					 	 <div class="control-group" style="margin-top: -20px;">
							    <div class="controls">
							      <button type="submit" class="btn">Search</button>
							    </div>
						  </div> 	
		 		 </form>
		 </div>
		 
		 <hr>
		  <h4>Transaction History</h4>  <hr>
		 <div>  <!--  Here list the Transactions -->
		 </div>
		 
		 </div>
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />