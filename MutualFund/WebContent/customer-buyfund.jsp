	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	<script type="text/javascript">
							function changefund(obj){
								txt = obj.options[obj.selectedIndex].text;
								
								document.getElementById('fundname').value=txt;
							}
		</script>	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
	 				<h4> Buy Fund </h4> <hr>
	 				<jsp:include page="error-list.jsp"></jsp:include>
			   		 <form class="form-horizontal">
				   		  <div class="control-group">
							    <label class="control-label" for="balance"><b>Name of the Fund</b></label>
							    <div class="controls controlwords">
							       <div class="row-fluid">
							      			<div class="span4">
							      					<input id="fundname"  name="fundName"  type="text" placeholder="Fund Name" > 
							      			</div>
							      			
							      			<!-- 
							      			<div class="span6">
									      		 <select multiple="multiple" onchange="changfund(this)">
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
							      			-->
							      	</div>

							    </div>
						  </div>
						  
						  <div class="control-group">
							    <label class="control-label" for="mailadd"><b> Last Trading Price:</b></label>
							    <div class="controls controlwords">
							      	<p id="mailadd" >
							      	<i> "Current price goes in Here"</i>
									</p>
							    </div>
						  </div>
						  
						    <div class="control-group">
							    <label class="control-label" for="mailadd"><b> Your Account Balance: </b></label>
							    <div class="controls controlwords">
							      	<p id="FundInfo" >
							      	<i> "Fund information goes in here"</i>
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
							      <button type="submit" class="btn">Buy Fund</button>
							    </div>
						  </div>
					  </form>
		<hr>
		<jsp:include page="customer-fundlist.jsp" />
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		