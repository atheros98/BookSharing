/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getReviewByTop(document.getElementById("idBook").value, 10);
});

function getReviewByTop(idBook, top) {
    // delete all current review
    var main = document.getElementById("content-article");
    while (main.hasChildNodes()) {
        main.removeChild(main.lastChild);
    }
    // get data review
    $.ajax({
        type: "GET",
        url: "ReviewBookController",
        data: {idBook: idBook, top: top},
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            var main = document.getElementById("content-article");
            for (var i in result) {
                var article = document.createElement("article");
                var a = document.createElement("a");
                a.href = "ProfileController?idUser=" + result[i].idUser;
                a.innerHTML = result[i].username;
                var p = document.createElement("p");
                p.innerHTML = result[i].createDate;
                var textarea = document.createElement("textarea");
                textarea.readonly = true;
                textarea.value = result[i].content;
                article.appendChild(a);
                article.appendChild(p);
                article.appendChild(textarea);
                main.appendChild(article);
            }
        },
        error: function (response) {
            alert('eror');
        }
    });
}

function getSelectTop() {
    var value = document.getElementById("select-top");
    getReviewByTop(document.getElementById("idBook").value, value.options[value.selectedIndex].value);
}

document.getElementById("sendReivew").addEventListener("submit", function (event) {
    event.preventDefault(); // set form do not reload when submit
    $.ajax({
        url: 'ReviewBookController',
        type: 'post',
        data: {
            idBook: document.getElementById("idBook").value,
            content: document.getElementById("content-review").value
        },
        success: function (data) {
            if (data === "true") {
                document.getElementById("content-review").value = "";
                getSelectTop();
                setTimeout(function () {
                    $('html, body').animate({
                        scrollTop: $("div.scrollhere").offset().top
                    }, 1000);
                }, 100);
            }
        },
        error: function () {
            alert('error');
        }
    });



});
