	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
			   		
		<!--put your page content here 
		  ============================================
		 -->
		
				          <div>
					              <form class="form-horizontal" method="post" action="createfund.do">
						            		<legend>Create Fund</legend>
				   						<div class="control-group">
											<label class="control-label"  for="inputFundname">Fund Name</label>
											<div class="controls">
												<input type="text"  name="fundName" id="inputFundid" placeholder="Fund Name">
											</div>
										</div>
									      <div class="control-group">
											<label class="control-label"  for="inputTicker">Ticker Symbol</label>
											<div class="controls">
												<input type="text" name="symbol" id="inputTickerid" placeholder="one to five characters">
											</div>
										</div>  
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">Create</button>
											</div>
										</div>    	
					            </form>
	   						</div>
	   						
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />