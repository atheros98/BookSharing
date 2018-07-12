<%-- 
    Document   : login
    Created on : Jul 12, 2018, 8:02:00 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Book Share</title>
        <link rel="stylesheet" type="text/css" href="css/login-style.css" />
    </head>
    <body>
        <div class="login-page">
            <div class="form">
                <form  class="login-form" action="Login" autocomplete="on" method="post"> 
                    <input type="text" name="username" required="required" placeholder="User name"/>
                    <input type="password" name="password" required="required" placeholder="Password" /> 
                    <%
                        if (null != request.getAttribute("error")) {
                            out.print("<h4 style=\"color: red;\">" + request.getAttribute("error") + "</h4>");
                        }
                    %>
                    <input type="hidden" value="login" name="command">
                    <button type="submit"> Login </button>
                    <p class="message">Not registered? <a href="register.jsp">Create an account</a></p>
                </form>
            </div>
        </div>
    </body>
</html>
