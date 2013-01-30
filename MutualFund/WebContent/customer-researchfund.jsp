
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="databean.FundGeneralInfoBean" %>
<%@ page import="databean.FundPriceHistoryBean" %>

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
	google.setOnLoadCallback(drawColumnChart);
	google.setOnLoadCallback(drawLineChart);

	var fundList = new Array();
	var title = new Array();
	var dataList = new Array();
	
	<% 
	FundGeneralInfoBean[] fundGeneralList = (FundGeneralInfoBean[]) request.getAttribute("fundGeneralList");
	for (int i=0; i< fundGeneralList.length; i++) { 
	%>
	
	fundList[<%= i %>] = "<%= fundGeneralList[i] %>"; 
	title[<%= i %>] = "<%= fundGeneralList[i].getName() %>";
	dataList[<%= i %>] = "<%= fundGeneralList[i].getLastTradingPrice() %>";

	<%  } %>	
	
	<% 
	FundPriceHistoryBean[] fundPriceHistoryList = (FundPriceHistoryBean[]) request.getAttribute("fundPriceList");
	if (fundPriceHistoryList != null){
	%>
		var title_lineChart = new Array();
		var dataList_lineChart = new Array();	
	<%
		for (int i=0; i< fundPriceHistoryList.length; i++) { 
	%>
	
		title_lineChart[<%= i %>] = "<%= fundPriceHistoryList[i].getPrice_date() %>";
		dataList_lineChart[<%= i %>] = "<%= fundPriceHistoryList[i].getPrice() %>";

	<%	}
	} %>	
	

	// Callback that creates and populates a data table,
	// instantiates the column chart, passes in the data and
	// draws it.
	function drawColumnChart() {

		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Fund Name');
		data.addColumn('number', 'Current Price');
		
		for (var i = 0; i < title.length; i++){
			if(dataList[i] != "null"){
				data.addRow([title[i], parseFloat(dataList[i])]);	
			}
		}
		//console.log(data.getNumberOfRows());

		// Set chart options
		var options = {
			'title' : 'Current Fund Performance',
			'width' : 640,
			'height' : 480
		};

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.ColumnChart(document
				.getElementById('chart_div'));
		chart.draw(data, options);
	}
	
	function drawLineChart() {

		// Create the data table.
		var data = new google.visualization.DataTable();
		data.addColumn('date', 'Date');
		data.addColumn('number', 'Price');
		for (var i = 0; i < title_lineChart.length; i++){
			if(dataList_lineChart[i] != "null"){
				var tmp = title_lineChart[i].split("-");
				
				var year = parseInt(tmp[0]);
				var month = parseInt(tmp[1]);
				var day = parseInt(tmp[2]);
				
				data.addRow([new Date(year,month,day), parseFloat(dataList_lineChart[i])]);	
				console.log(new Date(year,month,day));
			}
		}
		

		// Set chart options
		var options = {
			'title' : 'Performance History',
			'width' : 800,
			'height' : 600
		};

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.LineChart(document
				.getElementById('linechart_div'));
		chart.draw(data, options);
	}
</script>

<c:choose>
	<c:when test="${ empty fundPriceList }">
	</c:when>
	<c:otherwise>
	<a href="#chart_div" class="btn"><i class= "icon-th-list" ></i>View Fund List</a>
	<h4>Performance of ${curFund}</h4>
	<div id="linechart_div"></div>
	<div class="span12">
		<table class="table table-striped span12">
			<thead>
				<tr>
					<th>#</th>
					<th>Last Trading Date</th>
					<th style="text-align: right;">Last Trading Price</th>

				</tr>
			</thead>
			<tbody>
				<c:set var="num" value="1" />
				<c:forEach var="fundHistory" items="${fundPriceList}">
					<tr>
						<td>${num}</td>
						<td>${fundHistory.price_date}</td>
						<td style="text-align: right;">
							<fmt:formatNumber type="number"  pattern="#,##0.00" value="${fundHistory.price}" />
						</td>
					</tr>
					<c:set var="num" value="${num + 1}" />
				</c:forEach>
			</tbody>
		</table>
	</div>	
	</c:otherwise>
</c:choose>
<a href="#chart_div" class="btn"><i class= "icon-th-list" ></i>View Fund List</a>

<hr>
<h4>Fund List</h4>
<!-- For Fund chart -->
<div id="chart_div"></div>

<table class="table table-striped">
	<thead>
		<tr>
			<th>#</th>
			<th>Fund Id</th>
			<th>Fund Name</th>
			<th>Fund Symbol</th>
			<th  style="text-align: right;">Last Trading Price</th>
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
				<td style="text-align: right;">
					<fmt:formatNumber type="number"  pattern="#,##0.00" value="${fundlist.lastTradingPrice}" />
				</td>
				<td>${fundlist.lastTradingDate}</td>
				<td>
					<form method="POST" action="customer-research-fund.do">
						<input type="hidden" name="fundId" value="${fundlist.fundId}">
						<input type="hidden" name="fundName" value="${fundlist.name}">
						<button type="submit" class="btn">See Recent Trends</button>
					</form>
				</td>

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
