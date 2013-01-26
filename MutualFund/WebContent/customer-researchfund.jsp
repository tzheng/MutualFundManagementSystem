
<!--include header -->
<jsp:include page="header-customer-panel.jsp" />

<!--display path  -->	   		 		
		<div>
				<ul class="breadcrumb">
						<li><a href="customer-mainpanel.jsp"> <i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">Research Fund</li>  
				</ul>
		</div>	 
		

<!--put your page content here 
		  ============================================
		 -->


<table class="table table-striped">
	<thead>
		<tr>
			<th>#</th>
			<th>Fund Id</th>
			<th>Fund Name</th>
			<th>Closing Price</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1</td>
			<td>10-383</td>
			<td>Fund for Wild Animal</td>
			<td>50,000.00</td>
			<td><button type="submit" class="btn">See Recent Trends</button></td>

		</tr>
	</tbody>
</table>





<!--end of page content 
		  ============================================
		 -->

<!--include footer -->
<jsp:include page="footer-panel.jsp" />
