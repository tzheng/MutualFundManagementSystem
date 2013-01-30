	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
	 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
			   		
			   		<!--display path  -->	   		 		
		<div>
				<ul class="breadcrumb">
						<li><a href="employee-mainpanel.jsp"><i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">Create Fund</li>
				</ul>
		</div> 
		
		<!--put your page content here 
		  ============================================
		 -->
		
				          <div>
					              <form class="form-horizontal" method="post" action="createfund.do">
						            		<legend>Create Fund</legend>
						            		<jsp:include page="error-list.jsp" />
				   						<div class="control-group">
											<label class="control-label"  for="inputFundname">Fund Name</label>
											<div class="controls">
												<input type="text"  name="fundName" id="inputFundid" placeholder="Fund Name" value="${form.fundName }">
											</div>
										</div>
									      <div class="control-group">
											<label class="control-label"  for="inputTicker">Ticker Symbol</label>
											<div class="controls">
												<input type="text" name="symbol" id="inputTickerid" placeholder="one to five characters" value="${form.symbol }">
											</div>
										</div>  
										<div class="control-group">
											<div class="controls">
												<button type="submit" class="btn">Create</button>
											</div>
										</div>    	
					            </form>
					            
					            <hr>
					            
					            <h4 id="fundlist">Fund List</h4>	
								<hr>
									<table class="table table-striped">
								 				<thead>
							   								<tr class="info">
							   									<th>Fund Name</th>
						                                          <th >Symbol</th>
						    										<th style="text-align: right">Last Trading Date</th>
						    										<th style="text-align: right">Last Trading Price</th>
						  									</tr>
						  						</thead>
						  						<tbody>
						  							   <c:forEach var="fundlist" items="${ fundGeneralList}">
							  							   	<tr>
						  							   			<td>${fundlist.name} </td>
						  							   			<td>${fundlist.symbol} </td>
						  							   			<td style="text-align: right">
						  							   					<c:choose>
														  					<c:when test="${ empty fundlist.lastTradingDate}" >
														  							-
														  					</c:when>
														  					<c:otherwise>
														  							${fundlist.lastTradingDate} 
														  					</c:otherwise>
														  				</c:choose> 
						  							   			</td>
						  							   			<td style="text-align: right">
						  							   				<c:set var="zero" value="0.00" />
							  							   			<c:set var="ltp" value="${fundlist.lastTradingPrice}" />
							  							   			<c:choose>
							  							   					<c:when test="${ zero eq  ltp}" >
							  							   						-
							  							   					</c:when>
							  							   					<c:otherwise>
							  							   							<fmt:formatNumber type="number" pattern="#,##0.00" value="${fundlist.lastTradingPrice}" />
							  							   					</c:otherwise>
							  							   			</c:choose>
						  							   			</td>
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