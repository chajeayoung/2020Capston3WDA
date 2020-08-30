$(document).ready(function () {

    var audience = {
        applyId: $('#applyId').val(),
        aRecruits: $('#aRecruits').val(),
        result: $('#result').val(),
    }

    var now = new Date().getTime();
    var sdate = new Date($("#aStartdate").val()).getTime();
    var edate = new Date($("#aEnddate").val()).getTime();
    if (now < sdate) {
        $("#showList").prop('disabled', true);
        $("#showResult").prop('disabled', true);
        $("#result__title").text("응모전입니다! 응모 시작 전 까지 수정·삭제가 가능합니다.");

    } else if (now >= sdate && now <= edate) {
        $("#showList").prop('disabled', false);
        $("#showResult").prop('disabled', true);
        $("#delete-btn").prop('disabled', true);
        $("#update-btn").prop('disabled', true);
        $("#result__title").text("응모중 입니다. 응모 종료시 추첨이 가능합니다.");

    } else if (now > edate) {
        $("#showList").prop('disabled', false);
        $("#showResult").prop('disabled', false);
        $("#delete-btn").prop('disabled', true);
        $("#update-btn").prop('disabled', true);
        if ($("#result").val() == '0') {
            $("#result__title").text("응모마감 되었습니다. 추첨을 해주세요.");
            $("#showResult").val("추첨하기");
        } else if ($("#result").val() == '1') {
            $("#result__title").text("추첨이 완료되었습니다. 추첨된인원을 확인 해주세요.");
            $("#showResult").prop('disabled', true);
            $("#showList").prop('disabled', true);
            $("#showResult").val("추첨인원보기");
        }

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
                $("#applylist").empty();
                tableData += "<tr><th>이름</th><th>전화번호</th></tr>";

                $.each(data, function (key, value) {
                    tableData += '<tr>';
                    tableData += '<td>' + value.name + '</td>';
                    tableData += '<td>' + value.phone + '</td>';
                    tableData += '</tr>';
                });
                $("#applylist").append(tableData);
            },
            error: function (request, status, error) {
                alert("code = " + request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
            }
        });
    })

    $('#showResult').click(function () {

        swal({
            title: "추첨하시겠습니까?",
            text: "추첨 시 재추첨 하실 수 없습니다.",
            icon: "warning",
            buttons: true,
            dangerMode: true,
          }).then((willDelete) => {
            if(willDelete){
                $.ajax({
                    url: "/audience/showResult",
                    type: "get",
                    dataType: 'json',
                    data: audience,
                    success: function (data) {
                        $("#result__title").text("추첨이 완료되었습니다. 추첨된인원을 확인 해주세요.");
                        $("#showResult").val("추첨인원보기");
                        if (data.state == 1) {
                            return swal(data.message, "", "success");
                        }
                        swal(data.message, "", "success");
                        $("#resultlist").empty();
                        var tableData = "";
                        console.log(data.list);
                        $("#showResult").prop('disabled', true);
                        $("#showList").prop('disabled', true);
                        tableData += "<tr><th>이름</th><th>전화번호</th></tr>";
                        $.each(data.list, function (key, value) {
                            tableData += '<tr>';
                            tableData += '<td>' + value.name + '</td>';
                            tableData += '<td>' + value.phone + '</td>';
                            tableData += '</tr>';
                        });
        
                        $("#resultlist").append(tableData);
        
                    },
                    error: function (request, status, error) {
                        alert("code = " + request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
                    }
                });
            } else {
                swal("취소하셨습니다.");
            }
          });


        // if(! confirm("추첨시 추첨결과를 저장하고 재추첨할 수 없습니다. 추첨 하시겠습니까? ")){
        //     return ;
        // }

        
    })





})


