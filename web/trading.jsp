<%-- 
    Document   : index
    Created on : Jul 11, 2018, 11:10:17 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trading - BookShare</title>
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
                    <h3>LENDING BOOK</h3>
                    <section id='lending' class="section-center">
                    <c:set var="tradings" value="${user.available}"/>
                    <c:if test="${not empty tradings}">    
                        <div class="lending-manage">
                            <h3>Available book</h3>
                            <c:forEach var="t" items="${tradings}">
                                ${t.book.title}
                            </c:forEach>
                        </div>
                    </c:if>

                </section>
                <br/>
                <hr>
                <h3>BORROWING BOOK</h3>
                <section id='borrowing' class="section-center">
                    <c:set var="borrows" value="${user.borrowing}"/>

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

        <script>
            var lendings = document.getElementsByClassName("lending-manage");
            var borrowings = document.getElementsByClassName("borrowing-manage");
            if (lendings.length === 0) {
                document.getElementById("lending").innerHTML =
                        '<div class="not-found-book">Empty</div>';
            }
            if (borrowings.length === 0) {
                document.getElementById("borrowing").innerHTML =
                        '<div class="not-found-book">Empty</div>';
            }
        </script>
    </body>
</html>
