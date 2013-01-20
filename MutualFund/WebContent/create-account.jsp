
	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
	   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

       <c:forEach var="error" items="${errors}">
	   <h3 style="color:red"> ${error} </h3>
       </c:forEach>

	   	<!--put your page content here 
		  ============================================
		 -->

<h4>Create Account</h4> <hr />
<form class="form-horizontal">
  <div class="control-group">
    <label class="control-label" for="inputUsername">User Name</label>
    <div class="controls">
      <input type="text" id="inputUsername" placeholder="Username" value="${form.userName }">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">Password</label>
    <div class="controls">
      <input type="password" id="inputPassword" placeholder="Password" value="">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputConfirmPassword">Confirm Password</label>
    <div class="controls">
      <input type="password" id="inputConfirmPassword" placeholder="Confirm Password" value="">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputFirstName">First Name</label>
    <div class="controls">
      <input type="text" id="inputFirstName" placeholder="First Name" value="${form.firstName }">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputLastName">Last Name</label>
    <div class="controls">
      <input type="text" id="inputLastName" placeholder="Last Name" value="${form.lastName }">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputAddrLine1">Address Line 1</label>
    <div class="controls">
      <input type="text" id="inputAddrLine1" placeholder="Address Line 1" value="${form.addrLine1 }">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputAddrLine2">Address Line 2</label>
    <div class="controls">
      <input type="text" id="inputAddrLine2" placeholder="Address Line 2" value="${form.addrLine2 }">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputCity">City</label>
    <div class="controls">
      <input type="text" id="inputCity" placeholder="City" value="${form.city }">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputState">State</label>
    <div class="controls">
      <input type="text" id="inputState" placeholder="State" value="${form.state }">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputZip">Zip</label>
    <div class="controls">
      <input type="text" id="inputZip" placeholder="Zip" value="${form.state }">
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">Create Account</button>
    </div>
  </div>
</form>






		<!--end of page content 
		  ============================================
		 -->

		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
