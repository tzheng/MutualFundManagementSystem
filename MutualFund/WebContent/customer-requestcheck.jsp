	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		 
			   		 <h4> Request Check </h4> <hr>
			   		 <form class="form-horizontal">
				   		  <div class="control-group">
							    <label class="control-label" for="balance"><b>Available Balance</b></label>
							    <div class="controls controlwords">
							      <p id="balance" >$ "Available balance goes in Here" </p>
							    </div>
						  </div>
						  
						  <div class="control-group">
							    <label class="control-label" for="mailadd"><b> Check Mailing Address:</b></label>
							    <div class="controls controlwords">
							      	<p id="mailadd" >
							      	 "Check Mailing Address: goes in Here"
									<br> Address line1
									<br> Address Line2
									<br> City, State, Zip
									<br> <a href="edit">Edit</a>
									</p>
							    </div>
						  </div>
						  
						  
						  <div class="control-group">
							    <label class="control-label" for="balance"><b>Enter Amount</b></label>
							    <div class="controls">
							      	<div class="input-prepend">
									  	<span class="add-on">$</span>
									  	<input class="span10" id="appendedPrependedInput" type="text" placeholder="0.00">
									</div>
							    </div>
						  </div>
						  
						  <div class="control-group">
							    <div class="controls">
							      <button type="submit" class="btn">Request Check</button>
							    </div>
						  </div>
					  </form>
			   		 

		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		