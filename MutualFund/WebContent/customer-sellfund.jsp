<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	

	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	<!--display path  -->	   		 		
		<div>
				<ul class="breadcrumb">
						<li><a href="customer-mainpanel.do"> <i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">Sell Fund</li>  
				</ul>
		</div>	 
	   	
	   	
	   	<script type="text/javascript">
				$(document).ready(function(){
					$('.fundBtn').click(function(e){
						$.scrollTop(300);
						e.preventDefault();
					});
				});
		</script>
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		<h4 id="chooseFund"> Sell Fund </h4> <hr>
	 				<jsp:include page="success-status.jsp"></jsp:include>
	 				<jsp:include page="error-list.jsp"></jsp:include>
			   		 <form class="form-horizontal" method="post" action="customer-sellfund.do">
				   		  <div class="control-group">
							    <label class="control-label" for="balance"><b>Fund Name</b></label>
							    <div class="controls controlwords">
							       <div class="row-fluid">
							      			<div class="span4">
							      				<c:choose>
							      						<c:when test="${ empty getFundName }">
							      							<input id="fundname" name="fundName"  type="text" placeholder="Fund Name"  value="${ form.fundName }" >
							      						</c:when>
							      						<c:otherwise>
							      							 <input id="fundname" name="fundName"  type="text" placeholder="Fund Name"  value="${ getFundName }">
							      						</c:otherwise>
							      				</c:choose>
							      				<br>
							      				<a href="#fundlist">View Funds</a>
							      			</div>
							      			
							      			<!-- 
							      			<div class="span6">
									      		 <select multiple="multiple" onchange="changfund(this)">
													  <option>Fund 1</option>
													  <option>Fund 2</option>
													  <option>Fund 3</option>
													  <option>Fund 4</option>
													  <option>Fund 5</option>
													  <option>Fund 6</option>
													  <option>Fund 7</option>
													  <option>Fund 8</option>
												</select>
							      			</div>
							      			-->
							      	</div>

							    </div>
						  </div>
						  
						  
						  <div class="control-group">
							    <label class="control-label" for="balance"><b>Enter Shares</b></label>
							    <div class="controls">
							      	<div class="input-prepend">
									  	<input class="span10" id="appendedPrependedInput" type="text" name="shares" >
									</div>
							    </div>
						  </div>
						  
						  <div class="control-group">
							    <div class="controls">
							      <button type="submit" class="btn">Sell Fund</button>
							    </div>
						  </div>
					  </form>
		<hr>
		
		<h4 id="fundlist">Your Fund List</h4>	
		<hr>
			<table class="table table-striped">
		 				<thead>
	   								<tr class="info">
	   										<th>Fund Name</th>
	   										<th>Fund Symbol</th>
    										<th>Shares you own</th>
    										<th>Available Shares</th>
    										<th></th>
  									</tr>
  						</thead>
  						<tbody>
  							   <c:forEach var="fund" items="${ positionList}">
	  							   	<tr>
  							   			<td>${fund.fundName} </td>
  							   			<td>${fund.fundSymbol} </td>
  							   			<td>${fund.shares} </td>
  							   			<td>${fund.availableShares}</td>
  							   			<td> <a href="#chooseFund" class="btn" class="fundBtn" onclick="javascript:document.getElementById('fundname').value='${fund.fundName}';">Sell Fund</a>
  							   		</tr>
  							   </c:forEach>
  						</tbody>
  				</table>
			   		 		
			<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		