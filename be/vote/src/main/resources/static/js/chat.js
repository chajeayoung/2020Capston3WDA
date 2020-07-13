$(document).ready(function() {
    const socket = io("http://localhost:4000");
    if($('#username').text()==''){
        $("#chatinput").attr("disabled",true);
        $("#chatinput").attr('placeholder', '로그인이 필요합니다.' );
    } else {
        $(function() {
            $("#chatinput").attr("disabled",false);
            $("#chatinput").attr('placeholder', '내용을 입력하세요.' );
            $('#chatform').submit(function(e) {
                e.preventDefault(); // prevents page reloading
                socket.emit('chat message', $("#username").text() + " : " +  $('#chatinput').val());
                $('#chatinput').val('');
                return false;
            });
            socket.on('chat message', function(msg) {
                var username = $("#username").text();
                var url = msg;
                const num = url.split(' : ');
                var param = num[num.length-2];
    
                if(username==param)
                $('#messages').append($('<li id=user>').text(msg));
                else
                $('#messages').append($('<li>').text(msg));
                $("#messages").scrollTop($("#messages")[0].scrollHeight);
    
            });
        });
    }   
    
    $(function() {
        $(".c_h").click(function(e) {
            if ($(".chat_container").is(":visible")) {
                $(".c_h .right_c .mini").text("+")
                $(".l_c_h").css('width', 'auto') 
            } else {
                $(".c_h .right_c .mini").text("-")
                $(".l_c_h").css('width', '300px')          
            }
            $(".chat_container").slideToggle("slow");
            return false
        });
    });
});