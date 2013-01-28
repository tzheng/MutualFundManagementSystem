
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--include header -->
<jsp:include page="header-customer-panel.jsp" />

<!--display path  -->
<div>
	<ul class="breadcrumb">
		<li><a href="customer-mainpanel.do"> <i class="icon-home"></i>
				Home
		</a> <span class="divider">/</span></li>
		<li class="active">Research Fund</li>
	</ul>
</div>

<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	
	var fundList = (Array) '${fundGeneralList}';
	print(fundList);
	var title = ['Date'];
	var data = [fundList[0].lastTradingDate];
	for (var i = 0; i < fundList.length; i++){
		title[i+1] = fundList[i].name;
		data[i+1] = fundList[i].lastTradingPrice;
	}
	
	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				title, data]);

		var options = {
			title : 'Fund Performance',
			hAxis : {
				title : 'Fund',
				titleTextStyle : {
					color : 'blue'
				}
			},
			width: 400,
			height: 300
		};

		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
</script>

<!-- For chart -->
<div id="chart_div"></div>

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
				<td><button type="submit" class="btn">See Recent
						Trends</button></td>

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
