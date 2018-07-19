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
                    <c:forEach items="${tradings}" var="t">
                        <div class="new-book">
                            <div class="section-center-header">
                                <div class="user">
                                    <a href="#">
                                        <img src="${t.user.avatar}">
                                        <p>${t.user.fullName}</p>
                                    </a>
                                </div>
                                <div class="time">
                                    ${t.createDate}
                                </div>
                            </div>
                            <div class="book-item">
                                <c:url var="BookDetail" value="BookDetailController">
                                    <c:param name="id" value="${t.idBook}"/>
                                </c:url> 
                                <a href="${BookDetail}" class="book-link">
                                    <div class="img">
                                        <img src="${t.book.image}" alt=""/>
                                    </div>
                                    <div class="title">${t.book.title}</div>
                                    <div class="author">${t.book.author}</div>
                                    <div class="description">${t.book.description}</div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </section>
                <div class="page">
                    <c:if test="${pages > 1}">
                        <c:forEach var="p" begin="1" end="${pages}" step="1">
                            <c:url var="HomeByPage" value="HomeController">
                                <c:param name="page" value="${p}"/>
                            </c:url> 
                            <c:if test="${(param.page == null && p == 1) 
                                          || (param.page == p)}">
                                  <span class="selected-page">${p}</span>
                            </c:if>
                            <c:if test="${(param.page == null && p != 1) 
                                          || param.page != p && param.page != null}">
                                  <a class="next-page" href="${HomeByPage}">${p}</a>
                            </c:if>

                        </c:forEach>
                    </c:if>
                </div>
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
