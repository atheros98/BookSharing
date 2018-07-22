<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : details_book
    Created on : Jul 18, 2018, 12:35:46 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/upload_book.css" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt"
              crossorigin="anonymous">
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="content">
            <jsp:include page="content-left.jsp"></jsp:include>
                <!--Đây là đoạn code nội dung cho page-->
                <div class="content-center">
                    <div class="title-upload">
                        <i class="fas fa-user"><b><a href="#">${userOwner.getUserName()}</a></b></i>
                    <p>${tradingdate}</p>
                </div>
                <h2>${book.getTitle()}</h2>
                <div class="borrow-book">
                    <div class="rate">
                        <select id="rate" onchange="getSelectRate()">
                            <option value="1">1 Star</option>
                            <option value="2">2 Star</option>
                            <option value="3">3 Star</option>
                            <option value="4">4 Star</option>
                            <option value="5">5 Star</option>
                        </select>
                        <div id="star">
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                        </div>
                    </div>
                    <a href="#">Borrow</a>
                </div>
                <div class="title-upload" style="background: #F9F7F4;">
                    <i class="fas fa-info"><b>Information of lender</b></i>
                    <div style="clear: both;"></div>
                    <div class="info-book">
                        <div class="elements">
                            <div class="title"><i class="fas fa-envelope"></i>Email</div>
                            <div class="input">
                                <input type="email" name="email" value="${userOwner.getEmail()}" readonly="">
                            </div>
                        </div>
                        <div class="elements">
                            <div class="title"><i class="fas fa-address-card"></i>Address</div>
                            <div class="input">
                                <input type="text" value="${userOwner.getAddress()}" readonly="">
                            </div>
                        </div>
                        <div class="elements">
                            <div class="title"><i class="fas fa-phone-square"></i>Phone number</div>
                            <div class="input">
                                <input type="text" value="${userOwner.getPhoneNumber()}" readonly="">
                            </div>
                        </div>
                        <div class="elements">
                            <div class="title"><i class="fab fa-facebook"></i>Facebook</div>
                            <div class="input">
                                <input type="text" value="${userOwner.getLinkFacebook()}" readonly="">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cover-book">
                    <c:forEach var="cover" items="${coverBooks}">
                        <img src="<%=request.getContextPath()%>${cover.getUrlImage()}" alt="${cover}">
                    </c:forEach>
                </div>
                <div class="info-book">
                    <div class="scrollhere"></div>
                    <input type="hidden" id="idBook" value="${book.getId()}">
                    <div class="elements">
                        <div class="title"><i class="fas fa-barcode"></i>ISBN</div>
                        <div class="input"><input type="number" value="${book.getiSBN()}"  readonly="true"></div>
                    </div>
                    <div class="elements">
                        <div class="title"><i class="fas fa-file-signature"></i>Title</div>
                        <div class="input">
                            <input id="title" type="text" value="${book.getTitle()}" readonly="true">
                        </div>
                    </div>
                    <div class="elements">
                        <div class="title"><i class="fas fa-user-tie"></i>Author</div>
                        <div class="input">
                            <input id="author" type="text" value="${book.getAuthor()}" readonly="true">
                        </div>
                    </div>
                    <div class="elements">
                        <div class="title"><i class="fas fa-tags"></i>Tag</div>
                        <div class="input">
                            <input id="tag" type="text" value="${book.getTag()}" readonly="true">
                        </div>
                    </div>
                    <div class="elements">
                        <div class="title"><i class="fas fa-language"></i>Language</div>
                        <div class="input">
                            <input id="language" type="text" value="${book.getLanguage()}" readonly="true">
                        </div>
                    </div>
                    <div class="elements">
                        <div class="title"><i class="fas fa-pen"></i>Description</div>
                        <div class="input">
                            <textarea id="description" readonly="true" style="border: none;">${book.getDescription()}</textarea>
                        </div>
                    </div>
                </div>
                <div class="review-book">
                    <select id="select-top" onchange="getSelectTop()">
                        <option value="10">Top 10 reviews</option>
                        <option value="20">Top 20 reviews</option>
                        <option value="30">Top 30 reviews</option>
                        <option value="40">Top 40 reviews</option>
                    </select>
                    <div class="content-review">
                        <div id="content-article"></div>
                        <div class="title-upload">
                            <i class="fas fa-user"><b><a href="#">${username}</a></b></i>
                            <p>${currentDate}</p>
                            <form id="sendReivew">
                                <textarea id="content-review" placeholder="Write reivew here..." required=""></textarea>
                                <input type="submit" value="Send - Click here">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!--Kết thúc-->
            <jsp:include page="content-right.jsp"></jsp:include>
        </div>
        <script src="js/RateReviewBook.js"></script>
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
