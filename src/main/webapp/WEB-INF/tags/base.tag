<%@tag description="base" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

  <head>
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <meta charset="utf-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <meta name="description" content="">	
	
	  <title>Absence</title>    
     <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.3/css/mdb.min.css" rel="stylesheet">
  </head>

  <body style="background-color: #212121; color: #ECEFF1;>
    <jsp:include page="/WEB-INF/tags/header.jsp" />
    <jsp:include page="/WEB-INF/include.jsp" />
    <div id="body" style="background-color: #212121; color: #ECEFF1;">
      <jsp:doBody/>
    </div>
    <jsp:include page="/WEB-INF/tags/footer.jsp" />
  
   
    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.7.3/js/mdb.min.js"></script>
  </body>

</html>