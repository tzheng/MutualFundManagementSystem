
<!--include header -->
<jsp:include page="header-customer-panel.jsp" />


<!--put your page content here 
		  ============================================
		 -->
<div class="span11">
	<h4>Personal Information:</h4>
	<table class="table table-condensed">
		<thead>
			<tr>

				<th>Qi Wang</th>
				<th>Cash Balance</th>
				<th>Last Trading Day</th>


				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>

			<td>Address: 5000Forbes Avenues</br>Carnegie Mellon University</br>Pittsburgh,PA15213
			</td>
			<td>1000,000.00</td>
			<td>01-01-2013</td>
	</table>




	<hr>

	<h4>Fund Porfolio:</h4>
	<table class="table">
		<thead>
			<tr>
				<th>#</th>
				<th>Fund</th>
				<th>Purchase Date</th>
				<th>Shares</th>
				<th>Value</th>

				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<tr class="success">
				<td>1</td>
				<td>Good Fund</td>
				<td>01-01-2013</td>
				<td>100.00</td>
				<td>$120.00</td>

				<td><a href="#" class="btn">Buy +</a></td>
				<td><a href="#" class="btn">Sell-</a></td>
			</tr>

			<tr>
				<td>2</td>
				<td>Normal Fund</td>
				<td>01-01-2013</td>
				<td>100.00</td>
				<td>$100.00</td>

				<td><a href="#" class="btn">Buy +</a></td>
				<td><a href="#" class="btn">Sell-</a></td>
			</tr>

			<tr class="error">
				<td>3</td>
				<td>Bad Fund</td>
				<td>01-01-2013</td>
				<td>100.00</td>
				<td>$80.00</td>

				<td><a href="#" class="btn">Buy +</a></td>
				<td><a href="#" class="btn">Sell-</a></td>
			</tr>
		</tbody>
	</table>
</div>







<!--end of page content 
		  ============================================
		 -->

<!--include footer -->
<jsp:include page="footer-panel.jsp" />
