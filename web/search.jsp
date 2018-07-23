<%-- 
    Document   : Search
    Created on : Jul 18, 2018, 6:05:38 PM
    Author     : Atheros
--%>

<%@page import="com.entity.Book"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
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
                <c:if test="${empty param.query}">
                    <meta http-equiv="refresh" content="0;url=.">
                </c:if>
                <h3>Result for "${param.query}":</h3>
                <div class="search-nav">
                    <span>Find by: </span>
                    <ul class="type-list">
                        <% List<String> list = Arrays.asList("All", "Title", "Author", "ISBN", "Tag");%>
                        <c:forEach var="l" items="<%=list%>">
                            <li>
                                <c:url var="SearchByFilter" value="SearchBookController">
                                    <c:param name="query" value="${param.query}"/>
                                    <c:param name="filter" value="${l}"/>
                                </c:url> 
                                <c:if test='${(param.filter == null && l == "All")
                                              || (param.filter == l)}'>
                                      <a class="seleted-type" href="${SearchByFilter}">${l}</a>
                                </c:if>
                                <c:if test='${(param.filter == null && l != "All")
                                              ||(param.filter != null && param.filter != l)}'>
                                      <a class="unseleted-type" href="${SearchByFilter}">${l}</a>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <section class="section-center">
                    <c:forEach items="${books}" var="b">
                        <c:url var="BookDetail" value="DetailsBookController">
                            <c:param name="id" value="${b.id}"/>
                        </c:url> 
                        <div class="book-item" id="search">
                            <a href="${BookDetail}" class="book-link">
                                <div class="img">
                                    <img src="${b.image}" alt=""/>
                                </div>
                                <div class="book-content">
                                    <h2>${b.title}</h2>
                                    <author>${b.author}</author>
                                    <p>${b.description}</p>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                    <c:if test="${empty books}">
                        <div class="not-found-book">
                            Book not found
                        </div>
                    </c:if>
                </section>
                <div class="page">
                    <c:if test="${pages > 1}">
                        <c:forEach var="p" begin="1" end="${pages}" step="1">
                            <c:url var="SearchByPage" value="SearchBookController">
                                <c:param name="query" value="${param.query}"/>
                                <c:if test="${param.filter != null}">
                                    <c:param name="filter" value="${l}"/>
                                </c:if>
                                <c:param name="page" value="${p}"/>
                            </c:url> 
                            <c:if test="${(param.page == null && p == 1) 
                                          || (param.page == p)}">
                                  <span class="selected-page">${p}</span>
                            </c:if>
                            <c:if test="${(param.page == null && p != 1) 
                                          || param.page != p && param.page != null}">
                                  <a class="next-page" href="${SearchByPage}">${p}</a>
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
