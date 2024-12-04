<%-- 
    Document   : Home.jsp
    Created on : Sep 24, 2017, 8:43:14 PM
    Author     : buxuqua
--%>

<%@page import="net.nightst0rm.utils.StringUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    if(request.getSession().getAttribute("role") == null){
        response.sendRedirect("/login.jsp");
    }
%>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Dashboard</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<link href="css/datepicker3.css" rel="stylesheet">
	<link href="css/styles.css" rel="stylesheet">
	
	<!--Custom Font-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
	<!--[if lt IE 9]>
	<script src="js/html5shiv.js"></script>
	<script src="js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span></button>
				<a class="navbar-brand" href="/"><span>Simple</span>Panel</a>
				
	</nav>
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<div class="profile-sidebar">
			<div class="profile-userpic">
                            <img src="<%=request.getSession().getAttribute("avatarPath") %>" class="img-responsive" alt="">
			</div>
			<div class="profile-usertitle">
				<div class="profile-usertitle-name"><%= StringUtils.convertStringToHTMLCode((String)request.getSession().getAttribute("username"))%></div>
				<div class="profile-usertitle-name"><%= request.getSession().getAttribute("username")%></div><!-- test security scan tool XSS -->
				<div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
			</div>
			<div class="clear"></div>
		</div>
		<div class="divider"></div>
		<ul class="nav menu">
			<li class="active"><a href="/home.jsp"><em class="fa fa-dashboard">&nbsp;</em> Dashboard</a></li>
			<li><a href="/avatar.jsp"><em class="fa fa-copy">&nbsp;</em> Avatar Manager</a></li>
                        <li><a href="/logManager.jsp"><em class="fa fa-low-vision">&nbsp;</em> Log Manager</a></li>
			<li><a href="/Logout.9st0rm"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
		</ul>
	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="/">
					<em class="fa fa-home"></em>
				</a></li>
				<li class="active">Dashboard</li>
			</ol>
		</div><!--/.row-->
		
		
		<div class="row">
			<div class="panel panel-default ">
					<div class="panel-heading">
						Timeline
						<span class="pull-right clickable panel-toggle panel-button-tab-left"><em class="fa fa-toggle-up"></em></span></div>
					<div class="panel-body timeline-container">
						<ul class="timeline">
                                                        <li>
								<div class="timeline-badge"><em class="glyphicon glyphicon-plus"></em></div>
								<div class="timeline-panel">
									<div class="timeline-heading">
										<h4 class="timeline-title">From: buxu</h4>
									</div>
									<div class="timeline-body">
										<p>Welcome to Vietnam and WhiteHat Contest, i hope you all will have great time in my country. Let's dance :).</p>
									</div>
								</div>
							</li>
							
						</ul>
					</div>
				</div>
		</div><!--/.row-->
		
		
	</div>	<!--/.main-->
	
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/custom.js"></script>

		
</body>
</html>
