




function msg_time() {


    var date = $("#aEnddate").val();
    var rdate = date.substr(0, 19);
    var stDate = new Date().getTime();
    var edDate = new Date(rdate).getTime(); // 종료날짜
    var RemainDate = edDate - stDate; //
    var days = Math.floor(RemainDate / (1000 * 60 * 60 * 24));
    var hours = Math.floor((RemainDate % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var miniutes = Math.floor((RemainDate % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((RemainDate % (1000 * 60)) / 1000);
    document.getElementById('days').innerText = days,
        document.getElementById('hours').innerText = hours,
        document.getElementById('minutes').innerText = miniutes,
        document.getElementById('seconds').innerText = seconds;
    // document.all.timer.innerHTML = m; // div 영역에 보여줌

    if (RemainDate < 0) {
        // 시간이 종료 되었으면..
        clearInterval(tid); // 타이머 해제
        
        $("#timer__title").text("응모마감 되었습니다.");
        $("#timer__ul").hide();
    } else {
        RemainDate = RemainDate - 1000; // 남은시간 -1초
    }
}


$(document).ready(function () {
	
	
    tid = setInterval('msg_time()', 1000); // 타이머 1초간격으로 수행
    var $modalContainer = $('#modal-container'),
        $body = $('body'),
        $content = $('.content');
    var aResult = $('#aResult').val();
    var audience = {
        applyId: $('#applyId').val(),
        aLimit: $('#aLimit').val(),
        aPrice: $('#aPrice').val()
    }
    var now = new Date().getTime();
    var sdate = new Date($("#aStartdate").val()).getTime();
    var edate = new Date($("#aEnddate").val()).getTime();
    if(now<sdate){
    	$("#apply").prop('disabled', true);
    	$("#cofirm").prop('disabled', true);
    	$("#timer__title").text("응모전입니다!");
    	$("#timer__ul").hide();
    } else if(now>=sdate && now<=edate){
    	$("#apply").prop('disabled', false);
    	$("#cofirm").prop('disabled', true);
    	$("#timer__title").text("응모종료까지 남은 시간");
    	$("#timer__ul").show();
    } else if(now>edate){
    	$("#apply").prop('disabled', true);
    	$("#cofirm").prop('disabled', false);
    	$("#timer__title").text("응모마감 되었습니다!");
    	$("#timer__ul").hide();
    }
    
    $('#apply').click(function () {
        $.ajax({
            url: `/audience/apply/${audience.applyId}/${audience.aLimit}/${audience.aPrice}`,
            type: "get",
            cache: "false",
            dataType: "text",
            data: $('#userRid').text(),
            success: function (data) {

                alert(data);

            },

            error: function (request, status, error) {
                console.log("error");


            }

        });
    })

    $('#confirm').click(function () {
        $.ajax({
            url: "/audience/confirm",
            type: "get",
            cache: "false",
            dataType: "text",
            data: audience,
            success: function (data) {
         
                if (data.length < 35) {
                    $(".modal__message").text(data);
                    $(".modal__img").attr("src", "/uploads/불쌍.gif");
                }

                $(".modal__message").text(data);
                $modalContainer
                    .removeAttr('class')
                    .addClass('one');
                $content
                    .removeAttr('class')
                    .addClass('content');

                $body.addClass('modal-active');



            },

            error: function (request, status, error) {
                console.log("error");


            }

        });
    })

    $modalContainer.on('click', function () {
        $(this).addClass('out');
        $body.removeClass('modal-active');
        if ($(this).hasClass('one')) {
            $content.addClass('out');
        }
    });
})


