<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--include header -->
<jsp:include page="header-employee-panel.jsp" />

<!--put your page content here 
		  ============================================
		 -->
<form method="post" action="employee-transitionday.do">
<div class="row-fluid">
	<!-- customer name -->
	<div class="span2">
		<label>Transition Day</label>
	</div>
	<div class="span2">
		<label>Last Trading Day: ${ lastDate }</label>
	</div>
	<div class="span2">
		<input type="text"  class="dp span10" name = "specifiedDate" value=" ${ specifiedDate }" />
	</div>
</div>

<label>You should set closing prices to ${ fundListLength } funds.</label>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Fund Id</th>
			<th>Fund Name</th>
			<th>Fund Symbol
			<th>Last Trading Price</th>
			<th>Closing Price</th>
		</tr>
	</thead>
	<c:set var="count" value="0" />
	<tbody>
		<c:forEach var="fund" items="${ fundGeneralList }">
			<input type="hidden" name="fundId${count}" value="${fund.fundId}" />
			<tr>
				<td>${ fund.fundId }</td>
				<td>${ fund.name }</td>
				<td>${ fund.symbol }</td>
				<td>${ fund.lastTradingPrice }</td>
				<td><input type="text" name="price${count}" value="${ fund.lastTradingPrice }" /></td>
			</tr>
			<c:set var="count" value="${ count+1 }" />
		</c:forEach>
	</tbody>
</table>
<input type="hidden" name="fundListLength" value="${ fundListLength }" />
<button type="submit" class="btn">Submit</button>
</form>
=======

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

>>>>>>> refs/remotes/origin/master





<!--end of page content 
		  ============================================
		 -->

<!--include footer -->
<jsp:include page="footer-panel.jsp" />

