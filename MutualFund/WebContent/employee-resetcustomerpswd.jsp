
	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
	  <%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

       <c:forEach var="error" items="${errors}">
	   <h3 style="color:red"> ${error} </h3>
       </c:forEach> --%>

<!--put your page content here 
		  ============================================
		 -->

<h4>Reset Customer Password</h4> <hr />
<jsp:include page="error-list.jsp" />
<jsp:include page="success-status.jsp" />
<form class="form-horizontal" method="post" action="employee-reset-customer-pwd.do">
  <div class="control-group">
    <label class="control-label" for="inputUsername">Username</label>
    <div class="controls">
      <input type="text" name="userName" id="inputUsername" placeholder="Username"  value="${ form.userName }" >
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">New Password</label>
    <div class="controls">
      <input type="password" name="newPassword" id="inputPassword" placeholder="New Password"  >
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputConfirmPassword">Confirm Password</label>
    <div class="controls">
      <input type="password" name="confirmPassword" id="inputConfirmPassword" placeholder="Confirm Password" >
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">Reset Customer Password</button>
    </div>
  </div>
</form>






		<!--end of page content 
		  ============================================
		 -->

		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
