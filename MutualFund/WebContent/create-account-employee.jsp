
	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />

<!--put your page content here 
		  ============================================
		 -->

<h4>Create Employee Account</h4> <hr />
<jsp:include page="error-list.jsp" />
<form class="form-horizontal" method="post" action="create-employee-account.do">
  <div class="control-group">
    <label class="control-label" for="inputUsername">User Name</label>
    <div class="controls">
      <input type="text" name="userName" id="inputUsername" placeholder="Username"  <%-- value="${ form.userName }" --%> >
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputFirstName">First Name</label>
    <div class="controls">
      <input type="text" name="firstName" id="inputFirstName" placeholder="First Name"  <%-- value="${ form.firstName }" --%> >
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputLastName">Last Name</label>
    <div class="controls">
      <input type="text" name="lastName" id="inputLastName" placeholder="Last Name"  <%-- value="${ form.lastName }" --%> >
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">Password</label>
    <div class="controls">
      <input type="password" name="password" id="inputPassword" placeholder="Password"  >
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
