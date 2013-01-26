	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	<!--display path  -->	   		 		
		<div>
				<ul class="breadcrumb">
						<li><a href="customer-mainpanel.jsp"> <i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">Request Check</li>  
				</ul>
		</div>	 
		
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		 
			   		 <h4> Request Check </h4> <hr>
			   		 <jsp:include page="error-list.jsp"></jsp:include>
			   		 <form class="form-horizontal" method="POST" action="customer-requestcheck.do">
				   		  <div class="control-group">
							    <label class="control-label" for="balance"><b>Available Balance $</b></label>
							    <div class="controls controlwords">
							      <p id="balance" > ${availableBalance} </p>
							    </div>
						  </div>
						  
						  <div class="control-group">
							    <label class="control-label" for="mailadd"><b> Check Mailing Address</b></label>
							    <div class="controls controlwords">
							      	<p id="mailadd" >
									 ${customer.addrLine1}  <br>
									${customer.addrLine2} <br>
									${customer.city}, ${customer.state}, ${customer.zip} <br>
									</p>
							    </div>
						  </div>
						  
						  
						  <div class="control-group">
							    <label class="control-label" for="balance"><b>Enter Amount</b></label>
							    <div class="controls">
							      	<div class="input-prepend">
									  	<span class="add-on">$</span>
									  	<input type="text" class="span10" name="withdrawamount" placeholder="Max: ${availableBalance}" value="${form.withdrawamount}"/>
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
		