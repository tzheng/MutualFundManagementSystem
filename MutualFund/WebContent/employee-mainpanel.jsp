	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
			   		
		<!--put your page content here 
		  ============================================
		 -->
		
		<div>
				<ul class="breadcrumb">
						<li><a href="employee-mainpanel.jsp"><i class="icon-home"></i> Home</a> </li>
				</ul>
		</div>
		
		<div class="span11">
				
				<!-- 
				<h4> There are 5 check request. <a href="#">Deposit Check</a> </h4>
				<p>Maybe some more general Information  </p>
				 
				<hr>
				-->
				
				<div id="panel-nav" class="row-fluid">
						<ul id="panel-nav-ul">
								
								<a href="createfund.do">
								<li class="span3">
										<div class="menu-title">
											<span class="ca-icon">R</span> 
											<div style="margin-top: 20px">Create Fund </div>
											
										</div>
								</li>
								</a>
								
								<a href="create-account.do">
									<li class="span3">
										<div class="menu-title">
											<span class="ca-icon">U</span> 
											<div style="margin-top: 20px">Create Customer</div>
										</div>
									</li>
								</a>
								
								<a href="employeehistory.do">
									<li class="span3">
										<div class="menu-title">
										<span class="ca-icon">p</span> 
											<div style="margin-top: 20px">Transaction History</div>
										</div>
									</li>
								</a>
								
								<a href="employee-transitionday.do">
									<li class="span3">
										<div class="menu-title">
										<span class="ca-icon">J</span> 
											<div style="margin-top: 20px">Transition Day</div>
										</div>
									</li>
								</a>
						</ul>
				</div>
		</div>
		
		
		
		
		
		
		
		
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		
