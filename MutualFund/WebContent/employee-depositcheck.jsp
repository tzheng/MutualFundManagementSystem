	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
			   		
		<!--put your page content here 
		  ============================================
		 -->
				          <div>
					           <form class="form-horizontal" method="POST" action="employee-depositcheck.do">
					          <legend>Deposit Check</legend> 
						<jsp:include page="error-list.jsp"></jsp:include>
						<div class="control-group">
							<label class="control-label" for="inputCustomerid">Username</label>
							<div class="controls">
								<input type="text" name="userName" value="${form.userName}">							</div>
						</div>
						<div class="control-group">
							<label class="control-label"for="inputAmount">Amount $</label>
							<div class="controls">
									<div class="input-prepend">
									  	<span class="add-on">$</span>
									  	<input class="span10" id="appendedPrependedInput" type="text" placeholder="0.00" name="amount" >
									</div>
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