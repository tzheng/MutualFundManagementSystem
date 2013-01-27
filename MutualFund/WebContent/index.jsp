<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Carnegie Financial Service</title>
<meta name="description" content="">
<meta name="author" content="Team Snipers">

<!-- styles -->
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
</head>

	   				<script type="text/javascript">
	   					function OnSubmitForm(form) { 
	   						form.action = (form.usertype[1].checked == true)?'employee-login.do':'customer-login.do'; 
	   						form.submit(); 
	   						} 
					</script>

<body>

	<!--include header -->
	<jsp:include page="header.jsp" />

	<!--content  -->
	<div class="container">

		
		<div class="row-fluid">
			<div class="span5" style="border-right: 1px solid #f5f5f5;" >
				<h4 class="homepage-login">User Login</h4>
				<jsp:include page="error-list.jsp" />
				<br />
				<form class="form-horizontal" method="POST" action="customer-login.do" onSubmit="return false;" >
					<div class="control-group">
						<label class="control-label" for="inputUser1">Username</label>
						<div class="controls">
							<input type="text" id="inputUser1" placeholder="Username" name="userName" value="${ form.userName }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword1">Passoword</label>
						<div class="controls">
							<input type="password" id="inputPassword1" placeholder="Password" name="password">
						</div>
					</div>
					
					<div class="control-group">
					          <label class="control-label">User Type</label>
					          <div class="controls">
								     
								      <label class="radio inline">
								        <input type="radio" name="usertype" checked>
								        Customer
								      </label>
								      <label class="radio inline">
								        <input type="radio" name="usertype">
								        Employee
								      </label>
					  		</div>
				     </div>
					
					<div class="control-group">
						<div class="controls">
							<button type="submit" onClick="OnSubmitForm(this.form);"  class="btn">
								 Login
							</button>

						</div>
					</div>
				</form>
			</div>
 <!--  
			<div class="span6">
				<h4 class="homepage-login">Employee Login</h4>
				<br />
				<form class="form-horizontal" method="POST" action="employee-login.do">
					<div class="control-group">
						<label class="control-label" for="inputUser2">Username</label>
						<div class="controls">
							<input type="text" id="inputUser2" placeholder="Username" name="userName" value="${ form.userName }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword2">Passoword</label>
						<div class="controls">
							<input type="password" id="inputPassword2" placeholder="Password" name="password">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox"> <input type="checkbox">
								Remember me
							</label>

							<button type="submit" class="btn">
								Login
							</button>

						</div>
					</div>
				</form>
			</div>

			-->
			 
	   			
	   			
	   					<div id="myCarousel" class="carousel slide span7" >
	   							<div class="carousel-inner">
	   									<div class="active item">
	   											<img src="img/1.jpg" alt="">
	   											<div class="carousel-caption">
							                      	<h4>Carnegie Financial Service</h4>
							                      	<p>World Leading Mutual Fund Management System </p>
							                     </div>
	   									</div>
	   									
	   									<div class="item">
	   											<img src="img/2.jpg" alt="">
	   											<div class="carousel-caption">
							                      	<h4>Carnegie Financial Service</h4>
							                      	<p>Your best choice </p>
							                     </div>
	   									</div>
	   							</div>
	   							<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
  								<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
	   					</div>
	   					
	   					
	   			</div> <!--  end of .row-fluid -->
	   			
	   			
	   		</div> <!--  end of container -->




		<!--include footer -->
		<jsp:include page="footer.jsp" />

		<!--javascript
	    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="js/jquery.js"></script>
		<script src="js/bootstrap.js"></script>
</body>
</html>