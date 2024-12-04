<%@page import="java.util.Collection"%>
<%@page import="net.nightst0rm.entities.Token"%>
<%@page import="net.nightst0rm.entities.TokenManager"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.nightst0rm.entities.Logger"%>
<%@page import="net.nightst0rm.utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if (request.getSession().getAttribute("role") == null) {
        response.sendRedirect("/login.jsp");
        return;
    }
    Integer user_id = (Integer)session.getAttribute("user_id");
    Logger logger = new Logger();
    logger.setUser_id(user_id);
    ArrayList<Logger> list = logger.getList();
    
    TokenManager tokenManager = new TokenManager();
    Token token = new Token();
    token.setUser_id(user_id);
    tokenManager.setToken(token);
    Collection collection = tokenManager.values();
    ArrayList<Token> arrayList = new ArrayList<Token>(collection);
%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet"
              href="js/source/fancybox/jquery.fancybox-1.3.4.css" type="text/css"
              media="screen" />
        <link rel="stylesheet"
              href="resourcesss/biz/css/style.css" type="text/css"
              media="screen" />
        <script type="text/javascript" src="js/source/jquery-1.4.3.min.js"></script>
        <script type="text/javascript" src="js/source/jquery.cookie.js"></script>
        <script type="text/javascript"
        src="js/source/fancybox/jquery.fancybox-1.3.4.pack.js" charset="UTF-8"></script>
        <script type="text/javascript"
        src="js/source/fancybox/jquery.fancybox-1.3.4.js" charset="UTF-8"></script>
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
                                <img src="<%=request.getSession().getAttribute("avatarPath")%>" class="img-responsive" alt="">
                            </div>
                            <div class="profile-usertitle">
                                <div class="profile-usertitle-name"><%= StringUtils.convertStringToHTMLCode((String) request.getSession().getAttribute("username"))%></div>
                                <div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="divider"></div>
                        <ul class="nav menu">
                            <li><a href="/home.jsp"><em class="fa fa-dashboard">&nbsp;</em> Dashboard</a></li>
                            <li><a href="/avatar.jsp"><em class="fa fa-copy">&nbsp;</em> Avatar Manager</a></li>
                            <li class="active"><a href="/logManager.jsp"><em class="fa fa-low-vision">&nbsp;</em> Log Manager</a></li>
                            <li><a href="/Logout.9st0rm"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>
                        </ul>
                    </div><!--/.sidebar-->

                    <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
                        <div class="row">
                            <ol class="breadcrumb">
                                <li><a href="/">
                                        <em class="fa fa-home"></em>
                                    </a></li>
                                <li class="active">Log Manager</li>
                            </ol>
                        </div><!--/.row-->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="panel panel-default">
                                    <div class="panel-body tabs">
                                        <ul class="nav nav-tabs">
                                            <li class="active"><a href="#tab1" data-toggle="tab">Log</a></li>
                                            <li><a href="#tab2" data-toggle="tab">Token</a></li>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane fade in active" id="tab1">
                                                <div class="panel-body timeline-container">
						<ul class="timeline">
                                                    <%
                                                    Iterator<Logger> iterator = list.iterator();
                                                    while(iterator.hasNext())  
                                                        {
                                                        Logger log = iterator.next();

                                                    %>
							<li>
								<div class="timeline-badge"><em class="glyphicon glyphicon-pushpin"></em></div>
								<div class="timeline-panel">
									<div class="timeline-heading">
										<h4 class="timeline-title">Event <%=log.getUser_id()%>-<%=log.getId()%></h4>
									</div>
									<div class="timeline-body">
										<p><%=log.getEvent()%></p>
									</div>
								</div>
							</li>
							
                                                        <%
                                                        }
                                                        %>
						</ul>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="tab2">
                                                <div class="panel-body timeline-container">
						<ul class="timeline">
                                                    <%
                                                    Iterator<Token> iterator2 = arrayList.iterator();
                                                    while(iterator2.hasNext())  
                                                        {
                                                        Token tk = iterator2.next();

                                                    %>
							<li>
								<div class="timeline-badge"><em class="glyphicon glyphicon-pushpin"></em></div>
								<div class="timeline-panel">
									<div class="timeline-heading">
										<h4 class="timeline-title">Token <%=tk.getUser_id()%>-<%=tk.getId()%></h4>
									</div>
									<div class="timeline-body">
										<p><%=tk.getToken_value()%></p>
									</div>
								</div>
							</li>
							
                                                        <%
                                                        }
                                                        %>
						</ul>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div><!--/.panel-->
                            </div><!--/.col-->
			
		</div><!--/.row-->


                    </div>	<!--/.main-->

                    <script src="js/jquery-1.11.1.min.js"></script>
                    <script src="js/bootstrap.min.js"></script>


                    </body>
                    <script type="text/javascript" charset="UTF-8">
                                                            
                    </script>
                    </html>