
<!--include header -->
<jsp:include page="header-employee-panel.jsp" />

<!--put your page content here 
		  ============================================
		 -->
<div class="row-fluid">
	<!-- customer name -->
	<div class="span1">
		<label>Transaction Day</label>
	</div>
	<div class="span3 offset1">
		<input type="text" placeholder="2013-XX-XX">
	</div>
	<div class="span2 offset2">
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

