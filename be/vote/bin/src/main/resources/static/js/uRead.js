$(document).ready(function(){
    
    var audience = {
        applyId : $('#applyId').val(),
        aLimit : $('#aLimit').val(),
        aPrice : $('#aPrice').val()
    }
    console.log(audience);
    $('#apply').click(function(){
        $.ajax({
            url: "/audience/apply",
            type: "get",
            cache: "false",
            dataType: "text",
            data : audience,
            success: function(data){
                
                alert(data);
        
            },
        
            error: function (request, status, error){        
                console.log("error");
        
        
            }
        
          });
    })
       
    
    
    

})


