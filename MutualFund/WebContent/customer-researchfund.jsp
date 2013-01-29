
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--include header -->
<jsp:include page="header-customer-panel.jsp" />

<!--display path  -->
<div>
	<ul class="breadcrumb">
		<li><a href="view-account.do"> <i class="icon-home"></i>
				Home
		</a> <span class="divider">/</span></li>
		<li class="active">Research Fund</li>
	</ul>
</div>

<script type="text/javascript">
	// Load the Visualization API and the piechart package.
	google.load('visualization', '1.0', {
		'packages' : [ 'corechart' ]
	});

	// Set a callback to run when the Google Visualization API is loaded.
	google.setOnLoadCallback(drawChart);

	var fundList = new Array();
	fundList = '${requestScope.fundGeneralList}';
	for ( var i = 0; i < fundList.length; i++) {
		console.log(fundList[i]);
	}

	var title = [ 'Date' ];
	var data = [ fundList[0].lastTradingDate ];
	for ( var i = 0; i < fundList.length; i++) {
		title[i + 1] = fundList[i].name;
		data[i + 1] = fundList[i].lastTradingPrice;
	}

	// Callback that creates and populates a data table,
	// instantiates the pie chart, passes in the data and
	// draws it.
	function drawChart() {

		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Topping');
		data.addColumn('number', 'Slices');
		data.addRows([ [ 'Mushrooms', 3 ], [ 'Onions', 1 ], [ 'Olives', 1 ],
				[ 'Zucchini', 1 ], [ 'Pepperoni', 2 ] ]);
		//for (var i = 0; i < fundList.length; i++){
		//	data.addRow([title[i], data[i]]);	
		//}

		// Set chart options
		var options = {
			'title' : 'How Much Pizza I Ate Last Night',
			'width' : 400,
			'height' : 300
		};

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.PieChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>

<!--  
<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);

	var fundList = new Array();
	fundList = '${fundGeneralList}';
	console.log(fundList);
	var title = [ 'Date' ];
	var data = [ fundList[0].lastTradingDate ];
	for ( var i = 0; i < fundList.length; i++) {
		title[i + 1] = fundList[i].name;
		data[i + 1] = fundList[i].lastTradingPrice;
	}

	function drawChart() {
		var data = google.visualization.arrayToDataTable([ title, data ]);

		var options = {
			title : 'Fund Performance',
			hAxis : {
				title : 'Fund',
				titleTextStyle : {
					color : 'blue'
				}
			},
			width : 400,
			height : 300
		};

		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>
-->

<!-- For chart -->
<div id="chart_div"></div>

<c:choose>
	<c:when test="${ empty fundPriceList }">
	</c:when>
	<c:otherwise>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Fund Id</th>
					<th>Trading Date</th>
					<th>Trading Price</th>

				</tr>
			</thead>
			<tbody>
				<c:set var="num" value="1" />
				<c:forEach var="fundHistory" items="${fundPriceList}">
					<tr>
						<td>${num}</td>
						<td>${fundHistory.fund_id}</td>
						<td>${fundHistory.price_date}</td>
						<td>${fundHistory.price}</td>
					</tr>
					<c:set var="num" value="${num + 1}" />
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>

<table class="table table-striped">
	<thead>
		<tr>
			<th>#</th>
			<th>Fund Id</th>
			<th>Fund Name</th>
			<th>Fund Symbol</th>
			<th>Current Price</th>
			<th>Last Trading Day</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:set var="num" value="1" />
		<c:forEach var="fundlist" items="${ fundGeneralList}">
			<tr>

				<td>${num}</td>
				<td>${fundlist.fundId}</td>
				<td>${fundlist.name}</td>
				<td>${fundlist.symbol}</td>
				<td>${fundlist.lastTradingPrice}</td>
				<td>${fundlist.lastTradingDate}</td>
				<td><form method="POST" action="customer-research-fund.do">
						<input type="hidden" name="fundId" value="${fundlist.fundId}">
						<button type="submit" class="btn">See Recent Trends</button>
					</form></td>

			</tr>
			<c:set var="num" value="${num + 1}" />
		</c:forEach>
	</tbody>
</table>





<!--end of page content 
		  ============================================
		 -->

<!--include footer -->
<jsp:include page="footer-panel.jsp" />
