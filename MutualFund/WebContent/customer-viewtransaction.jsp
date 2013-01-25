	    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	   
	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />
	   	
	   	
	   	<!--put your page content here 
		  ============================================
		 -->
		 
		 <!-- 
		  <h4>Transaction History Filter</h4> <hr>
		 <div>  
		 		 <form class="form-horizontal">
				 		 <div class="control-group">
							    <label class="control-label" for="dateRange">Date Range</label>
							    <div class="controls">
							    		<div class="row-fluid">
							          		<div class="span5">From: <input type="text"  class="dp span6" value="02-16-2012" ></div>
							          		 <div class="span5">To: <input type="text"  class="dp span6" value="02-16-2012" > </div>
							    		</div>
							    </div>
						  </div>
						  
						  <div class="control-group">
							    <label class="control-label" for="dateRange">Amount Range</label>
							    <div class="controls">
							    		<div class="row-fluid">
							          		<div class="span5">From:<div class="input-prepend">
									  	<span class="add-on">$</span>
									  	<input class="span6" id="appendedPrependedInput" type="text" placeholder="0.00">
									</div></div>
							          		 <div class="span4">To: <div class="input-prepend">
									  	<span class="add-on">$</span>
									  	<input class="span6" id="appendedPrependedInput" type="text" placeholder="0.00">
									</div> </div>
							    		</div>
							    </div>
						  </div>
					
					<script type="text/javascript">
							function changeFilter(obj){
								txt = obj.options[obj.selectedIndex].text;
								
								document.getElementById('cashOperation').style.display = 'none'; 
								document.getElementById('fundOperation').style.display = 'none'; 
								
								if ( txt.match('Cash')) {
									document.getElementById('cashOperation').style.display='block';
								}
								if ( txt.match('Fund')) {
									document.getElementById('fundOperation').style.display='block';
								}
							}
					</script>	  
						  
					<div class="control-group">
					          
					          <label class="control-label">Transaction Type</label>
					          <div class="controls">
					            <select class="input-xlarge" id="transactionType" onchange="changeFilter(this)">
								      <option>Cash</option>
								      <option>Fund</option>
								</select>
					          </div>
					        </div>	  
					        
					 <div class="control-group" id="cashOperation">
					          <label class="control-label">Cash Operation</label>
					          <div class="controls">
					            <select class="input-xlarge">
					      <option>Check</option>
					      <option>Deposit</option></select>
					          </div>
					        </div>
					
					    <div class="control-group" id="fundOperation" style="display: none;">
					          <label class="control-label">Fund Operation</label>
					          <div class="controls">
					            <select class="input-xlarge">
					      <option>Buy</option>
					      <option>Sell</option></select>
					          </div>
					        </div>
					
					    <div class="control-group">
					          <label class="control-label">Status</label>
					          <div class="controls">
					            <select class="input-xlarge">
					      <option>Processed</option>
					      <option>Pending</option></select>
					          </div>
					        </div>

					 	 <div class="control-group" style="margin-top: -10px;">
							    <div class="controls">
							      <button type="submit" class="btn">Search</button>
							    </div>
						  </div> 	
						  

		 		 </form>
		 </div>
		 
		 <hr>
		  -->
		  <h4>Transaction History</h4>  <hr>
		 <div>  <!--  Here list the Transactions -->
		 		<table class="table table-striped" >
		 				<thead>
	   								<tr class="info" style="text-align: right;">
                                          <th style="text-align: center;">Date</th>
    										<th>Operation</th>
    										<th>Fund Name</th>
    										<th style="text-align: center;">Share Number</th>
    										<th style="text-align: center;">Share Price</th>
    										<th style="text-align: center;">Dollar Amount</th>
    										<th>Status</th>
  									</tr>
  						</thead>
  						<tbody>
  							   <c:forEach var="history" items="${transactionHistory}">
  							   		<c:set var="status" value="${history.transactionStatus}"></c:set>
  							   		<c:set var="pending" value="Pending"> </c:set>
  							   		<c:set var="rejected" value = "Rejected"></c:set>
  							   		<c:choose>
	  							   		<c:when test="${status eq pending}">
	  							   			<tr class="info">
	  							   		</c:when>
	  							   		<c:when test="${status eq rejected}">
	  							   			<tr class="error">
	  							   		</c:when>
	  							   		<c:otherwise>
	  							   			<tr>
	  							   		</c:otherwise>
  							   		</c:choose>
  							   		
  							   			<c:choose>
  							   				<c:when test="${ empty history.transactionDate }">
  							   					<td style="text-align: center">-</td> 
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: center;">${history.transactionDate} </td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<td>${history.operation} </td>
  							   			<td>${history.fundName} </td>
  							   			
  							   			<c:set var="amount" value="${history.dollarAmount}"></c:set>
  							   			<c:set var="zero" value = "0.00"></c:set> 
  							   			
  							   			<c:set var="shares" value="${history.shareNumber}"></c:set>
  							   			<c:set var="zero" value = "0.000"></c:set> 
  							   			<c:choose>
  							   				<c:when test= "${shares eq zero }">
  							   						<td style="text-align: center">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 50px;">${history.shareNumber} </td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<c:set var="price" value="${history.sharePrice}"></c:set>
  							   			<c:set var="zero1" value = "0.00"></c:set> 
  							   			<c:choose>
  							   				<c:when test= "${price eq zero1 }">
  							   						<td style="text-align: center">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 50px;">${history.sharePrice} </td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			
  							   			<c:set var="dollar" value="${history.dollarAmount}"></c:set>
  							   			<c:choose>
  							   				<c:when test= "${dollar eq zero1 }">
  							   						<td style="text-align: center">-</td>
  							   				</c:when>
  							   				<c:otherwise>
  							   					<td style="text-align: right; padding-right: 50px;">${history.dollarAmount} </td>
  							   				</c:otherwise>
  							   			</c:choose>
  							   			<td>${history.transactionStatus} </td>
  							   		</tr>
  							   		
  							   </c:forEach>
  						</tbody>
  				</table>
		 </div>
		 
		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		
