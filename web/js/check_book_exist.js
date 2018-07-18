/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global value */

document.getElementById("isbn").addEventListener("keyup", function () {
    $.ajax({
        url: 'CheckBookExist',
        data: {ISBN: this.value},
        type: 'get',
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function () {
            alert('error');
        }
    });
});
