<%@page import="net.nightst0rm.utils.StringUtils"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Nightst0rm - Register</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/datepicker3.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->
        <%
            if (request.getParameter("infor") != null) {
                String infor = request.getParameter("infor");
            }
        %>
    </head>
    <body>
        <div class="row">
            <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">Register</div>
                    <div class="panel-body">
                        <form role="form" action="/Register.9st0rm" method="POST">
                            <fieldset><%=StringUtils.convertStringToHTMLCode(request.getParameter("infor"))%>
                                <div class="form-group">
                                    <input class="form-control" placeholder="username" name="username" type="text" autofocus="">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <input type="submit" value="Register" class="btn btn-primary"/>
                                <button type="button" class="btn btn-md btn-warning" onClick="goLogin();">Go Login</button>
                            </fieldset>

                        </form>
                    </div>
                </div>
            </div><!-- /.col-->
        </div><!-- /.row -->	


        <script src="js/jquery-1.11.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
    <script type="text/javascript" charset="UTF-8">
                        $(document).ready(function () {
                            goLogin = function () {
                                window.location = "/login.jsp";
                            }
                        });
                    </script>
</html>
