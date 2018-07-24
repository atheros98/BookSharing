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
        <script src="js/smpSortableTable.js"></script>
        <script type="text/javascript">
            const people = {
                data: [{
                        "title": "Tanner",
                        "avatar": "img/admin.jpg",
                        "createdate": {"text": "04\/09\/1925", "sort": -1411540671},
                        "statusBook": 1,
                        "action": 1
                    }, {
                        "title": "Adasdasd",
                        "avatar": "img/admin.jpg",
                        "createdate": {"text": "04\/11\/1925", "sort": -1411540671},
                        "statusBook": 0,
                        "action": 0
                    }, {
                        "title": "XXXXXXXXX",
                        "avatar": "img/admin.jpg",
                        "createdate": {"text": "04\/11\/1925", "sort": -1411540671},
                        "statusBook": 2,
                        "action": 2
                    }, {
                        "title": "Adas234234dasd",
                        "avatar": "img/admin.jpg",
                        "createdate": {"text": "04\/11\/1925", "sort": -1411540671},
                        "statusBook": 3,
                        "action": 3
                    }, {
                        "fname": "Joette",
                        "lname": "Steinert",
                        "age": 51,
                        "bdate": {"text": "12\/31\/1966", "sort": -94718271}
                    }, {
                        "fname": "Herma",
                        "lname": "Barra",
                        "age": 14,
                        "bdate": {"text": "07\/30\/2003", "sort": 1059582129}
                    }, {
                        "fname": "Alpha",
                        "lname": "Henry",
                        "age": 35,
                        "bdate": {"text": "10\/16\/1982", "sort": 403633329}
                    }, {
                        "fname": "Coleen",
                        "lname": "Smail",
                        "age": 44,
                        "bdate": {"text": "10\/09\/1973", "sort": 119031729}
                    }, {
                        "fname": "Drucilla",
                        "lname": "Abel",
                        "age": 37,
                        "bdate": {"text": "08\/25\/1980", "sort": 336068529}
                    }, {
                        "fname": "Mirian",
                        "lname": "Zeitz",
                        "age": 88,
                        "bdate": {"text": "04\/20\/1930", "sort": -1252823871}
                    }, {
                        "fname": "Adah",
                        "lname": "Weight",
                        "age": 89,
                        "bdate": {"text": "05\/11\/1929", "sort": -1282549071}
                    }, {
                        "fname": "Lucio",
                        "lname": "Palmisano",
                        "age": 50,
                        "bdate": {"text": "02\/28\/1968", "sort": -58084671}
                    }, {
                        "fname": "Harland",
                        "lname": "Burnam",
                        "age": 30,
                        "bdate": {"text": "12\/09\/1987", "sort": 566068929}
                    }, {
                        "fname": "Georgetta",
                        "lname": "Veiga",
                        "age": 70,
                        "bdate": {"text": "07\/21\/1947", "sort": -708421071}
                    }, {
                        "fname": "Andre",
                        "lname": "Heal",
                        "age": 56,
                        "bdate": {"text": "01\/13\/1962", "sort": -251361471}
                    }, {
                        "fname": "Mabelle",
                        "lname": "Condie",
                        "age": 19,
                        "bdate": {"text": "08\/10\/1998", "sort": 902766129}
                    }, {
                        "fname": "Cuc",
                        "lname": "Varney",
                        "age": 51,
                        "bdate": {"text": "01\/31\/1967", "sort": -92039871}
                    }, {
                        "fname": "Edith",
                        "lname": "Daulton",
                        "age": 61,
                        "bdate": {"text": "09\/10\/1956", "sort": -419931471}
                    }, {
                        "fname": "Angele",
                        "lname": "Ledonne",
                        "age": 38,
                        "bdate": {"text": "09\/03\/1979", "sort": 305223729}
                    }, {
                        "fname": "Melony",
                        "lname": "Stender",
                        "age": 87,
                        "bdate": {"text": "01\/22\/1931", "sort": -1228891071}
                    }, {
                        "fname": "Tosha",
                        "lname": "Sasaki",
                        "age": 31,
                        "bdate": {"text": "03\/14\/1987", "sort": 542740929}
                    }, {
                        "fname": "Darcy",
                        "lname": "Ikard",
                        "age": 97,
                        "bdate": {"text": "02\/01\/1921", "sort": -1543559871}
                    }, {
                        "fname": "Oren",
                        "lname": "Jone",
                        "age": 36,
                        "bdate": {"text": "04\/17\/1982", "sort": 387912129}
                    }, {
                        "fname": "Bill",
                        "lname": "Dines",
                        "age": 20,
                        "bdate": {"text": "11\/25\/1997", "sort": 880478529}
                    }, {
                        "fname": "Lovie",
                        "lname": "Wengerd",
                        "age": 19,
                        "bdate": {"text": "01\/02\/1999", "sort": 915297729}
                    }, {
                        "fname": "Bertram",
                        "lname": "Bland",
                        "age": 87,
                        "bdate": {"text": "04\/08\/1931", "sort": -1222324671}
                    }, {
                        "fname": "Kermit",
                        "lname": "Fravel",
                        "age": 17,
                        "bdate": {"text": "06\/04\/2001", "sort": 991671729}
                    }, {
                        "fname": "Camellia",
                        "lname": "Ross",
                        "age": 69,
                        "bdate": {"text": "12\/04\/1948", "sort": -665044671}
                    }, {
                        "fname": "Gilma",
                        "lname": "Burruel",
                        "age": 33,
                        "bdate": {"text": "08\/23\/1984", "sort": 462126129}
                    }, {
                        "fname": "Maybelle",
                        "lname": "Shimek",
                        "age": 80,
                        "bdate": {"text": "12\/22\/1937", "sort": -1010644671}
                    }, {
                        "fname": "Everett",
                        "lname": "Newhouse",
                        "age": 44,
                        "bdate": {"text": "05\/18\/1974", "sort": 138126129}
                    }, {
                        "fname": "Darron",
                        "lname": "Redding",
                        "age": 49,
                        "bdate": {"text": "07\/02\/1968", "sort": -47288271}
                    }, {
                        "fname": "Cindie",
                        "lname": "Braverman",
                        "age": 66,
                        "bdate": {"text": "03\/11\/1952", "sort": -561969471}
                    }, {
                        "fname": "Ezequiel",
                        "lname": "Mukai",
                        "age": 70,
                        "bdate": {"text": "08\/26\/1947", "sort": -705310671}
                    }, {
                        "fname": "Toi",
                        "lname": "Shiver",
                        "age": 26,
                        "bdate": {"text": "11\/27\/1991", "sort": 691262529}
                    }, {
                        "fname": "Karrie",
                        "lname": "Kloss",
                        "age": 88,
                        "bdate": {"text": "12\/22\/1929", "sort": -1263105471}
                    }, {
                        "fname": "Suzie",
                        "lname": "Sartor",
                        "age": 73,
                        "bdate": {"text": "10\/18\/1944", "sort": -795339471}
                    }, {
                        "fname": "Wilbur",
                        "lname": "Brashear",
                        "age": 92,
                        "bdate": {"text": "05\/16\/1926", "sort": -1376811471}
                    }, {
                        "fname": "Clotilde",
                        "lname": "Eliason",
                        "age": 95,
                        "bdate": {"text": "04\/24\/1923", "sort": -1473403071}
                    }, {
                        "fname": "Jennine",
                        "lname": "Digirolamo",
                        "age": 93,
                        "bdate": {"text": "03\/04\/1925", "sort": -1414651071}
                    }, {
                        "fname": "Tamera",
                        "lname": "Null",
                        "age": 53,
                        "bdate": {"text": "05\/29\/1965", "sort": -144920271}
                    }, {
                        "fname": "Theressa",
                        "lname": "Tea",
                        "age": 25,
                        "bdate": {"text": "12\/31\/1992", "sort": 725822529}
                    }, {
                        "fname": "Cherryl",
                        "lname": "Sweitzer",
                        "age": 13,
                        "bdate": {"text": "11\/14\/2004", "sort": 1100452929}
                    }, {
                        "fname": "Nakisha",
                        "lname": "Reppert",
                        "age": 95,
                        "bdate": {"text": "04\/02\/1923", "sort": -1475303871}
                    }, {
                        "fname": "Laurence",
                        "lname": "Pipkins",
                        "age": 25,
                        "bdate": {"text": "09\/08\/1992", "sort": 715969329}
                    }, {
                        "fname": "Sherwood",
                        "lname": "Mcdowell",
                        "age": 32,
                        "bdate": {"text": "12\/16\/1985", "sort": 503601729}
                    }, {
                        "fname": "Yvone",
                        "lname": "Gullett",
                        "age": 51,
                        "bdate": {"text": "06\/24\/1966", "sort": -111137871}
                    }, {
                        "fname": "Jenni",
                        "lname": "Silva",
                        "age": 74,
                        "bdate": {"text": "10\/30\/1943", "sort": -825925071}
                    }, {
                        "fname": "Del",
                        "lname": "Febus",
                        "age": 42,
                        "bdate": {"text": "04\/18\/1976", "sort": 198696129}
                    }, {
                        "fname": "Leisa",
                        "lname": "Ferber",
                        "age": 50,
                        "bdate": {"text": "07\/02\/1967", "sort": -78910671}
                    }]
            };
            $(document).ready(function () {
                $('#people-table').smpSortableTable(people.data, 10);
            });
        </script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="content">
            <jsp:include page="content-left.jsp"></jsp:include>
                <!--Đây là đoạn code nội dung cho page-->
                <div class="content-center">
                    <h3>Manage user's trading</h3>
                    <table id="people-table">
                        <thead>
                            <tr>
                                <th id="title">Title</th>
                                <th id="coverbook">Cover book</th>
                                <th id="createdate">Create Date</th>
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