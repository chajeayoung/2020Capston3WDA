	   window.onload = function(){
		

		var newSS=document.createElement('link');
		newSS.rel='stylesheet';
		newSS.href='/css/style.css'
		document.getElementsByTagName("head")[0].appendChild(newSS);
		var userRole = $("#userRole").text();
		
		//사이드바 필요할시 스타일활성해주는 것
		 $(".sideon").attr('class','glyphicon glyphicon-menu-hamburger');
		 $(".btn2").attr('class','btn'); 
	
		
		//회원관리	
		$("#side").append("<img src='/uploads/images.jpg'></img>");
		$("#side").append("<li><a href='/userInfo/allCompany'>서비스신청관리</a></li>"); 
		
		
	   
		    
	}

