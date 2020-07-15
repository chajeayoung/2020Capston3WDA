$(document).ready(function(){
    console.log("aaa")
    $('#apply').click(function(e){  
        console.log("aaa")
        var id = $("#auditionId").val();

        location.href = "/audition_con/form/"+id

    })
       
})