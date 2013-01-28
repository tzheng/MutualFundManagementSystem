 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


<!--put your page content here 
		  ============================================
		 -->


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
				<td>${fundlist.lastTradingDate}</td>
				<td>${fundlist.lastTradingPrice}</td>
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
