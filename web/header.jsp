<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : header
    Created on : Jul 11, 2018, 11:11:54 PM
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="cover">
    <img src="<%=request.getContextPath()%>/img/cover.jpg"/>
</div>
<div class="header" id="myheader">
    <div class="main-header">
        <div class="logo">
            <a href="HomeController">
            <b>Book</b>Share
            </a>
        </div> 
        <div class="menu">
            <ul>
                <li>
                    <b><a href="HomeController">Home</a></b>
                </li>
                <li>
                    <b><a href="upload_book.jsp">Upload Book</a></b>
                </li>
                <li>
                    <b><a href="trading.jsp">Trading</a></b>
                </li>
            </ul>
        </div>
        <div class="search">
            <div class="search-container">
                <form action="SearchBookController" method="POST">
                    <input type="text" placeholder="Search.." name="query" autocomplete="off" value="${param.query}">
                    <button type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
            </div>
        </div>
        <div class="user-area">
            <c:if test="${username == null}">
                <b><a class="login" href="LoginController">Login</a></b>
                <b><a class="login" href="register.jsp">Register</a></b>
            </c:if>
            <c:if test="${username != null}">
                <div class="dropdown">
                    <a class="login" href="UpdateProfileController">               
                        <i class="fas fa-user"> ${username}</i>
                    </a>
                    <div class="dropdown-content">
                        <a href="UpdateProfileController">Profile</a>
                        <a href="changepassword.jsp">Change password</a>
                        <a href="LogoutController">Logout</a>
                    </div>
                </div>
            </c:if>
        </div>

    </div>
</div>
