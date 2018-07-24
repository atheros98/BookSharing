(function ($) {
    $.fn.smpSortableTable = function (jmthuong, data, max) {

        var checkStatus = function (status) {
            if (jmthuong === 'borrow') {
                switch (status) {
                    case 0:
                        return 'Borrowing';
                        break;
                    case 1:
                        return 'Confirming';
                        break;
                    case 2:
                        return 'Books borrowed';
                        break;
                    case 3:
                        return 'Finished';
                        break;
                }
            } else {
                switch (status) {
                    case 0:
                        return 'Borrowing';
                        break;
                    case 1:
                        return 'Confirming';
                        break;
                    case 2:
                        return 'Books borrowed';
                        break;
                    case 3:
                        return 'Finished';
                        break;
                }
            }
        };

        var checkAction = function (action, idTrading) {
            if (jmthuong === 'borrow') {
                switch (action) {
                    case 0:
                        return 'Borrowing';
                        break;
                    case 1:
                        return 'Confirming';
                        break;
                    case 2:
                        return 'Books borrowed';
                        break;
                    case 3:
                        return 'Finished';
                        break;
                }
            } else {
                switch (action) {
                    case 0:
                        return '<a data-action="Delete" data-idtrading="' + idTrading + '" href="#">Delete</a>';
                        break;
                    case 1:
                        return '<a data-action="Delete" data-idtrading="' + idTrading +
                                '" href="#">Cancel</a><br><a data-action="Accept" data-idtrading="' + idTrading + '" href="#">Confirm</a>';
                        break;
                    case 2:
                        return '<a data-action="Complete" data-idtrading="' + idTrading + '" href="#">Get the book back</a>';
                        break;
                    case 3:
                        return '<a data-action="rate" href="#">Rate the borrower</a>';
                        break;
                }
            }
        };

        var renderTable = function (start, end, max, data) {
            var returnHTML = '';
            for (var i = start; i < Math.min(end, max); i++) {
                returnHTML += '<tr>';
                returnHTML += '<td  id="no">' + i + '</td>';
                for (var key in data[i]) {
//                    console.log(data[i].id);
                    switch (key) {
                        case 'title':
                            returnHTML += '<td  id="title">' + (data[i][key] ? data[i][key] : 'N/A') + '</td>';
                            break;
                        case 'createDate':
                            returnHTML += '<td  id="createdate">' + (data[i][key] ? data[i][key] : 'N/A') + '</td>';
                            break;
                        case 'username':
                            if (data[i][key])
                                returnHTML += '<td  id="username"><a href="UpdateProfileController?id=' + data[i].userID + '">' + data[i][key] + '</a></td>';
                            else
                                returnHTML += '<td  id="username">N/A</td>';
                            break;
                        case 'status':
                            returnHTML += '<td  id="status">' + checkStatus(data[i][key]) + '</td>';
                            returnHTML += '<td  id="action">' + checkAction(data[i][key], data[i].id) + '</td>';
                            break;
                        default:
                            returnHTML += '';
                            break;
                    }

                }
                returnHTML += '</tr>';
            }
            return returnHTML;
        };

        var sortFns = function (key) {
            return {
                desc: function (a, b) {
                    if (typeof a[key] !== 'object')
                        return a[key] > b[key] ? -1 :
                                (a[key] < b[key] ? 1 : 0);
                    else
                        return a[key].sort > b[key].sort ? -1 :
                                (a[key].sort < b[key].sort ? 1 : 0);
                },
                asc: function (a, b) {
                    if (typeof a[key] !== 'object')
                        return a[key] < b[key] ? -1 :
                                (a[key] > b[key] ? 1 : 0);
                    else
                        return a[key].sort < b[key].sort ? -1 :
                                (a[key].sort > b[key].sort ? 1 : 0);
                }
            };
        };

        /* SETUP */
        var $table = $(this);
        var tableName = $table.attr('id');
        var index = 0;
        $table.addClass('smpSortableTable--processed');
        $table.find('tbody').html(renderTable(0, data.length, max, data));
        $table.find('th:not(.smp-not-sortable)').addClass('smpSortableTable--sortable ' + tableName + '--sortable');
        $table.after(
                '<div class="smpSortableTable--nav" id="' + tableName + '--nav">' +
                '<a class="smpSortableTable--nav-links smpSortableTable--prev smpSortableTable--disabled" id="' + tableName + '--prev">&laquo; Previous</a>' +
                '<span class="smpSortableTable--counter" id="' + tableName + '--counter"></span>' +
                '<a class="smpSortableTable--nav-links smpSortableTable--next" id="' + tableName + '--next">Next &raquo;</a>' +
                '</div>'
                );
        $.each($table.find('th'), function (i, v) {
            var id = $(v).attr('id');
            $(v).attr('id', tableName + '_' + id);
        });

        /* Init counter */
        if (data.length) {
            $('#' + tableName + '--counter').text(
                    '1 - ' + Math.min(data.length, max) + ' of ' + data.length
                    );
        } else {
            $('#' + tableName + '--counter').text('Nothing to Display');
            $('#' + tableName + '--next').addClass('smpSortableTable--disabled');
            $table.find('th').removeClass('smpSortableTable--sortable');
        }
        if (data.length <= max) {
            $('#' + tableName + '--next').addClass('smpSortableTable--disabled');
        }

        /* Init next/prev */
        if (data.length > max) {
            $('#' + tableName + '--next').click(function () {
                if (!$(this).hasClass('smpSortableTable--disabled')) {
                    var start = index += max;
                    var end = start + max;
                    var size = data.length;

                    $table.find('tbody').html(
                            renderTable(start, size, end, data)
                            );

                    $('#' + tableName + '--counter').text(
                            (start + 1) + ' - ' + Math.min(size, end) + ' of ' + size
                            );

                    $('#' + tableName + '--prev').removeClass('smpSortableTable--disabled');
                    if (end >= size) {
                        $('#' + tableName + '--next').addClass('smpSortableTable--disabled');
                    }
                }
            });
            $('#' + tableName + '--prev').click(function () {
                if (!$(this).hasClass('smpSortableTable--disabled')) {
                    var start = index -= max;
                    var end = start + max;
                    var size = data.length;

                    $table.find('tbody').html(
                            renderTable(start, size, end, data)
                            );

                    $('#' + tableName + '--counter').text(
                            (start + 1) + ' - ' + Math.min(size, end) + ' of ' + size
                            );

                    $('#' + tableName + '--next').removeClass('smpSortableTable--disabled');
                    if (!start) {
                        $('#' + tableName + '--prev').addClass('smpSortableTable--disabled');
                    }
                }
            });
        }

        /* Init sorting*/
        $('.' + tableName + '--sortable').click(function () {
            var direction = $(this).hasClass('asc') ? 'desc' : 'asc';
            var colName = $(this).attr('id').replace(tableName + '_', '');
            $('.' + tableName + '--sortable').removeClass('desc asc');
            index = 0;
            data.sort(sortFns(colName)[direction]);
            $table.find('tbody').html(
                    renderTable(0, data.length, max, data)
                    );
            $(this).addClass(direction);
            $('#' + tableName + '--prev').addClass('smpSortableTable--disabled');
            if (data.length > max) {
                $('#' + tableName + '--next').removeClass('smpSortableTable--disabled');
            }
            $('#' + tableName + '--counter').text('1 - ' + Math.min(data.length, max) + ' of ' + data.length);
        });
    };
})(jQuery);

