<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password - Book Share</title>
        <link rel="stylesheet" type="text/css" href="css/login-style.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt"
              crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="content">
                <div class="login-page">
                    <div class="form">
                        <form  id="registered" action="ChangePasswordController" autocomplete="on"  method="post"> 
                            <h1 style="padding-bottom: 25px"> Change Password </h1>
                            <input id="old-pass" type="password" name="old-password" required="required" placeholder="Old Password" autocomplete="off"/> 
                            <input id="pass" type="password" name="password" required="required" placeholder="New Password" />
                            <input id="re-pass" type="password" name="re-pass" required="required" placeholder="Confirm Password" onkeyup='check()' />
                        <%
                            if (null != request.getAttribute("error")) {
                                out.print("<h4 style=\"color: red;\">" + request.getAttribute("error") + "</h4>");
                            } else if (null != request.getAttribute("success")) {
                                out.print("<h4 style=\"color: green;\">" + request.getAttribute("error") + "</h4>");
                            } else {
                                out.print("<h4 id='message'></h4>");
                            }
                        %>
                        <input type="hidden" value="register" name="command">
                        <button type="submit"> Submit </button>
                    </form>
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