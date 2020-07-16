	   window.onload = function(){
		

		var newSS=document.createElement('link');
		newSS.rel='stylesheet';
		newSS.href='/css/style.css'
		document.getElementsByTagName("head")[0].appendChild(newSS);
		var userRole = $("#userRole").text();
		
		//사이드바 필요할시 스타일활성해주는 것
		 $(".sideon").attr('class','glyphicon glyphicon-menu-hamburger');
		 $(".btn2").attr('class','btn'); 
	
		//개인정보
		 $("#side").append("<li><a href='/userInfo'>개인정보</a></li>");
			
		if(userRole=="1"){ //관리자
		//회원관리	
		$("#side").append("<li><a href='/userInfo/allUser'>회원관리</a></li>");
		$("#side").append("<li><a href='/userInfo/allCompany'>서비스신청관리</a></li>"); }
		
		
		if(userRole=="1"||userRole=="2"){ //관리자 또는 매니저
		//오디션관리
		$("#side").append("<li><a href='/audition/list'>오디션개설</a></li>");}	
		
		if(userRole=="1"||userRole=="2"||userRole=="3"){ //관리자 또는 매니저 또는 심사위원
		$("#side").append("<li><a href='/audition_con/list'>예선심사</a></li>");
		$("#side").append("<li><a href='/auditionresult/list'>심사결과</a></li>");}	
		
		if(userRole=="1"||userRole=="2"){ //관리자 또는 매니저
		//투표관리
		$("#side").append("<li><a href='/vote/create'>투표개설</a></li>");	 
		$("#side").append("<li><a href='/userInfo/manage/vote'>투표관리</a></li>");
		//커뮤니티관리
		if(userRole=="1")
		$("#side").append("<li><a href='/userInfo/AllProgram/'>프로그램관리</a></li>");	
		if(userRole=="2")
		$("#side").append("<li><a href='/userInfo/myProgram/'>프로그램관리</a></li>");	 
		$("#side").append("<li><a href='/userInfo/myCommunity/'>팬클럽관리</a></li>");	 


		$("#side").append("<li><a href='/hotclib'>핫클립관리</a></li>");	 
		$("#side").append("<li><a href='/userInfo/audience/mlist'>방청권추첨</a></li>");	 


			    
		//굿즈샵관리
		$("#side").append("<li><a href='/userInfo/manage/product'>상품관리</a></li>");	 
		$("#side").append("<li><a href='/shop/create'>상품등록</a></li>");	 
		$("#side").append("<li><a href='/userInfo/manage/order'>주문관리</a></li>");	 
		$("#side").append("<li><a href='/userInfo/manage/manageOrderState'>판매추이</a></li>");	
		    
		//채팅관리
		$("#side").append("<li><a href='#'>채팅방관리</a></li>");	}	
		
		$("#side").append("<li><a href='/userInfo/myAudition'>나의오디션신청</a></li>");
		$("#side").append("<li><a href='/userInfo/voter'>나의투표</a></li>");
		$("#side").append("<li><a href='/shop/orderList'>나의주문</a></li>");
		$("#side").append("<li><a href='/shop/mybag'>장바구니</a></li>");
		// $("#side").append("<li><a href='#'>구매내역</a></li>");
	   
		    
	}

