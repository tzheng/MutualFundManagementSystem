<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	    <title>Carnegie Financial Service</title>
	    <meta name="description" content="">
	    <meta name="author" content="Team Snipers">
	    
	    <!-- styles -->
	    <link href="css/bootstrap.css" rel="stylesheet" />
	    <link href="css/bootstrap-responsive.css" rel="stylesheet"/>
	    <link href="css/style.css" rel="stylesheet" />
	</head>
	
	<body>
	   
	   <!--include header -->
	   <jsp:include page="header.jsp" />
	   
	   <!--content  -->
	   <div class="container">
	   
	   <!-- 
	   		<div class="row-fluid">
	   			<div class="span6" style="border-right: 1px solid #f5f5f5; ">
	   				<h4 class="homepage-login">Customer Login</h4> <br />
	   				<form class="form-horizontal">
	   					<div class="control-group" >
	   						<label class="control-label" for="inputUser1">Username</label>
	   						<div class="controls">
	   							<input type="text" id="inputUser1" placeholder="Username">
	   						</div>
	   					</div>
	   					<div class="control-group" >
	   						<label class="control-label" for="inputPassword1">Passoword</label>
	   						<div class="controls">
	   							<input type="password" id="inputPassword1" placeholder="Password">
	   						</div>
	   					</div>
	   					<div class="control-group">
						    <div class="controls">
						      <label class="checkbox">
						        <input type="checkbox"> Remember me
						      </label>
						      <button type="submit" class="btn">Login</button>
						    </div>
						  </div>
	   				</form>
	   			</div>
	   			
	   			<div class="span6">
	   				<h4 class="homepage-login">Employee Login</h4> <br />
	   				<div class="alert alert-error">
	   					Wrong Password! 
	   				</div>
	   				<form class="form-horizontal">
	   					<div class="control-group" >
	   						<label class="control-label" for="inputUser2">Username</label>
	   						<div class="controls">
	   							<input type="text" id="inputUser2" placeholder="Username">
	   						</div>
	   					</div>
	   					<div class="control-group" >
	   						<label class="control-label" for="inputPassword2">Passoword</label>
	   						<div class="controls">
	   							<input type="password" id="inputPassword2" placeholder="Password">
	   						</div>
	   					</div>
	   					<div class="control-group">
						    <div class="controls">
						      <label class="checkbox">
						        <input type="checkbox"> Remember me
						      </label>
						      <button type="submit" class="btn">Login</button>
						    </div>
						  </div>
	   				</form>
	   			</div>	   			
	   		
	   		-->
	   		
	   		<div class="row-fluid">
	   			<div class="span4" style="border-right: 1px solid #f5f5f5; ">
	   				<h4 class="homepage-login">User Login</h4> <br />
	   				<div class="alert alert-error">
	   					Wrong Password! 
	   				</div>
	   				<form class="form-horizontal" style="margin-left: -60px;">
	   					<div class="control-group" >
	   						<label class="control-label" for="inputEmail2">Email</label>
	   						<div class="controls">
	   							<input type="text" id="inputEmail2" placeholder="Email">
	   						</div>
	   					</div>
	   					<div class="control-group" >
	   						<label class="control-label" for="inputPassword2">Passoword</label>
	   						<div class="controls">
	   							<input type="password" id="inputPassword2" placeholder="Password">
	   						</div>
	   					</div>
	   					<div class="control-group">
						    <div class="controls">
						      <label class="checkbox">
						        <input type="checkbox"> Remember me
						      </label>
						      <button type="submit" class="btn">Login</button>
						    </div>
						  </div>
	   				</form>
	   			</div>
	   			
	   			
	   			
	   			<div class="span8" >
	   				<div id="advertisement">
						<h1> This is Advertisement! <br> Some other content <br> maybe with some pictures!</h1>
						<p>Carnegie Financial Service Mutual Fund Management System....</p>
						<a class="btn btn-large btn-success" href="#">More Information...</a>
					</div>
	   			</div>
	   			
	   			
	   		</div> <!--  end of .row-fluid -->
	   		<hr>
	   		
	   </div>
		
		<!--include footer -->
		<jsp:include page="footer.jsp" />
		
		<!--javascript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->
	    <script src="js/jquery.js"></script>
	    <script src="js/bootstrap.js"></script>
	</body>
</html>