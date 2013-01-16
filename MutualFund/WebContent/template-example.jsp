<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	    <title>Carnegie Financial Service - </title>
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
	   <div class="container-fluid">
	   		<div class="row-fluid">
	   				<jsp:include page="header-employee.jsp" />
	   		 		
			   		 <div class="span9">
				          <div class="hero-unit">
					            <h1>Hello, world!</h1>
					            <p>PUT anything here!!!! <br>  if you want backgound like this, use class="hero-unit"</p> <br><br>
					            <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
				          </div>
			   		 </div>
			   		 
			   		 <div class="span9">
					 		<h1>Hello, world!</h1>
					         <p>PUT anything here!!!! <br>  if you don't want back gound, just like this. anyway, depends on you.  </p> <br><br>
					         <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
				     </div>
			 </div>
	   </div>
		
		<!--include footer -->
		<jsp:include page="footer.jsp" />
		
		<!--javascript -->
	    <script src="js/jquery.js"></script>
	    <script src="js/bootstrap.js"></script>
	</body>
</html>