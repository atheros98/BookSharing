/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global value */

function debounce(func, wait) {
    var timeout;

    return function () {
        var context = this,
                args = arguments;

        var executeFunction = function () {
            func.apply(context, args);
        };

        clearTimeout(timeout);
        timeout = setTimeout(executeFunction, wait);
    };
}
;

function disableInput(status) {
    for (var i = 1; i <= 5; i++) {
        document.getElementById('cover' + i).disabled = status;
    }
    document.getElementById('title').readOnly = status;
    document.getElementById('author').readOnly = status;
    document.getElementById('tag').readOnly = status;
    document.getElementById('language').readOnly = status;
    document.getElementById('description').readOnly = status;
    document.getElementById('title').value = "";
    document.getElementById('author').value = "";
    document.getElementById('tag').value = "";
    document.getElementById('language').value = "";
    document.getElementById('description').value = "";
}

document.getElementById("isbn").addEventListener("input", debounce(function () {
    $.ajax({
        url: 'CheckBookExist',
        data: {isbn: document.getElementById("isbn").value},
        dataType: 'json',
        type: 'get',
        cache: false,
        success: function (data) {
            console.log(data.author);
            disableInput(true);
            if (data !== '{}') {
                document.getElementById('title').value = data.title;
                document.getElementById('author').value = data.author;
                document.getElementById('tag').value = data.tag;
                document.getElementById('language').value = data.language;
                document.getElementById('description').value = data.description;
            } else {
                disableInput(false);
            }
        },
        error: function () {
            alert('error');
        }
    });
}, 500));

document.getElementById("checkRules").addEventListener("change", function () {
    console.log(!this.checked);
    document.getElementById("uploadBtn").disabled = !this.checked;
});