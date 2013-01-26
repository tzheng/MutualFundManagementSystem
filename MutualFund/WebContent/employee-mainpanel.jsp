	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
			   		
		<!--put your page content here 
		  ============================================
		 -->
		
		<div>
				<ul class="breadcrumb">
						<li><a href="employee-mainpanel.jsp"><i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
				</ul>
		</div>
		
		<div class="span11">
				<h4> There are 5 check request. <a href="#">Deposit Check</a> </h4>
				<p>Maybe some more general Information  </p>
				
				<hr>
				
				<div id="panel-nav" class="row-fluid">
						<ul id="panel-nav-ul">
								<a href="employee-createfund.jsp">
									<li class="span3">
									icon here
										<div class="menu-title">
											Create Fund
										</div>
									</li>
								</a>
								
								<a href="create-account.do">
									<li class="span3">
										icon here
										<div class="menu-title">
											Create Customer Account
										</div>
									</li>
								</a>
								
								<a href="employeehistory.do">
									<li class="span3">
									icon here
										<div class="menu-title">
											View Transaction History
										</div>
									</li>
								</a>
								
								<a href="employee-transactionday.jsp">
									<li class="span3">
									icon here
										<div class="menu-title">
											Transition Day
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
		
