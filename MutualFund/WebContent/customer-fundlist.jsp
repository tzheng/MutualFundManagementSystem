<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	

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
  							   			<td> <button class="btn">Buy Fund</button>
  							   		</tr>
  							   		<input type="hidden" name="fundId" value="${fundlist.fundId} " />
  							   </c:forEach>
  						</tbody>
  				</table>