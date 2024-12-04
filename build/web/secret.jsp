<%-- 
    Document   : secret
    Created on : 10/10/2018, 3:24:52 PM
    Author     : qubu
--%>
<%
    if (request.getSession().getAttribute("role") == null) {
        response.sendRedirect("/login.jsp");
        return;
    }
    if (!request.getParameter("auth").equals("bfhdsadjahsiahfhbjanffxxxxxkjakdfhadajfada")) {
        response.sendRedirect("/login.jsp");
        return;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Secret page</title>
    </head>
    <body>
        <h1>First flag: WhiteHat2018{thisisflag}</h1>
        <h1>War file: https://drive.google.com/file/d/1tAsD6EcnQuodvAvEhzwNZKBBbx_Hcqzq/view?usp=sharing</h1>
    </body>
</html>
