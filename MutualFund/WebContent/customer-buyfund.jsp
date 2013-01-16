	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
	 				<h4> Buy Fund </h4> <hr>
			   		 <form class="form-horizontal">
				   		  <div class="control-group">
							    <label class="control-label" for="balance"><b>Name of the Fund</b></label>
							    <div class="controls controlwords">
							       <div class="row-fluid">
							      			<div class="span4">
							      					<input type="text" value="Fund 1" disabled> 
							      			</div>
							      			
							      			<div class="span6">
									      		 <select multiple="multiple">
													  <option>Fund 1</option>
													  <option>Fund 2</option>
													  <option>Fund 3</option>
													  <option>Fund 4</option>
													  <option>Fund 5</option>
													  <option>Fund 6</option>
													  <option>Fund 7</option>
													  <option>Fund 8</option>
												</select>
							      			</div>
							      	</div>

							    </div>
						  </div>
						  
						  <div class="control-group">
							    <label class="control-label" for="mailadd"><b> Current Price:</b></label>
							    <div class="controls controlwords">
							      	<p id="mailadd" >
							      	<i> "Current price goes in Here"</i>
									</p>
							    </div>
						  </div>
						  
						    <div class="control-group">
							    <label class="control-label" for="mailadd"><b> Fund Information: </b></label>
							    <div class="controls controlwords">
							      	<p id="FundInfo" >
							      	<i> "Fund information goes in here"</i>
									</p>
							    </div>
						  </div>
						  
						  
						  <div class="control-group">
							    <label class="control-label" for="balance"><b>Enter Amount</b></label>
							    <div class="controls">
									  	<input class="span3" id="appendedPrependedInput" type="text" placeholder="Max Amount: 100">
							    </div>
						  </div>
						  
						  <div class="control-group">
							    <label class="control-label" for="totalPrice"><b>Total Price</b></label>
							    <div class="controls">
									  	<input class="span3" id="totalPrice" type="text" value="Total Price" disabled>
							    </div>
						  </div>
						  <div class="control-group">
							    <div class="controls">
							      <button type="submit" class="btn">Buy Fund</button>
							    </div>
						  </div>
					  </form>

		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		