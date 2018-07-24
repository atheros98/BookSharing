<%-- 
    Document   : manage_book
    Created on : Jul 23, 2018, 12:37:31 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>

        <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
        <link rel="stylesheet" href="css/smpSortableTable.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="js/smpSortableTable.js"></script>
        <script src="js/loadData.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="content">
            <jsp:include page="content-left.jsp"></jsp:include>
                <!--Đây là đoạn code nội dung cho page-->
                <div class="content-center">
                    <input id="typeTrading" type="hidden" value="LendingController">
                    <div class="trading">
                        <ul>
                            <li>
                                <a id="lending" onclick="changeManage('lending')" href="#">Lending</a>
                            </li>
                            <li>
                                <a id="borrowing" onclick="changeManage('borrowing')" href="#">Borrowing</a>
                            </li>
                        </ul>
                    </div>
                    <h3 id="title-trading">Manage user's lending</h3>
                    <select id="status-select" onchange="getSelectStatus()">

                    </select>
                    <table id="people-table">
                        <thead>
                            <tr>
                                <th id="no">No</th>
                                <th id="title">Title</th>
                                <th id="createdate">Create Date</th>
                                <th id="username">User borrow</th>
                                <th id="status">Status</th>
                                <th id="action">Action</th>
                            </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
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