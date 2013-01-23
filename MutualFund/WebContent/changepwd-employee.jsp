
	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />


	   	<!--put your page content here 
		  ============================================
		 -->

<h4>Change Password</h4> <hr />
<jsp:include page="error-list.jsp" />
<jsp:include page="success-status.jsp" />
<form class="form-horizontal" method="POST" action="employee-change-pwd.do">
  <div class="control-group">
    <label class="control-label" for="inputOldPassword">Old Password</label>
    <div class="controls">
      <input type="text" id="inputOldPassword" placeholder="Old Password" name="oldPassword">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputNewPassword">New Password</label>
    <div class="controls">
      <input type="password" id="inputNewPassword" placeholder="New Password" name="newPassword">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputConfirmPassword">Confirm New Password</label>
    <div class="controls">
      <input type="password" id="inputConfirmPassword" placeholder="Confirm New Password" name="confirmPassword">
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">Change Password</button>
    </div>
  </div>
</form>






		<!--end of page content 
		  ============================================
		 -->

		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
