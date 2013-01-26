
<!--include header -->
<jsp:include page="header-employee-panel.jsp" />

		<div>
				<ul class="breadcrumb">
						<li><a href="employee-mainpanel.jsp"><i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">Transition Day</li>
				</ul>
		</div>

<!--put your page content here 
		  ============================================
		 -->
<div class="row-fluid">
	<!-- customer name -->
	<div class="span2">
		<label>Transition Day</label>
	</div>
	<div class="span2">
		<input type="text"  class="dp span10" value="02-16-2012" >
	</div>
	<div class="span2 ">
		<button type="submit" class="btn">Update</button>
	</div>
</div>

<table class="table table-striped">
	<thead>
		<tr>
			<th>#</th>
			<th>Transaction Id</th>
			<th>Closing Price</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1</td>
			<td>10-383</td>
			<td>480,000.00</td>
			<td><button type="submit" class="btn">Save</button></td>
			
		</tr>
	</tbody>
</table>






<!--end of page content 
		  ============================================
		 -->

<!--include footer -->
<jsp:include page="footer-panel.jsp" />

