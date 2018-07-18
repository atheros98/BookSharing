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
                        <i class="fas fa-user"><b><a href="#">jmthuong97</a></b></i>
                        <p>18/07/2018</p>
                    </div>
                    <h2>Dac Nhan Tam</h2>
                    <div class="borrow-book">
                        <div class="rate">
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star checked"></span>
                            <span class="fa fa-star"></span>
                            <span class="fa fa-star"></span>
                        </div>
                        <a href="#">Borrow</a>
                    </div>
                    <div class="cover-book">
                        <img src="img/dac-nhan-tam.png" alt="cover1">
                        <img src="img/empty.jpg" alt="cover2">
                        <img src="img/empty.jpg" alt="cover3">
                        <img src="img/empty.jpg" alt="cover4">
                        <img src="img/empty.jpg" alt="cover5">
                    </div>
                    <div class="info-book">
                        <div class="elements">
                            <div class="title"><i class="fas fa-barcode"></i>ISBN</div>
                            <div class="input"><input id="isbn" type="number" name="isbn" placeholder="ISBN Book" required=""></div>
                        </div>
                        <div class="elements">
                            <div class="title"><i class="fas fa-file-signature"></i>Title</div>
                            <div class="input">
                                <input id="title" type="text" name="title-book" placeholder="Title Book" required="">
                            </div>
                        </div>
                        <div class="elements">
                            <div class="title"><i class="fas fa-user-tie"></i>Author</div>
                            <div class="input">
                                <input id="author" type="text" name="author" placeholder="Author Book" required="">
                            </div>
                        </div>
                        <div class="elements">
                            <div class="title"><i class="fas fa-tags"></i>Tag</div>
                            <div class="input">
                                <input id="tag" type="text" name="tag" placeholder="Tag Book" required="">
                            </div>
                        </div>
                        <div class="elements">
                            <div class="title"><i class="fas fa-language"></i>Language</div>
                            <div class="input">
                                <input id="language" type="text" name="language" placeholder="Language Book" required="">
                            </div>
                        </div>
                        <div class="elements">
                            <div class="title"><i class="fas fa-pen"></i>Description</div>
                            <div class="input">
                                <textarea id="description" rows="6" cols="50" name="description" placeholder="Description Book" required="" style="border: none;"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="review-book">
                        <select>
                            <option value="10">Top 10 reviews</option>
                            <option value="20">Top 20 reviews</option>
                            <option value="30">Top 30 reviews</option>
                            <option value="40">Top 40 reviews</option>
                        </select>
                        <div class="content-review">
                            <article>
                                <b>nguyenchi1</b>
                                <p>18/07/2018</p>
                                <textarea name="content-review" readonly="true">asdhiashudad</textarea>
                            </article>
                            <article>
                                <b>jmthuong97</b>
                                <p>18/07/2018</p>
                                <textarea name="content-review" readonly="true">hayasdopj aspdoj adojsadpasdoasdosado</textarea>
                            </article>
                            <article>
                                <b>dungdt</b>
                                <p>18/07/2018</p>
                                <textarea name="content-review" readonly="true">x</textarea>
                            </article>
                            <div class="title-upload">
                                <i class="fas fa-user"><b><a href="#">jmthuong97</a></b></i>
                                <p>18/07/2018</p>
                                <form id="sendReivew">
                                    <textarea name="content-review" placeholder="Write reivew here..." required=""></textarea>
                                    <input type="submit" value="Send - Click here">
                                </form>
                            </div>
                        </div>
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
