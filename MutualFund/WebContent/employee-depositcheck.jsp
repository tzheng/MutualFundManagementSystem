	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
			   		
		<!--put your page content here 
		  ============================================
		 -->
				          <div>
					           <form class="form-horizontal">
					          <legend>Deposit Check</legend>
						<div class="control-group">
							<label class="control-label" for="inputCustomerid">Customer ID</label>
							<div class="controls">
								<input type="text" id="inputCustomerid" placeholder="Customer ID">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"for="inputAmount">Amount $</label>
							<div class="controls">
								<input type="text" id="inputAmount" placeholder=".00">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"for="inputDate">Deposit Date</label>
							<div class="controls">
								<input type="text" id="inputDate" placeholder="dd/mm/yy">
							</div>
						</div>
							<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn">Deposit</button>
							</div>
							</div>
							</form>
						</div>
						
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />