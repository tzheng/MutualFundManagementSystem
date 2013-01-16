
	   <!--include header -->
	   <jsp:include page="header-employee-panel.jsp" />


	   	<!--put your page content here 
		  ============================================
		 -->

<h4>Create Account</h4> <hr />
<form class="form-horizontal">
  <div class="control-group">
    <label class="control-label" for="inputUsername">Username</label>
    <div class="controls">
      <input type="text" id="inputUsername" placeholder="Username">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">Password</label>
    <div class="controls">
      <input type="password" id="inputPassword" placeholder="Password">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputConfirmPassword">Confirm Password</label>
    <div class="controls">
      <input type="password" id="inputConfirmPassword" placeholder="Confirm Password">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputFirstName">First Name</label>
    <div class="controls">
      <input type="text" id="inputFirstName" placeholder="First Name">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputLastName">Last Name</label>
    <div class="controls">
      <input type="text" id="inputLastName" placeholder="Last Name">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputAddress">Address</label>
    <div class="controls">
      <input type="text" id="inputAddress" placeholder="Address">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputCity">City</label>
    <div class="controls">
      <input type="text" id="inputCity" placeholder="City">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputState">State</label>
    <div class="controls">
      <input type="text" id="inputState" placeholder="State">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputZip">Zip</label>
    <div class="controls">
      <input type="text" id="inputZip" placeholder="Zip">
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
