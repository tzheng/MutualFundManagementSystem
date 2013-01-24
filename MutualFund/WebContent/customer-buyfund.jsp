	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
	   
	   
	   
	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />


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
	 				<h4 id="chooseFund"> Buy Fund </h4> <hr>
	 				<jsp:include page="success-status.jsp"></jsp:include>
	 				<jsp:include page="error-list.jsp"></jsp:include>
			   		 <form class="form-horizontal" method="post" action="customer-buyfund.do">
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
							      							 <input id="fundname" name="fundName"  type="text" placeholder="Fund Name"  value="${getFundName }">
							      						</c:otherwise>
							      				</c:choose>
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
							    <label class="control-label" for="mailadd"><b> Your Account Balance: </b></label>
							    <div class="controls controlwords">
							      	<p id="FundInfo" >
							      	 $ ${cash }
									</p>
							    </div>
						  </div>
						  
						  
						  <div class="control-group">
							    <label class="control-label" for="balance"><b>Enter Amount</b></label>
							    <div class="controls">
							      	<div class="input-prepend">
									  	<span class="add-on">$</span>
									  	<input class="span10" id="appendedPrependedInput" type="text" placeholder="Max: ${cash }" name="amount" value="${ form.amount }" >
									</div>
							    </div>
						  </div>
						  
						  <div class="control-group">
							    <div class="controls">
							      <button type="submit" class="btn">Buy Fund</button>
							    </div>
						  </div>
					  </form>
		<hr>
		
		<h4>Fund List</h4>	
		<hr>
			<table class="table table-striped">
		 				<thead>
	   								<tr class="info">
	   									<th>Fund Name</th>
                                          <th>Symbol</th>
    										<th>Last Trading Date</th>
    										<th>Last Trading Price</th>
    										<th></th>
  									</tr>
  						</thead>
  						<tbody>
  							   <c:forEach var="fundlist" items="${ fundGeneralList}">
	  							   	<tr>
  							   			<td>${fundlist.name} </td>
  							   			<td>${fundlist.symbol} </td>
  							   			<td>${fundlist.lastTradingDate} </td>
  							   			<td>${fundlist.lastTradingPrice}</td>
  							   			<td> <a href="#chooseFund" class="btn" class="fundBtn" onclick="javascript:document.getElementById('fundname').value='${fundlist.name}';">Buy Fund</a>
  							   		</tr>
  							   </c:forEach>
  						</tbody>
  				</table>

		<!--end of page content 
		  ============================================
		 -->
		
		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
		