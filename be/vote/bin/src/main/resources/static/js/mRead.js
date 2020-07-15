$(document).ready(function () {

    var audience = {
        applyId: $('#applyId').val(),
        aRecruits: $('#aRecruits').val()
    }

    //응모인원 리스트 출력 ajax
    $('#showList').click(function () {
        $.ajax({
            url: "/audience/showList",
            type: "get",
            dataType: 'json',
            data: audience,
            success: function (data) {
                var tableData = "";
                $("#list").empty();
                $.each(data, function (key, value) {
                    tableData += '<tr>';
                    tableData += '<td>' + value.name + '</td>';
                    tableData += '<td>' + value.phone + '</td>';
                    tableData += '</tr>';
                });
                $("#list").append(tableData);
            },
            error: function (request, status, error) {
                alert("code = " + request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
            }
        });
    })

    $('#showResult').click(function () {
        if(! confirm("추첨시 추첨결과를 저장하고 재추첨할 수 없습니다. 추첨 하시겠습니까? a")){
            return ;
        }

        $.ajax({
            url: "/audience/showResult",
            type: "get",
            dataType: 'json',
            data: audience,
            success: function (data) {
                if(data.state == 1){
                    return alert(data.message);
                }
                alert(data.message);
                $("#list").empty();
                var tableData = "";
                $.each(data, function (key, value) {
                    tableData += '<tr>';
                    tableData += '<td>' + value.name + '</td>';
                    tableData += '<td>' + value.phone + '</td>';
                    tableData += '</tr>';
                });

                $("#list").append(tableData);

            },
            error: function (request, status, error) {
                alert("code = " + request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
            }
        });
    })





})


