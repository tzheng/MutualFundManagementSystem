
 <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />
<div>
				<ul class="breadcrumb">
						<li><a href="employee-mainpanel.jsp"><i class="icon-home"></i> Home</a> <span class="divider">/</span></li>
						<li class="active">Reset Customer Password</li>
				</ul>
		</div>

	   	<!--put your page content here 
		  ============================================
		 -->

<h4>Reset Customer Password</h4> <hr />
<form class="form-horizontal">
  <div class="control-group">
    <label class="control-label" for="inputUsername">Username</label>
    <div class="controls">
      <input type="text" id="inputUsername" placeholder="Username">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputNewPassword">New Password</label>
    <div class="controls">
      <input type="password" id="inputNewPassword" placeholder="New Password">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputConfirmPassword">Confirm New Password</label>
    <div class="controls">
      <input type="password" id="inputConfirmPassword" placeholder="Confirm New Password">
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">Reset Password</button>
    </div>
  </div>
</form>






		<!--end of page content 
		  ============================================
		 -->

		<!--include footer -->
		<jsp:include page="footer-panel.jsp" />
