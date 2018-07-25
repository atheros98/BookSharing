
function clear() {
    var end = document.getElementById("people-table--nav");
    if (end !== null) {
        end.remove();
    }

    var count = 0;
    var table = document.getElementById('people-table');
    while (table.rows.length > 1)
    {
        if (count > 1)
            table.deleteRow(1);
        count++;
    }
}

function loadData(api, status) {
    console.log(api + "  asfd: " + status);
    $.ajax({
        url: api,
        data: {"type": status},
        dataType: 'json',
        type: 'get',
        success: function (data) {
            var tradings = [];
            var count = 1;
            console.log(data);
            data.forEach(function (element) {
                var item = {
                    "title": element.title,
                    "createDate": element.createDate,
                    "userID": element.userID,
                    "username": element.username,
                    "status": element.status,
                    "id": element.id
                };
                console.log(element.title);
                tradings.push(item);
                count++;
            });
            console.log(data);
            clear();
            $('#people-table').smpSortableTable(api, tradings, 10);
        },
        error: function () {
            alert('error');
        }
    });
}

// function do event select status
function getSelectStatus() {
    var status = document.getElementById("status-select");
    var status_select = status.options[status.selectedIndex].value;
    loadData(document.getElementById("typeTrading").value, status_select);
}

// load data for select
function loadSelect(type) {
    var lending = ['Available', 'Pending', 'Lending', 'Complete'];
    var borrowing = ['Pending', 'Borrowing', 'Complete'];
    var arr = [], dele = [];

    arr = type === 'LendingController' ? lending : borrowing;

    var select = document.getElementById("status-select");

    lending.forEach(function (item) {
        var val = "#status-select option[value='" + item + "']";
        $(val).remove();
    });
    borrowing.forEach(function (item) {
        var val = "#status-select option[value='" + item + "']";
        $(val).remove();
    });

    arr.forEach(function (item) {
        var option = document.createElement("option");
        option.text = item;
        option.value = item;
        select.add(option);
    });
}

//===================================================== Do action =====================================================

$(document).on('click', '#action a', function () {
    event.preventDefault(); // set form do not reload when submit
    console.log($(this).attr("data-idtrading"));
    doAction($(this).attr("data-action"), $(this).attr("data-idtrading"));
});
function doAction(method, idTrading) {
    console.log(idTrading);
    $.ajax({
        url: 'TradingController',
        type: 'post',
        data: {
            method: method,
            idTrading: idTrading
        },
        success: function (data) {
            if (data === "true") {
                getSelectStatus();
            }
        },
        error: function () {
            alert('error');
        }
    });
}

function changeManage(type) {
    if (type === 'lending') {
        document.getElementById("people-table").rows[0].cells[3].innerHTML = "Borrower";
        document.getElementById("title-trading").innerHTML = "Manage user's lending";
        document.getElementById("typeTrading").value = "LendingController";
        document.getElementById("lending").className = "select-items";
        document.getElementById("borrowing").className = "";
        loadData("LendingController", "Available");
        loadSelect("LendingController");
    } else {
        document.getElementById("people-table").rows[0].cells[3].innerHTML = "Lender";
        document.getElementById("title-trading").innerHTML = "Manage user's borrowing";
        document.getElementById("typeTrading").value = "BorrowingController";
        document.getElementById("borrowing").className = "select-items";
        document.getElementById("lending").className = "";
        loadData("BorrowingController", "Pending");
        loadSelect("BorrowingController");
    }
}
//=======================================================================================================================

$(document).ready(function () {
    loadData("LendingController", "Available");
    loadSelect("LendingController");
});

