
<!--include header -->
<jsp:include page="header-employee-panel.jsp" />

<!--put your page content here 
		  ============================================
		 -->
		 
		 

 <div class="row-fluid">
		  <h4>Search Customer</h4> <hr>
		 <div>  <!--  This is filter for Transaction History -->
		 		 <form class="form-horizontal">
				 		 <div class="control-group">
							    <label class="control-label" for="scustomer">Name or ID</label>
							    <div class="controls">
							          	<input type="text" id="scustomer" placeholder="name or id">
							    </div>
						  </div>
						  
						  <div class = "control-group">
	   									<div class="controls">
	   											<button type="submit" class="btn">Search</button>	   										
	   									</div>
	   					  </div>
		 		 </form>
		 </div>
		 
		 <hr>
		  <h4>Customer List</h4>  <hr>
		 <div>  <!--  Here list the Transactions -->
		 		<table class="table table-condensed">
	   							<tr class="info">
                                           <td>Customer ID</td>
    										<td>Name</td>
    										<td>Balance</td>
  								</tr>
  				</table>
		 </div>
		 
		 </div>
		 
		 
		 <!--end of page content 
		  ============================================
		 -->

<!--include footer -->
<jsp:include page="footer-panel.jsp" />
		 