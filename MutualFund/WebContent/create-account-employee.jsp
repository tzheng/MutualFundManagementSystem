
<!--include header -->
<jsp:include page="header-employee-panel.jsp" />

<!--display path  -->
<div>
	<ul class="breadcrumb">
		<li><a href="employee-mainpanel.jsp"> <i class="icon-home"></i>
				Home
		</a> <span class="divider">/</span></li>
		<li class="active">Create Employee Account</li>
	</ul>
</div>

<!--put your page content here 
		  ============================================
		 -->

<h4>Create Employee Account</h4>
<h5>
	Note:&nbsp;<span style="color: red">*</span>&nbsp;stands for required
	field
</h5>
<hr />
<jsp:include page="error-list.jsp" />
<form class="form-horizontal" method="post"
	action="create-employee-account.do">
	<div class="control-group">
		<label class="control-label" for="inputUsername">Username</label>
		<div class="controls">
			<input type="text" name="userName" id="inputUsername"
				placeholder="Username"<%-- value="${ form.userName }" --%> >
			<span style="color: red">*</span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputFirstName">First Name</label>
		<div class="controls">
			<input type="text" name="firstName" id="inputFirstName"
				placeholder="First Name"<%-- value="${ form.firstName }" --%> >
			<span style="color: red">*</span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputLastName">Last Name</label>
		<div class="controls">
			<input type="text" name="lastName" id="inputLastName"
				placeholder="Last Name"<%-- value="${ form.lastName }" --%> >
			<span style="color: red">*</span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputPassword">Password</label>
		<div class="controls">
			<input type="password" name="password" id="inputPassword"
				placeholder="Password"> <span style="color: red">*</span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label" for="inputConfirmPassword">Confirm
			Password</label>
		<div class="controls">
			<input type="password" name="confirmPassword"
				id="inputConfirmPassword" placeholder="Confirm Password">
			<span style="color: red">*</span>
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
