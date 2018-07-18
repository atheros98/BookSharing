/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
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
            alert('eror');
        }
    });
});

function setUserRate(userRate) {
    document.getElementById('rate').getElementsByTagName('option')[userRate - 1].selected = 'selected';
}

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
            alert('error');
        }
    });
}

