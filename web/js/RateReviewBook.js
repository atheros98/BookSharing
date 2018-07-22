/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// set default when load page
document.onreadystatechange = () => {
    if (document.readyState === 'complete') {
        
        // set rate default of book
        var idBook = document.getElementById("idBook").value;
        $.ajax({
            type: "GET",
            url: "RateBookController",
            data: {idBook: idBook},
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            success: function (result) {
                setUserRate(result.userRate);
                setTotalRate(result.totalRate);
            },
            error: function () {
                console.log("set user rate");
                alert('eror');
            }
        });
        
        // set top 10 review
        getReviewByTop(idBook, 10);
    }
};

//==================================================  FOR RATE ==================================================

// function set value rate dropdown (rate of user)
function setUserRate(userRate) {
    document.getElementById('rate').getElementsByTagName('option')[userRate - 1].selected = 'selected';
}

// function set total value rate (rate of all user)
function setTotalRate(totalRate) {
    var main = document.getElementById("star");
    while (main.hasChildNodes()) {
        main.removeChild(main.lastChild);
    }

    // set star
    var count = 0;
    for (var i = 0; i < 5; i++) {
        var span = document.createElement("span");
        if (count < totalRate) {
            span.className = "fa fa-star checked";
        } else {
            span.className = "fa fa-star";
        }
        main.appendChild(span);
        count++;
    }
}

//  event refresh total rate of all user when current user change your rate
function getSelectRate() {
    // set now star
    var rate = document.getElementById("rate");
    var userRate = rate.options[rate.selectedIndex].value;
//    setTotalRate(rate.options[rate.selectedIndex].value);

    $.ajax({
        url: 'RateBookController',
        type: 'post',
        data: {
            idBook: document.getElementById("idBook").value,
            rate: userRate
        },
        success: function (data) {
            console.log(data);
            if (data !== "false") {
                setTotalRate(data);
            }
        },
        error: function () {
            console.log("get select rate");
            alert('error');
        }
    });
}

//==================================================  FOR REVIEW ==================================================
// function get top review with param
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

// event change when user select top review
function getSelectTop() {
    var value = document.getElementById("select-top");
    getReviewByTop(document.getElementById("idBook").value, value.options[value.selectedIndex].value);
}

// event user send review
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
