<%-- 
    Document   : index
    Created on : Jul 11, 2018, 11:10:17 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt"
              crossorigin="anonymous">

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="content">
            <jsp:include page="content-left.jsp"></jsp:include>
                <!--Đây là đoạn code nội dung cho page-->
                <div class="content-center">
                    <h3>NEWEST BOOK UPLOAD</h3>
                    <section class="section-center">
                        <div class="section-center-header">
                            <div class="user">
                                <a href="#">
                                    <img src="img/admin.jpg" alt="admin">
                                    <p>admin</p>
                                </a>
                            </div>
                            <div class="time">
                                11/07/2018
                            </div>
                        </div>
                    </section>
                </div>
                <!--Kết thúc-->
            <jsp:include page="content-right.jsp"></jsp:include>
        </div>
        <script>
            window.onscroll = function () {
                myFunction();
            };

            var header = document.getElementById("myheader");
            var sticky = header.offsetTop;

            function myFunction() {
                if (window.pageYOffset > sticky) {
                    header.classList.add("sticky");
                } else {
                    header.classList.remove("sticky");
                }
            }
        </script>
    </body>
</html>
