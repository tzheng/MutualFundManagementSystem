	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		<h4> Sell Fund </h4> <hr>
			   		 <form class="form-horizontal">
				   		  <div class="control-group">
							    <label class="control-label" for="balance"><b>Choose a Fund:</b></label>
							    <div class="controls controlwords">
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
						  
						  <div class="control-group">
							    <label class="control-label" for="mailadd"><b> Current Price:</b></label>
							    <div class="controls controlwords">
							      	<p id="mailadd" >
							      	<i> "Current price goes in Here"</i>
									</p>
							    </div>
						  </div>
						  
						    <div class="control-group">
							    <label class="control-label" for="mailadd"><b> Number of Shares Owned:  </b></label>
							    <div class="controls controlwords">
							      	<p id="FundInfo" >
							      	<i> "Number of shares owned"</i>
									</p>
							    </div>
						  </div>
						  
						  
						  <div class="control-group">
							    <label class="control-label" for="balance"><b>Shares to Sell</b></label>
							    <div class="controls">
									  	<input class="span3" id="appendedPrependedInput" type="text" placeholder="shares">
							    </div>
						  </div>
						  
						  <div class="control-group">
							    <div class="controls">
							      <button type="submit" class="btn">Sell Fund</button>
							    </div>
						  </div>
					  </form>
			   		 		
			<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		