
	   <!--include header -->
	   <jsp:include page="header-customer-panel.jsp" />

<!--display path  -->	   		 		
		<div>
				<ul class="breadcrumb">
						<li><a href="view-account.do"> <i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">Change Password</li>  
				</ul>
		</div>	 
		
	   	<!--put your page content here 
		  ============================================
		 -->

<h4>Change Password</h4> <hr />
<jsp:include page="error-list.jsp" />
<jsp:include page="success-status.jsp" />
<form class="form-horizontal" method="POST" action="customer-change-pwd.do">
  <div class="control-group">
    <label class="control-label" for="inputOldPassword">Current Password</label>
    <div class="controls">
      <input type="password" id="inputOldPassword" placeholder="Current Password" name="oldPassword">
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
