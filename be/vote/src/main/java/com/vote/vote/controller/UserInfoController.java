package com.vote.vote.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.constraints.Null;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.customSelect.CustomAuditionCon;
import com.vote.vote.db.customSelect.CustomOrderListSelect;
import com.vote.vote.db.customSelect.CustomOrderState;
import com.vote.vote.db.customSelect.CustomOrderStatePop;
import com.vote.vote.db.customSelect.CustomOrderStatePopAge;
import com.vote.vote.db.customSelect.CustomOrderStatePopGender;
import com.vote.vote.db.customSelect.CustomPrd;
import com.vote.vote.db.customSelect.CustomVote;
import com.vote.vote.db.dto.Company;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Popular;
import com.vote.vote.db.dto.Program;
import com.vote.vote.db.dto.ProgramManager;
import com.vote.vote.db.dto.Vote;
import com.vote.vote.db.dto.AuditionCon;
import com.vote.vote.repository.AuditionConJpaRepository;
import com.vote.vote.repository.CompanyJpaRepository;
import com.vote.vote.repository.CustomAuditionConRepository;
import com.vote.vote.repository.CustomCompanyRepository;
import com.vote.vote.repository.CustomMemberRepository;
import com.vote.vote.repository.CustomOrderListRepository;
import com.vote.vote.repository.CustomOrderReopsitoy;
import com.vote.vote.repository.CustomPrdJapRepository;
import com.vote.vote.repository.CustomProgramRepository;
import com.vote.vote.repository.CustomVoteRepository;
import com.vote.vote.repository.JudgeJpaRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.OrderJpaRepository;
import com.vote.vote.repository.PopularJpaRepository;
import com.vote.vote.repository.ProgramJpaRepository;
import com.vote.vote.repository.ProgramManagerJpaRepository;
import com.vote.vote.service.StorageService;

// import org.springframework.util.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	
	@Autowired
	private MemberJpaRepository memberRepository;
	
	@Autowired  
	private StorageService storageService; 
	
	@Autowired
	private CustomMemberRepository customRepository;
	
	@Autowired
	private CompanyJpaRepository companyRepository;
	
	@Autowired
	private CustomCompanyRepository customCompanyRepository;
	
	@Autowired
	private ProgramJpaRepository programRepository;
	
	@Autowired
	private AuditionConJpaRepository AuditionConRepository;

	@Autowired
	private CustomProgramRepository customProgramRepository;
	
	@Autowired
	private ProgramManagerJpaRepository pmRepository;	
	
	@Autowired
	private JudgeJpaRepository jmRepository;
	// @Autowired
	// private VoteJpaRepository voteRepository;
	@Autowired
	private CustomVoteRepository customVoteRepository;
	
	@Autowired
	private PopularJpaRepository popularRepository;

	@Autowired
	private CustomPrdJapRepository customPrdRepository;

	@Autowired
	private CustomOrderListRepository customOrderListRepository;

	@Autowired
	private OrderJpaRepository orderRepository;

	@Autowired
	private CustomOrderReopsitoy customOrderReopsitoy;

	@Autowired
	private CustomAuditionConRepository customAuditionConRepository;



	//개인정보
	@RequestMapping(value={"","/"})
	public String index(RedirectAttributes redirAttrs,Model model) {  
		
	
		
        return "userInfo/index";
       	}
	
	@RequestMapping(value={"/axios","/axios/"})
	@ResponseBody
	public JSONArray indexAxios(Principal user, Model model){
			
		Member member = memberRepository.findByUserid(user.getName());
		
		JSONArray json = new JSONArray();
		JSONObject memberData = new JSONObject();
	
		//카카오톡 유저인지? 일반 유저인지?
		if(member.getPassword()==null) {
			memberData.put("kakao", "1");
			memberData.put("r_id", member.getNo());
			memberData.put("username", member.getName());
			memberData.put("nickname", member.getNickname());
			memberData.put("userid", member.getUserid());
			memberData.put("profile", member.getProfile());
			memberData.put("gender", member.getGender());
			memberData.put("birth", member.getBirth());
			memberData.put("phone", member.getPhone());
			memberData.put("joindate", member.getJoindate());
			memberData.put("addr", member.getAddr());
			memberData.put("addr2", member.getAddr2());
			memberData.put("point", member.getPoint());
			memberData.put("role", member.getRole());
			
			System.out.println(member.getBirth());
//			memberData.put("r_id", member.getNo());
//			memberData.put("username", member.getName());
//			memberData.put("joindate", member.getJoindate());
//			memberData.put("userid", member.getUserid());
//			memberData.put("profile", member.getProfile());
//			memberData.put("phone", member.getPhone());
			
		}else {
			memberData.put("kakao", "0");
			memberData.put("r_id", member.getNo());
			memberData.put("password", member.getPassword());
			memberData.put("username", member.getName());
			memberData.put("nickname", member.getNickname());
			memberData.put("userid", member.getUserid());
			memberData.put("profile", member.getProfile());
			memberData.put("gender", member.getGender());
			memberData.put("birth", member.getBirth());
			memberData.put("phone", member.getPhone());
			memberData.put("joindate", member.getJoindate());
			memberData.put("addr", member.getAddr());
			memberData.put("addr2", member.getAddr2());
			memberData.put("point", member.getPoint());
			memberData.put("role", member.getRole());
			
		}
			
			json.add(memberData);
		
				
		return json;
	}
	 @RequestMapping(value="/userUpdate", method=RequestMethod.POST)
	    public String updateOk(Member cc, RedirectAttributes redirAttrs, Principal principal,
	    		@RequestParam(name="profile2") MultipartFile file){
	       	
	    	System.out.println("test:"+cc.toString());
	    	String thumbnailPath = cc.getProfile();  // 프로필사진 변동안했을때 그대로 두기위해서
	    	String url = "/uploads/";

	    	if(!file.isEmpty()) { // 프로필사진 변경을 했을시 
	    		
	    		System.out.println("넘어온 파일없다 마");
//	    		storageService.store2(file);
	    		//String thumbnailPath2 = StringUtils.cleanPath(file.getOriginalFilename());
	    		thumbnailPath = url.concat(storageService.store2(file));
	    		//thumbnailPath = url.concat(thumbnailPath2);
	   	
	    	}
	    
	    	
	 	memberRepository.userUpdate(cc.getPassword(), cc.getName(), cc.getGender(), cc.getBirth(), 
		 			cc.getNickname(), cc.getAddr(), cc.getAddr2(),thumbnailPath, cc.getNo());

		
	            return "redirect:/userInfo";
	        

	    }
	 //회원정보
		@RequestMapping(value={"/allUser"})
		public String allUser(Principal user, RedirectAttributes redirAttrs) {        
			
	        return "userInfo/alluser";
	       	}
		
	 @RequestMapping(value={"/axios2","/axios2/"}) //사용자정보
		@ResponseBody
		public JSONArray indexAxios(Principal user, @PageableDefault Pageable pageable, Model model){
				
			List<Member> members = customRepository.findAll(pageable);
		
			long count = customRepository.CountAll();

			System.out.println("pageable : " + pageable);

			System.out.println("getOffset : " + pageable.getOffset());

			JSONArray json = new JSONArray();

			for( Member member : members){
				JSONObject memberData = new JSONObject();
				memberData.put("r_id", member.getNo());
				memberData.put("username", member.getName());
				memberData.put("nickname", member.getNickname());
				memberData.put("userid", member.getUserid());
				memberData.put("profile", member.getProfile());
				memberData.put("gender", member.getGender());
				memberData.put("birth", member.getBirth());
				memberData.put("phone", member.getPhone());
				memberData.put("joindate", member.getJoindate());
				memberData.put("addr", member.getAddr());
				memberData.put("addr2", member.getAddr2());
				memberData.put("point", member.getPoint());
				memberData.put("role", member.getRole());
				
				json.add(memberData);
			}
			json.add(count);
					
			return json;
		}
	 
	 //회사정보
		@RequestMapping(value={"/allCompany"})
		public String allCompany() {        
			
	        return "userInfo/allCompany";
	       	}
		
		 @RequestMapping(value={"/axios3","/axios3/"}) //사용자정보
			@ResponseBody
			public JSONArray companyAxios(Principal user, @PageableDefault Pageable pageable, Model model){
					
				List<Company> companies = customCompanyRepository.findAll(pageable);
			
				long count = customCompanyRepository.CountAll();

				System.out.println("pageable : " + pageable);

				System.out.println("getOffset : " + pageable.getOffset());

				JSONArray json = new JSONArray();


				for( Company company : companies){
					JSONObject companyData = new JSONObject();
					
					companyData.put("c_id", company.getId());
					companyData.put("r_id", company.getRid());
					companyData.put("c_name", company.getCname());
					companyData.put("c_content", company.getCcontent());
					companyData.put("c_reader", company.getCreader());
					companyData.put("c_phone", company.getCphone());
					companyData.put("c_program", company.getCprogram());
					companyData.put("c_category", company.getCcategory());
					companyData.put("c_startdate", company.getCstartdate());
					companyData.put("c_enddate", company.getCenddate());
					companyData.put("c_budget", company.getCbudget());
					companyData.put("c_confirm", company.getCconfirm());

					
					json.add(companyData);
				}
				json.add(count);
						
				return json;
			}
		 
		 @RequestMapping(value="/companyUpdate", method=RequestMethod.POST)
		    public String registerOk(Company cc){
		       	
			 System.out.println("DDD");
			 System.out.println("DDD"+cc.toString());
		    	return "redirect:/userInfo/allCompany";
		    

		    }
		 
		 @RequestMapping(value={"/companyConfirm","/companyConfirm/"},
				 method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE) 
		 @ResponseBody
			public JSONObject companyConfirm(@RequestBody JSONObject axiosData,
											 Principal user,
											 @Nullable Authentication authentication,
											 Program program,
											 ProgramManager pm
											){
		
			 CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
				

			 System.out.println(axiosData.get("select"));  // 관리자가 선택한 프로그램의 사업자번호
			 int data =  (int) axiosData.get("select");
			 JSONObject result = new JSONObject();
			 
			// 처음 투표하는 사람인지 확인하기 위한 voter
			Company company = customCompanyRepository.findByConfirm(data);
			String confirm = company.getCconfirm();
				if(confirm.equals("0")) { // 승인을 받은적이 없으면
					System.out.println("승인되지 않은 프로그램입니다.");
					
					Company cc = companyRepository.findById(data); // 해당 사업자번호의 회사정보를 찾는다.
					
					program.setCategory(cc.getCcategory());   //카테고리
					program.setImg("defaultProfile.png"); //기본이미지
					program.setName(cc.getCprogram());            //프로그램명
					
					
					programRepository.saveAndFlush(program);       // program 테이블에 추가
					
					Program pi = customProgramRepository.findByPK(cc.getCprogram()); // 해당 프로그램의 번호를 가져옴
									
					System.out.println(""+cc.getRid()+"/"+pi.getId());
					pm.setId(cc.getRid());
					pm.setProgramId(pi.getId());
					
					pmRepository.saveAndFlush(pm); // 프로그램 권한자 등록
					
					customCompanyRepository.updateByConfirm(data); // 프로그램 confirm 1로 변경
					memberRepository.managerUpdate(cc.getRid()); // 회원테이블 role 3 으로 변경 (매니저)
					
					System.out.println(program.toString());
					
					
					result.put("message","승인완료 및 매니저권한을 부여하였습니다.");
					
					
				}else {
					result.put("errorMessage","이미 승인한 프로그램입니다.");
					System.out.println("이미 승인한 프로그램입니다.");
				}
		

				return result;
				
			}
	
	@RequestMapping(value={"/voter","/voter/"})
	public String voterVoteList(Principal user){// 내가 투표한 투표 리스트  뷰
		
		
		return "/userInfo/voterVoteList";
	}


			//  내 정보 페이지 투표 관련
	@RequestMapping(value={"/voter/axios","/voter/axios/"})
	@ResponseBody
	public JSONArray voterVoteListAxios(Principal user, @PageableDefault Pageable pageable){// 내가 투표한 투표 리스트  정보
		Member member = memberRepository.findByUserid(user.getName());
		

		CustomVote cv = customVoteRepository.getVotesByR_id(pageable, member.getNo());

		
		JSONArray result = new JSONArray();

		for(Vote vote : cv.getVotes()){
			JSONObject voteInfo = new JSONObject();
			voteInfo.put("no", vote.getId());
			voteInfo.put("title", vote.getTitle());
			voteInfo.put("startTime",vote.getLongStartTime());
			voteInfo.put("endTime",vote.getLongEndTime());
			voteInfo.put("resultTime",vote.getLongResultShowTime());
			result.add(voteInfo);
		}
		result.add(cv.getCount());
		
		return result;
	}
	// 투표 관리 페이지
	@RequestMapping(value={"/manage/vote","/manage/vote/"})
	public String manageVoteView(){// 내가 투표한 투표 리스트  뷰
		
		
		return "/userInfo/manageVote";
	}


			//  내 정보 페이지 투표 관련
	@RequestMapping(value={"/manage/vote/axios","/manage/vote/axios/"})
	@ResponseBody
	public JSONArray manageVoteAxios(Principal user, @PageableDefault Pageable pageable){// 내가 투표한 투표 리스트  정보
		Member member = memberRepository.findByUserid(user.getName());
		
		CustomVote cv = customVoteRepository.getMyVotes(pageable, member.getNo());
		// List<Vote>  votes = customVoteRepository.getMyVotes(pageable, member.getNo());
		// int count = customVoteRepository.getMyVotesCount(member.getNo());

		// System.out.println("ㅁㅁㅁㅁ"+votes);
		
		JSONArray result = new JSONArray();

		for(Vote vote : cv.getVotes()){
			JSONObject voteInfo = new JSONObject();
			voteInfo.put("no", vote.getId());
			voteInfo.put("title", vote.getTitle());
			voteInfo.put("startTime",vote.getLongStartTime());
			voteInfo.put("endTime",vote.getLongEndTime());
			voteInfo.put("resultTime",vote.getLongResultShowTime());
			result.add(voteInfo);
		}
		result.add(cv.getCount());
		
		return result;
	}
		 
		 //나의 프로그램
			@RequestMapping(value={"/myProgram"})
			public String myProgram() {        
				
		        return "userInfo/myProgram";
		       	}
			
		 
		 @RequestMapping(value={"/myProgram/axios","/myProgram/axios/"}) //사용자정보
			@ResponseBody
			public JSONObject myProgramAxios(){
					
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			CustomUserDetails sessionUser = (CustomUserDetails)principal;
			
			 
		
		  if(sessionUser.getROLE().equals("2")) {
		  
		  ProgramManager pm = pmRepository.findById(sessionUser.getR_ID()); 
		  
//		  System.out.println( pm.getId());
		 
		  
		  Program program = programRepository.findById(pm.getProgramId());
	  
//		  System.out.println(program.toString());
		  
		

		
				JSONObject programData = new JSONObject();
				
				programData.put("id", program.getId());
				programData.put("name", program.getName());
				programData.put("img", program.getImg());
				programData.put("category", program.getCategory());
				programData.put("logo", program.getLogo());

				
				
					
			return programData;
		  
		  
		  }else { 
			  System.out.println("매니저가아닙니다.");
			  
		  }
		  
		return null;
		 
				
				
				
			

			 	
			}
		 
		 @RequestMapping(value="/programUpdate", method=RequestMethod.POST)
		    public String programUpdate(Program p, 
		    		RedirectAttributes redirAttrs, Principal principal,
					@RequestParam(name="file") MultipartFile file,
					@Nullable @RequestParam(name="logoImg") MultipartFile[] logoImg){
		       	
			 
			 
		    	String thumbnailPath = p.getImg();



				String logo = "0";
		    	if(!file.isEmpty()) { // 프로필사진 변경을 했을시 
		    		
		    				    	
			    	thumbnailPath = storageService.store2(file);
		   	
				}	
				if(!logoImg[0].isEmpty()){
					logo = StringUtils.cleanPath(logoImg[0].getOriginalFilename());
					p.setLogo(logo);
					storageService.store2(logoImg[0]);
				}

				System.out.println("p : "+p);
			 programRepository.programUpdate(p.getId(), p.getName(), thumbnailPath, p.getCategory(), logo);
			
			 
		    	return "redirect:/userInfo/myProgram";   

		    }
		 
		 //팬클럽 관리
			@RequestMapping(value={"/myCommunity"})
			public String myCommunity() {        
				System.out.println("팬클럽관리");
		        return "userInfo/myCommunity";
		        
		       	}
		 
			 @RequestMapping(value={"/myCommunity/axios","/myCommunity/axios/"}) //사용자정보
				@ResponseBody
				public JSONArray myCommunityAxios(Principal user, @PageableDefault Pageable pageable, Model model){
						
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				CustomUserDetails sessionUser = (CustomUserDetails)principal;
		
				if(sessionUser.getROLE().equals("2")) {
			  
					ProgramManager pm = pmRepository.findById(sessionUser.getR_ID()); 
			  
//				  System.out.println( pm.getId());
		  
					Program program = programRepository.findById(pm.getProgramId());
	  
     				System.out.println("gd"+pm.getProgramId());
     				
					List<Popular> populares = popularRepository.findByPid(program.getId());
		
								
					JSONArray json = new JSONArray();
					
					for( Popular popular : populares){
						JSONObject popularData = new JSONObject();
						
						popularData.put("id", popular.getId());
						popularData.put("name", popular.getName());
						popularData.put("img", popular.getImg());
						popularData.put("logo", popular.getLogo());
						popularData.put("birth", popular.getBirth());
						popularData.put("height", popular.getHeight());	
						popularData.put("weight", popular.getWeight());
						popularData.put("blood", popular.getBlood());
						popularData.put("hobby", popular.getHobby());
						popularData.put("ability", popular.getAbility());
						popularData.put("intro", popular.getIntro());
						popularData.put("p_id", popular.getPid());

						
						json.add(popularData);
					}
				
					//json.add(count);
					json.add(pm.getProgramId());
					return json;
			  
			  
			  }else { 
				  System.out.println("잘못된 접근입니다.");
				  
			  }
			  
			return null;	 	
				}
			 
				
			@RequestMapping(value={"/hoo"}, method=RequestMethod.POST)
			public String hoo(@RequestParam("formid") int formid, Authentication authentication){ //후보
				
				CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
				AuditionCon auditionCon = AuditionConRepository.findByFormid(formid);

				ProgramManager pManager = pmRepository.findById(userDetails.getR_ID());
				Popular pop = new Popular();

				pop.setName(auditionCon.getFusername());
				pop.setImg(auditionCon.getFprofile());
				pop.setLogo(auditionCon.getFprofile());
				pop.setPid(pManager.getProgramId());
				pop.setBirth(auditionCon.getBirth2());
				pop.setWeight(auditionCon.getFweight());
				pop.setHeight(auditionCon.getFheight());
				pop.setBlood(auditionCon.getFblood());
				pop.setHobby(auditionCon.getFhobby());
				pop.setAbility(auditionCon.getFability());
				pop.setIntro(auditionCon.getIntroduce());
				
				popularRepository.saveAndFlush(pop);
				
					
				return "redirect:/audition_con/list";
			}
			
			 
			@RequestMapping(value="/insertPopular", method=RequestMethod.POST)
				public String insertOk(Popular pp, RedirectAttributes redirAttrs, 
				Principal principal
				,@Nullable @RequestParam(name="img2") MultipartFile file,
				@Nullable @RequestParam(name="img3") MultipartFile file2
			    		){
			       	
					
			    	System.out.println("test:"+pp.toString());


	
			    		
			    		 String thumbnailPath = storageService.store2(file);

						 pp.setImg(thumbnailPath);
						 
						 String thumbnailPath2 = storageService.store2(file2);

			    		 pp.setLogo(thumbnailPath2);
			    		
			    		 System.out.println("후보등록정보:"+pp.toString());
			    	
			    		 popularRepository.saveAndFlush(pp);
				
			            return "redirect:/userInfo/myCommunity";
			        

			    }	
			
			@RequestMapping(value="/updatePopular", method=RequestMethod.POST)
		    public String updateOk(Popular pp, RedirectAttributes redirAttrs, Principal principal
		    		,@Nullable @RequestParam(name="img2") MultipartFile file,
					 @Nullable @RequestParam(name="img3") MultipartFile file2
		    		){
		       	
				System.out.println(pp.toString());
		    	
		    	
		    	String thumbnailPath = pp.getImg();  // 프로필사진 변동안했을때 그대로 두기위해서
				String url = "/uploads/";
				
				String thumbnailPath2 = pp.getLogo();  // 프로필사진 변동안했을때 그대로 두기위해서


		
		    	if(!file.isEmpty()) { // 프로필사진 변경을 했을시 

		    		    		thumbnailPath = storageService.store2(file);
		    		
				   }
				   
				if(!file2.isEmpty()) { 

					thumbnailPath2 = storageService.store2(file2);
		
				 }
				 pp.setImg(thumbnailPath);
				 pp.setLogo(thumbnailPath2);
				popularRepository.saveAndFlush(pp);
				
		    	
		    	
		            return "redirect:/userInfo/myCommunity";
		        

		    }	
			
			@RequestMapping(value="/deletePopular", method=RequestMethod.POST)
		    public String deleteOk(Popular pp, RedirectAttributes redirAttrs, Principal principal
		    		){
		       	
				System.out.println(pp.toString());
		    			    	
		    	
		    	popularRepository.deleteByid(pp.getId());
				
		    	
		    	
		            return "redirect:/userInfo/myCommunity";
		        

		    }	
		

		@RequestMapping(value={"/manage/product","/manage/product/"})
		public String managePrd(){

			return "/userInfo/managePrd";
		}
		@RequestMapping(value={"/manage/product/axios","/manage/product/axios/"})
		@ResponseBody
		public CustomPrd managePrdAxios(@PageableDefault Pageable pageable, Authentication authentication){

			try{
				CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

				CustomPrd prd = customPrdRepository.getManagerPrd(userDetails.getR_ID(), pageable);
				
				return prd;

			}catch(Exception e){
				System.out.println("등록 상품 목록에서 오류 발생.");
				return null;
			}
		}

		@RequestMapping(value={"/manage/order","/manage/order/"}, method=RequestMethod.GET)
		public String manageOrder() {

			return "/userInfo/manageOrder";
		}
		@ResponseBody
		@RequestMapping(value={"/manage/order/axios","/manage/order/axios/"}, method=RequestMethod.GET)
		public CustomOrderListSelect manageOrderAxios(@Nullable Authentication authentication, @PageableDefault Pageable page) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			CustomOrderListSelect items = customOrderListRepository.getOrderListByManagerId(userDetails.getR_ID(),page);
			
			return items;
		}


		@RequestMapping(value={"/manage/manageOrderState","/manage/manageOrderState/"}, method=RequestMethod.GET)
		public String orderState() {

			return "/userInfo/orderState";
		}
		@ResponseBody
		@RequestMapping(value={"/manage/manageOrderState/axios","/manage/manageOrderState/axios/"}, method=RequestMethod.GET)
		public JSONObject  orderStateAxios(@Nullable Authentication authentication) {

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			ProgramManager pManager = pmRepository.findById(userDetails.getR_ID());

			List<CustomOrderState> result = customOrderReopsitoy.getOrderStateByManagerId(userDetails.getR_ID());
			List<CustomOrderStatePop> result2 = customOrderReopsitoy.getOrderStatePopByProgramId(pManager.getProgramId());
			List<CustomOrderStatePopGender> result3 = customOrderReopsitoy.getOrderStatePopGenderByProgramId(pManager.getProgramId());
			List<CustomOrderStatePopAge> result4 = customOrderReopsitoy.getOrderStatePopPopAgeByProgramId(pManager.getProgramId());
			JSONObject json = new JSONObject();
			json.put("state", result);
			json.put("pop", result2);
			json.put("gender", result3);
			json.put("age", result4);



			return json;
		}

		@RequestMapping(value={"/myAudition","/myAudition"}, method = RequestMethod.GET)
		public String myAudition(){
			return "/userInfo/myAudition";
		}
		@RequestMapping(value={"/myAudition/axios","/myAudition/axios/"}, method = RequestMethod.GET)
		@ResponseBody
		public CustomAuditionCon myAuditionAxios(@Nullable Authentication authentication,  @PageableDefault Pageable page){

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			CustomAuditionCon auditionCon = customAuditionConRepository.getMyAuditionCon(userDetails.getR_ID(), page);
			System.out.println(auditionCon.getCount());
			return auditionCon;
		}


		
		
		
}