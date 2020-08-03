function w3_open() {
  
    document.getElementById("mobileSideBar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";

    var userRole = $("#userRole").text();

    // if(window.location.href.indexOf("userInfo") > -1){
    //   $("#mobileMenus").empty();
    // //개인정보
    // $("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo'>개인정보</a></div>");
			
	// 	if(userRole=="1"){ //관리자
	// 	//회원관리	
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/allUser'>회원관리</a></div>");
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/allCompany'>서비스신청관리</a></div>"); }
		
		
	// 	if(userRole=="1"||userRole=="2"){ //관리자 또는 매니저
	// 	//오디션관리
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/audition/list'>오디션개설</a></div>");}	
		
	// 	if(userRole=="1"||userRole=="2"||userRole=="3"){ //관리자 또는 매니저 또는 심사위원
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/audition_con/list'>예선심사</a></div>");
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/auditionresult/list'>심사결과</a></div>");}	
		
	// 	if(userRole=="1"||userRole=="2"){ //관리자 또는 매니저
	// 	//투표관리
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/vote/create'>투표개설</a></div>");	 
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/manage/vote'>투표관리</a></div>");
    // //커뮤니티관리

 
    // // if(userRole=="1")
    // // $("#mobileMenus").append("<div class='dropMenu'>커뮤니티관리"+
    // // "<div class='drop'><a href='/userInfo/AllProgram/'>프로그램관리</a><a href='#'>서비스소개</a><a href='#'>개발자소개</a></div></div>");	

	// 	if(userRole=="2")
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/myProgram/'>프로그램관리</a></div>");	 
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/myCommunity/'>팬클럽관리</a></div>");	 
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/hotclib'>핫클립관리</a></div>");	 
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/audience/mlist'>방청권추첨</a></div>");	 
		    
	// 	//굿즈샵관리
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/manage/product'>상품관리</a></div>");	 
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/shop/create'>상품등록</a></div>");	 
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/manage/order'>주문관리</a></div>");	 
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/manage/manageOrderState'>판매추이</a></div>");	
		    
	// 	//채팅관리
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='#'>채팅방관리</a></div>");	}	
			
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/myAudition'>나의오디션신청</a></div>");
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/userInfo/voter'>나의투표</a></div>");
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/shop/orderList'>나의주문</a></div>");
	// 	$("#mobileMenus").append("<div class='sideMenu'><a href='/shop/mybag'>장바구니</a></div>");
	// 	// $("#mobileMenus").append("<div class='sideMenu'><a href='#'>구매내역</a></div>");

     
    
    //  }
    //  else{
      $("#mobileMenus").empty();
      $("#mobileMenus").append(" <a href='/'><div class='sideMenu'>홈</div></a>");
     
      $("#mobileMenus").
      append("<div class='dropMenu'><a href='#'><div class='sideMenu'>소개</div></a><div class='drop'><a href='/introduce/blockChain'>블록체인이란?</a><a href='#'>서비스소개</a><a href='#'>개발자소개</a></div></div>");
    
      $("#mobileMenus").
      append("<div class='dropMenu'>"+
      "<a href='#'><div class='sideMenu'>오디션</div></a><div class='drop'><a href='/audition/listuser'>참가신청</a><a href='/auditionresult/listuser'>예선결과안내</a></div></div>");



      $("#mobileMenus").
      append("<a href='/vote' class='sideMenu'> <div class='sideMenu'>투표</div> </a> <div class='dropMenu sideMenu'><a href='/community'><div href='/community' class='sideMenu'>커뮤니티</div> </a><div class='drop'>"+
" <a href='#'>팬클럽</a><a href='#'>인기투표</a><a href='/hotclib'>주간Hot Clip</a><a href='/audience/ulist2'>방청권응모</a> </div> </div> <a href='/shop/index' class='sideMenu'> <div class='/shop/index'>굿즈샵</div> </a> ");
    
       
//      }
  
//   }
}
  function w3_close() {
    document.getElementById("mobileSideBar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
  }