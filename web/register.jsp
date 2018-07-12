<%-- 
    Document   : register
    Created on : Jul 12, 2018, 8:12:42 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register - Book Share</title>
        <link rel="stylesheet" type="text/css" href="css/login-style.css" />
    </head>
    <body>
        <div class="login-page">
            <div class="form">
                <form  id="registered" action="Login" autocomplete="on"  method="post"> 
                    <input id="name" type="text" name="name" required="required" placeholder="Name"  autocomplete="off"/>
                    <input id="username" type="text" name="username" required="required" placeholder="Username" autocomplete="off"/>
                    <input id="email" type="email" name="email" required="required" placeholder="Email" autocomplete="off"/> 
                    <input type="date" name="bod" required="" placeholder="Date of birth"/>
                    <input id="pass" type="password" name="pass" required="required" placeholder="Password" />
                    <input id="re-pass" type="password" name="re-pass" required="required" placeholder="Re-password" onkeyup='check()' />
                    <span id='message'></span>
                    <%
                        if (null != request.getAttribute("error")) {
                            out.print("<span style=\"color: red;\">" + request.getAttribute("error") + "</span>");
                        }
                    %>
                    <input type="hidden" value="registered" name="command">
                    <button type="submit"> Sign up </button>
                </form>
                <p class="message">Already registered? <a href="login.jsp">Sign In</a></p>
            </div>
        </div>
        <script>
            function check() {
                if (document.getElementById('pass').value === document.getElementById('re-pass').value) {
                    document.getElementById('message').style.color = 'green';
                    document.getElementById('message').innerHTML = 'Confirm password correct.';
                } else {
                    document.getElementById('message').style.color = 'red';
                    document.getElementById('message').innerHTML = 'These passwords do not match. Retry?';
                }
            }
        </script>
    </body>
</html>
