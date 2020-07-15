$(document).ready(function(){
    
    $('#addOption').click(function(e){
        var div = $("#optionDiv");
        var option = $(document.createElement("div"));

        var title = $(document.createElement("input"));
        var button = $(document.createElement("button"));

        button.attr("type","button");
        button.html("X");
        button.on("click",function(e){
            var parent = $(e.target.parentElement)
            parent.empty();
        })

        title.attr("name","option");
        title.attr("required","true");
        title.attr("placeholder","옵션명")

        option.append(title);
        option.append(button);

        div.append(option);
        

    })
       
})