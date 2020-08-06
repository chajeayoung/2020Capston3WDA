package com.vote.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.customSelect.CustomBagSelect;
import com.vote.vote.db.customSelect.CustomOrderInfo;
import com.vote.vote.db.customSelect.CustomOrderListSelect;
import com.vote.vote.db.customSelect.CustomOrderUpdate;
import com.vote.vote.db.customSelect.CustomPrd;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Mybag;
import com.vote.vote.db.dto.Order;
import com.vote.vote.db.dto.OrderList;
import com.vote.vote.db.dto.Popular;
import com.vote.vote.db.dto.Prd;
import com.vote.vote.db.dto.PrdCategory;
import com.vote.vote.db.dto.PrdCategoryD;
import com.vote.vote.db.dto.PrdColor;
import com.vote.vote.db.dto.PrdImage;
import com.vote.vote.db.dto.PrdOption;
import com.vote.vote.db.dto.PrdSize;
import com.vote.vote.db.dto.Program;
import com.vote.vote.db.dto.ProgramManager;
import com.vote.vote.repository.Asdf;
import com.vote.vote.repository.CustomMybagRepository;
import com.vote.vote.repository.CustomOrderListRepository;
import com.vote.vote.repository.CustomPrdJapRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.MybagJpaRepository;
import com.vote.vote.repository.OrderJpaRepository;
import com.vote.vote.repository.OrderListJpaRepository;
import com.vote.vote.repository.PopularJpaRepository;
import com.vote.vote.repository.PrdCateDJpaRepository;
import com.vote.vote.repository.PrdCategoryDJpaRepository;
import com.vote.vote.repository.PrdCategoryJpaRepository;
import com.vote.vote.repository.PrdColorJpaRepository;
import com.vote.vote.repository.PrdImageJpaRepository;
import com.vote.vote.repository.PrdJpaRepository;
import com.vote.vote.repository.PrdOptionJpaRepository;
import com.vote.vote.repository.PrdSizeJpaRepository;
import com.vote.vote.repository.ProgramJpaRepository;
import com.vote.vote.repository.ProgramManagerJpaRepository;
import com.vote.vote.service.KakaoPayService;
import com.vote.vote.service.StorageService;
import com.vote.vote.service.dto.BuyProductsDTO;
import com.vote.vote.service.dto.KakaoPayApprovalVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;




@Controller
public class ShopController {

	@Autowired
	PrdJpaRepository prdRepository;
	@Autowired
	PrdCateDJpaRepository prdCateDJpaRepository;
	@Autowired
	Asdf asdf;
	
	@Autowired  
	private StorageService storageService; 
	
	@Autowired
	private PrdCategoryJpaRepository  prdCategoryRepository;

	@Autowired
	private PrdCategoryDJpaRepository prdCategoryDRepository;

	@Autowired
	private PrdColorJpaRepository prdColorRepository;

	@Autowired
	private PrdSizeJpaRepository prdSizeRepository;

	@Autowired
	private ProgramManagerJpaRepository pManagerRepository;

	@Autowired
	private PrdImageJpaRepository pImageRepository;

	@Autowired
	private PrdOptionJpaRepository pOptionRepository;

	@Autowired
	private MemberJpaRepository memberRepository;
	
	@Autowired
	private CustomPrdJapRepository customPrdRepository;
	
	@Autowired
	private MybagJpaRepository mybagRepository;

	@Autowired
	private CustomMybagRepository customMybagRepository;

	@Autowired
	private OrderJpaRepository orderRepository;

	@Autowired
	private OrderListJpaRepository orderListRepository;

	@Autowired
	private CustomOrderListRepository customOrderListRepository;


	@Autowired
	private ProgramJpaRepository programRepository;


	@Autowired
	private KakaoPayService kakaoPay;


	@Autowired
	private ProgramManagerJpaRepository pmRepository;	

	@Autowired
	private PopularJpaRepository popularRepository;

	@RequestMapping("/shop/index")
	public String index(Model model,Principal user) {
//		model.addAttribute("username",user.getName());
		// prdRepository.find
		
		return "shop/index";

	}
	@RequestMapping(value={"/shop/index/axios","/shop/index/axios/"})
	@ResponseBody
	public JSONArray indexAxios(Model model,Principal user) {
		//카테고리별 상품 5개씩 (최근순)      // 나중에 추가 :  추천 상품, 신상품.
		
		JSONArray json = new JSONArray();
		json.add(0,customPrdRepository.getCategorySelect(4)); // 카테고리별로 4개 씩.
		json.add(1,customPrdRepository.getRecommendPrd(1,4)); 
		json.add(2,customPrdRepository.getRecommendPrd(5,8)); 

		return json;

	}

	@RequestMapping(value={"/shop/list","/shop/list"})
	public String shopList() {
		
		
		return "/shop/shop_list";
	}
	
	@ResponseBody
	@RequestMapping("/shop/list/axios")//기본 리스트, 옵션 선택, 페이지네이션
	public CustomPrd shopListAxios(
		@Nullable @RequestParam("categoryId")	Integer categoryId,
		@Nullable @RequestParam("categoryDId") Integer categoryDId,
		@Nullable @RequestParam("search") String search,
		@Nullable @RequestParam("programId") Integer programId,
		@PageableDefault Pageable page
		){
		System.out.println(("카테고리별 아이템 요청"));
		int category = 0;
		int categoryD = 0;
		int program = 0;
		String text = " ";
		
		

		if(categoryId != null ){
			category = categoryId.intValue();
		}
		if(categoryDId != null){
			categoryD = categoryDId.intValue();
		}
		if(search != null){
			text = search;
		}
		if(programId != null ){
			program = programId.intValue();
		}

		CustomPrd prds = customPrdRepository.getPrdsByCategory(category, categoryD, text, program, page);

		return prds;
	}
	@ResponseBody
	@RequestMapping("/shop/categorySet")
	public JSONArray categorySet(){
		List<PrdCategoryD> categoryD1 =  prdCategoryDRepository.findByCategory(1);
		List<PrdCategoryD> categoryD2 =  prdCategoryDRepository.findByCategory(2);
		List<PrdCategoryD> categoryD3 =  prdCategoryDRepository.findByCategory(3);
		List<PrdCategoryD> categoryD4 =  prdCategoryDRepository.findByCategory(4);
		List<PrdCategoryD> categoryD5 =  prdCategoryDRepository.findByCategory(5);
		List<PrdCategoryD> categoryD6 =  prdCategoryDRepository.findByCategory(6);
		List<Program> programs = programRepository.findAll();
		JSONArray result = new JSONArray();
		result.add(0,categoryD1);
		result.add(1,categoryD2);
		result.add(2,categoryD3);
		result.add(3,categoryD4);
		result.add(4,categoryD5);
		result.add(5,categoryD6);
		result.add(6,programs);

		return result;
	}

	// @RequestMapping("/shop/cart")
	// public String cart() {

	// 	return "asdf";
	// }


	@RequestMapping(value={"/shop/create","/shop/create/"})
	public String create(){// 상품 생성 뷰

		return "/shop/create";
	}
	@RequestMapping(value={"/shop/create/axios","/shop/create/axios/"})
	@ResponseBody
	public JSONArray createAxios(){// 상품 생성 뷰
		JSONArray datas = new JSONArray();

		

		return datas;
	}
	@RequestMapping(value={"/shop/store","/shop/store/"})// 상품저장
	public String store(
		@RequestParam("category") int category, //카테고리
		@RequestParam("categoryD") int categoryD, // 세부 카테고리
		@RequestParam("endTime") String endTime, // 판매 종료날짜
		@RequestParam("title") String title, // 상품명
		@RequestParam("info1") String info1, // 간단설명
		@RequestParam("info2") String info2, // 상세설명
		@RequestParam("price") int price, // 가격
		@RequestParam("stock") int stock, // 재고 
		@RequestParam("file1") MultipartFile[] file1,//대표이미지
		@RequestParam("file2") MultipartFile[] file2,//부가 이미지
		@Nullable @RequestParam("file3") MultipartFile[] file3, // 설명이미지endTime,
		@Nullable @RequestParam("optionColor") int[] optionColor,
		@Nullable @RequestParam("optionSize") int[] optionSize,
		@Nullable @RequestParam("optionTitle") String[] optionTitle,
		@Nullable @RequestParam("optionPrice") int[] optionPrice,
		@Nullable @RequestParam("optionStock") int[] optionStock,
		@RequestParam("pop") int pop,
		Principal user,
		Authentication authentication

	 ){// 상품 생성 뷰
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		ProgramManager pManager = pManagerRepository.findById(userDetails.getR_ID());

		// 상품 저장
		String file1Name= storageService.store2(file1[0]); // 대표사진

		Prd product = new Prd();

		product.setProgramId(pManager.getProgramId());// 프로그램 id 
		product.setCategoryId(category);  // 카테고리 id
		product.setCategoryD(categoryD); // 세부 카테고리 id
		product.setManager(userDetails.getR_ID()); // 매니저 아이디
		product.setName(title); // 상품명
		product.setContent(info1); // 상품 간단 설묭
		product.setDetail(info2); // 상품 상세 설명
		product.setPrice(price); // 상품 가격
		product.setState("0"); // 상품 판매 상태   (임시)
		product.setEndDate(endTime); // 상품 판매 종료 날짜
		product.setStock(stock);	
		product.setImg(file1Name);
		product.setPopId(pop);
		System.out.println(product.toString());
		prdRepository.saveAndFlush(product);



		System.out.println("상품저장 완료");
		System.out.println("상품 id : "+product.getProductId());
		// 상품 이미지 저장

		ArrayList<String> file2Name = new ArrayList<String>();
		ArrayList<String> file3Name = new ArrayList<String>();
		
		
		


		// // 파일 저장	
		for(int i=0;i<file2.length;i++){ // 최대 서브 파일 3개
			if(!file2[i].isEmpty()){
				file2Name.add(storageService.store2(file2[i]));		// 파일 저장 및 파일 이름을 배열에 저장
			}
			
		}

		
			for(int i=0;i<file3.length;i++){
				if(!file3[i].isEmpty()){ // 파일이 업로드 되지 않아도, 길이가 1로 잡힘
					file3Name.add(storageService.store2(file3[i]));// 파일 저장 및 파일 이름을 배열에 저장
				}
			}
		
		


		for(String name: file2Name){
			PrdImage Img = new PrdImage();
			Img.setProductId(product.getProductId());
			Img.setProductImage(name);
			Img.setImageState("1");// 대표 이미지 0	
			pImageRepository.saveAndFlush(Img);
		}
		try{ // 상품 설명 이미지는 선택 사항
			if(file3Name != null){
				for(String name: file3Name){
					PrdImage Img = new PrdImage();
					Img.setProductId(product.getProductId());
					Img.setProductImage(name);
					Img.setImageState("2");// 설명이미지.
					pImageRepository.saveAndFlush(Img);
				}
				System.out.println("3번 저장 완료");
			}
			
		}catch(NullPointerException e){
			System.out.println("상품 설명 이미지 없음");
		}
		

		try{
			// 상품 옵션 저장
			PrdOption defaultOption = new PrdOption();
			defaultOption.setColorId(0);
			defaultOption.setSizeId(0);
			defaultOption.setProductId(product.getProductId());
			defaultOption.setoPrice(0);
			defaultOption.setoTitle("기본");
			defaultOption.setpStock(stock);
			pOptionRepository.saveAndFlush(defaultOption);
			if(optionColor != null){
				for(int i =0; i<optionColor.length; i++){
					PrdOption pOption = new PrdOption();
					pOption.setColorId(optionColor[i]);
					pOption.setSizeId(optionSize[i]);
					pOption.setProductId(product.getProductId());
					pOption.setoPrice(optionPrice[i]);
					pOption.setoTitle(optionTitle[i]);
					pOption.setpStock(optionStock[i]);
					pOptionRepository.saveAndFlush(pOption);
				}
			}
			
		}catch(NullPointerException e){
			System.out.println("상품 옵션 선택사항");
		}
		
		
		


		return "redirect:/shop/create";
	}
	


	@RequestMapping(value={"/shop/store/axios","/shop/store/axios/"})// 상품저장 관련 정보 가져오기
	@ResponseBody
	public JSONArray categoryAxios(@Nullable Authentication authentication){// 상품 생성 뷰

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		JSONArray categorys = new JSONArray();

		// 카테고리 가져옴.
		List<PrdCategory> category = prdCategoryRepository.findAll();
		List<PrdCategoryD> categoryD = prdCategoryDRepository.findAll();

		categorys.add(0,category);
		categorys.add(1,categoryD);


		//상품 옵션 가져옴
		List<PrdColor> color = prdColorRepository.findAll();
		List<PrdSize> size = prdSizeRepository.findAll();

		categorys.add(2,color);
		categorys.add(3,size);

		ProgramManager pm = pmRepository.findById(userDetails.getR_ID()); 
		  
		Program program = programRepository.findById(pm.getProgramId());

		
		List<Popular> populares = popularRepository.findByPid(program.getId());

					
		JSONArray json = new JSONArray();
		
		for( Popular popular : populares){
			JSONObject popularData = new JSONObject();
			
			popularData.put("id", popular.getId());
			popularData.put("name", popular.getName());
			popularData.put("img", popular.getImg());
			popularData.put("p_id", popular.getPid());

			
			json.add(popularData);
		}

		categorys.add(4,json);

		return categorys;
	}

	@RequestMapping(value={"/shop/edit/{prdId}","/shop/edit/{prdId}"}) // 상품 수정 뷰
	public String editPrd(@PathVariable("prdId") int prdId, Model model){
		model.addAttribute("id", prdId);

		return "/shop/edit";
	}

	@ResponseBody
	@RequestMapping(value={"/shop/edit/{prdId}/axios","/shop/edit/{prdId}/axios"}) // 상품 수정 뷰
	public JSONObject editPrdAxios(@PathVariable("prdId") int p_id){
		Prd prd = prdRepository.findByProductId(p_id);
		List<PrdOption> option = pOptionRepository.findByProductIdOrderByOptionIdAsc(p_id);
		option.remove(0);
		List<PrdImage> img = pImageRepository.findByProductId(p_id);

		JSONObject json = new JSONObject();
		json.put("prd", prd);
		json.put("option",option);
		json.put("img",img);
		return json;
	}
	
	@RequestMapping(value={"/shop/update/{prdId}","/shop/update/{prdId}/"}, method = RequestMethod.POST) // 상품 PUT  
	public String prdUpdate(// HTML 에서는 GET , POST 만 지원 하는것으로 알고 있음.
		@PathVariable("prdId") int p_id,
		@RequestParam("category") int category, //카테고리
		@RequestParam("categoryD") int categoryD, // 세부 카테고리
		@RequestParam("endTime") String endTime, // 판매 종료날짜
		@RequestParam("title") String title, // 상품명
		@RequestParam("info1") String info1, // 간단설명
		@RequestParam("info2") String info2, // 상세설명
		@RequestParam("price") int price, // 가격
		@RequestParam("stock") int stock, // 재고 
		@Nullable @RequestParam("file1") MultipartFile[] file1,//대표이미지
		@Nullable @RequestParam("file2") MultipartFile[] file2,//부가 이미지
		@Nullable @RequestParam("file3") MultipartFile[] file3, // 설명이미지endTime,
		@Nullable @RequestParam("optionColor") int[] optionColor,
		@Nullable @RequestParam("optionSize") int[] optionSize,
		@Nullable @RequestParam("optionTitle") String[] optionTitle,
		@Nullable @RequestParam("optionPrice") int[] optionPrice,
		@Nullable @RequestParam("optionStock") int[] optionStock,
		@Nullable @RequestParam("detailImgDelete") boolean detailImgDelete

		){ 
			// 상품 기본정보 수정 
			Prd prd = prdRepository.findByProductId(p_id);
			prd.setCategoryId(category);
			prd.setCategoryD(categoryD);
			prd.setEndDate(endTime);
			prd.setName(title);
			prd.setContent(info1);
			prd.setDetail(info2);
			prd.setPrice(price);
			prd.setState("0");
			prd.setStock(stock);

			if(!file1[0].isEmpty()){ // 파일이 있으면 업데이트
				String file1Name= storageService.store2(file1[0]); // 대표사진
				prd.setImg(file1Name);
			}
			System.out.println(prd.toString());
			prdRepository.saveAndFlush(prd);
			
			List<PrdOption> options = pOptionRepository.findByProductIdOrderByOptionIdAsc(p_id);
			PrdOption defaultOption = options.get(0);
			defaultOption.setpStock(stock);
			pOptionRepository.saveAndFlush(defaultOption);
			options.remove(0);// 기본 옵션은 수정할 필요가 없음.

			if(optionColor != null){// 옵션이 있다면.

				if(optionColor.length > options.size()){ // 옵션수가 증가한 경우.
					int count = optionColor.length - options.size();// 추가된 옵션 개수
					
					for(int i =0; i<options.size(); i++){ // 기존 옵션 수만큼 업데이트
						PrdOption item = options.get(i);
						item.setColorId(optionColor[i]);
						item.setSizeId(optionSize[i]);
						item.setoTitle(optionTitle[i]);
						item.setoPrice(optionPrice[i]);
						item.setpStock(optionStock[i]);
						pOptionRepository.saveAndFlush(item);
					}
	
					for(int i = optionColor.length-count; i<optionColor.length;i++){ // 추가된 옵션 수 만큼 Insert
						PrdOption item = new PrdOption();
						item.setProductId(p_id);
						item.setColorId(optionColor[i]);
						item.setSizeId(optionSize[i]);
						item.setoTitle(optionTitle[i]);
						item.setoPrice(optionPrice[i]);
						item.setpStock(optionStock[i]);
						pOptionRepository.saveAndFlush(item);
					}
	
				}else if(optionColor.length == options.size()){//옵션이 수정된 경우.
					for(int i =0; i<options.size(); i++){ // 기존의 옵션 업데이트
						PrdOption item = options.get(i);
						item.setColorId(optionColor[i]);
						item.setSizeId(optionSize[i]);
						item.setoTitle(optionTitle[i]);
						item.setoPrice(optionPrice[i]);
						item.setpStock(optionStock[i]);
						pOptionRepository.saveAndFlush(item);
					}
				}else{ // 옵션이 삭제된 경우.
					int count = options.size() - optionColor.length;// 삭제할 옵션 개수
					// 기존 5 , 추가 2     count 1   3 
					for(int i =0; i<optionColor.length; i++){ // 기존 옵션 수만큼 업데이트
						PrdOption item = options.get(i);
						item.setColorId(optionColor[i]);
						item.setSizeId(optionSize[i]);
						item.setoTitle(optionTitle[i]);
						item.setoPrice(optionPrice[i]);
						item.setpStock(optionStock[i]);
						pOptionRepository.saveAndFlush(item);
					}
	
					for(int i = options.size()-count; i<options.size();i++){ // 기존의 옵션 삭제.
						pOptionRepository.delete(options.get(i));
					}
				}

			}else{// 옵션이 없는 경우. 
				for(PrdOption option: options){
					pOptionRepository.delete(option);
				}
			}
			

		// 서브 이미지 && 부가 이미지 수정

		// 서브 이미지
		List<PrdImage> subImages = pImageRepository.findByProductIdAndImageState(p_id, "1");

		// 부가이미지
		List<PrdImage> infoImages = pImageRepository.findByProductIdAndImageState(p_id, "2");

		if(!file2[0].isEmpty()){ // 파일이 없어도, length 가 1로 잡힘.
			
			if(file2.length > subImages.size()){//추가되는 이미지가, 기존 이미지 수 보다 많을 경우.
				int count = file2.length - subImages.size();
				for(int i=0; i<subImages.size(); i++){// 기존 이미지 수 만큼 업데이트
					PrdImage img = subImages.get(i);
					img.setProductImage(storageService.store2(file2[i]));
					pImageRepository.saveAndFlush(img);
				}

				for(int i = file2.length-count; i<file2.length; i++){ // 추가된 이미지 만큼 저장
					PrdImage img = new PrdImage();
					img.setProductId(p_id);
					img.setImageState("1");
					img.setProductImage(storageService.store2(file2[i]));
					pImageRepository.saveAndFlush(img);
				}
			}else if(file2.length == subImages.size()){//추가되는 이미지 수 == 기존 이미지 수

				for(int i=0; i<subImages.size(); i++){// 기존 이미지 수 만큼 업데이트
					PrdImage img = subImages.get(i);
					img.setProductImage(storageService.store2(file2[i]));
					pImageRepository.saveAndFlush(img);
				}
			}else{// 새로 업데이트 되는 이미지가, 기존 이미지 수 보다 적은 경우
				int count = subImages.size() - file2.length;

				for(int i=0; i<file2.length; i++){ // 기존 이미지 컬럼 수정
					PrdImage img = subImages.get(i);
					img.setProductImage(storageService.store2(file2[i]));
					pImageRepository.saveAndFlush(img);
				}
				for(int i = subImages.size()-count; i<subImages.size(); i++){//  이미지 삭제
					pImageRepository.delete(subImages.get(i));
				}
			}

		}
		if(detailImgDelete){ // 파일 전체 삭제
			for(int i =0; i<infoImages.size();i++){
				pImageRepository.delete(infoImages.get(i));
			}
	
		}else if(!file3[0].isEmpty()){ // 파일이 없어도, length 가 1로 잡힘.
			
			if(file3.length > infoImages.size()){//추가되는 이미지가, 기존 이미지 수 보다 많을 경우.
				int count = file3.length - infoImages.size();
				for(int i=0; i<infoImages.size(); i++){// 기존 이미지 수 만큼 업데이트
					PrdImage img = infoImages.get(i);
					img.setProductImage(storageService.store2(file3[i]));
					pImageRepository.saveAndFlush(img);
				}

				for(int i = file3.length-count; i<file3.length; i++){ // 추가된 이미지 만큼 저장
					PrdImage img = new PrdImage();
					img.setProductId(p_id);
					img.setImageState("2");
					img.setProductImage(storageService.store2(file3[i]));
					pImageRepository.saveAndFlush(img);
				}
			}else if(file3.length == infoImages.size()){//추가되는 이미지 수 == 기존 이미지 수

				for(int i=0; i<infoImages.size(); i++){// 기존 이미지 수 만큼 업데이트
					PrdImage img = infoImages.get(i);
					img.setProductImage(storageService.store2(file3[i]));
					pImageRepository.saveAndFlush(img);
				}
			}else{// 새로 업데이트 되는 이미지가, 기존 이미지 수 보다 적은 경우
				int count = infoImages.size() - file3.length;

				for(int i=0; i<file3.length; i++){ // 기존 이미지 컬럼 수정
					PrdImage img = infoImages.get(i);
					img.setProductImage(storageService.store2(file3[i]));
					pImageRepository.saveAndFlush(img);
				}
				for(int i = infoImages.size()-count; i<infoImages.size(); i++){//  이미지 삭제
					pImageRepository.delete(infoImages.get(i));
				}
			}

		}
			



		return "redirect:/userInfo/manage/product";
	}


	@RequestMapping(value={"/shop/product/{prdId}","/shop/product/{prdId}/"}, method=RequestMethod.GET)
	public String prdShow(@PathVariable("prdId") int prdId){

		return "shop/prdShow";

	}
	@RequestMapping(value={"/shop/product/axios/{prdId}","/shop/product/axios/{prdId}/"}, method=RequestMethod.GET)
	@ResponseBody
	public JSONArray prdShowAxios(@PathVariable("prdId") int prdId){
		Prd prd = prdRepository.findByProductId(prdId);
		List<PrdImage> img = pImageRepository.findByProductId(prdId);
		List<PrdOption> option = pOptionRepository.findByProductIdOrderByOptionIdAsc(prdId);
		List<PrdColor> color = prdColorRepository.findAll();
		List<PrdSize> size = prdSizeRepository.findAll();

		Member member = memberRepository.findByNo(prd.getManager());
		
		JSONArray result = new JSONArray();

		result.add(0,prd);// 상품정보
		result.add(1,img);// 상품 이미지
		result.add(2,option);// 옵션 정보
		result.add(3,color);// 색상 리스트
		result.add(4,size); // 사이즈 리스트
		result.add(5,member); // 판매자 정보
		return result;

	}
	@RequestMapping(value={"/shop/product/{prdId}","/shop/product/{prdId}/"}, method=RequestMethod.DELETE)
	@ResponseBody
	public void prdDelete(@PathVariable("prdId") int prdId){


		prdRepository.deleteById(prdId);

	}

	// 장바구니
	@RequestMapping(value={"/shop/{prdId}/mybag","/shop/{prdId}/mybag/"}, produces = "application/json") //Axios
	@ResponseBody
	public JSONObject addMybag(
		@Nullable @PathVariable("prdId") int p_id, 
		// @RequestParam("optionId") String optionId,
		// @RequestParam("quantity") String quantity,
		@RequestBody Mybag mybag,// json 데이터는 class 로 받아야 하는 듯.
		Principal user, 
		@Nullable Authentication authentication){ // 상품을 장바구니에 추가.


			JSONObject json = new JSONObject();
		try{
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		
			mybag.setProductId(p_id); //상품 id 
			// mybag.setOptionId(Integer.parseInt(optionId)); // 옵션 id
			// mybag.setQuantity(Integer.parseInt(quantity)); // 수량
			mybag.setUserId(userDetails.getR_ID()); //유저 아이디
			mybagRepository.saveAndFlush(mybag);

			json.put("success", "장바구니에 추가되었습니다.");
			return json;

			
			}catch(Exception e){
				json.put("error","장바구니 추가에 실패했습니다.");
				return json;
			}
	}
	
		@RequestMapping(value={"/shop/mybag","/shop/mybag/"})
		public String showMybag(){


			return "/shop/mybag";

		}

		@ResponseBody
		@RequestMapping(value={"/shop/mybag/axios","/shop/mybag/axios/"}, method=RequestMethod.GET)
		public CustomBagSelect showMybagAxios(
			@Nullable Authentication authentication,
			@PageableDefault Pageable page
		) {
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			// CustomBagSelect mybags = customMybagRepository.getMybag(userDetails.getR_ID(), page);
			CustomBagSelect mybags = customMybagRepository.getMybag(userDetails.getR_ID());



			return mybags;
		}
		@ResponseBody
		@RequestMapping(value={"/shop/mybag/{bagId}","/shop/mybag/{bagId}"}, method=RequestMethod.DELETE)
		public JSONObject mybagItemDelete(@PathVariable("bagId") int id) {

			// 유효성 검사로, 자신의 장바구니 아이템인지 확인하는 소스 추가 가능.
			JSONObject result = new JSONObject();

			

			try{
				mybagRepository.deleteById(id);
				result.put("success", "장바구니에서 삭제되었습니다.");

			}catch(Exception e){
				System.out.println("장바구니 아이템 삭제중 오류 발생");
				result.put("error", "삭제에 실패하였습니다.");
			}	
			
			
			return result;

		}
		
		@RequestMapping(value={"/shop/order","/shop/order/"}, method=RequestMethod.GET)
		public String order() {
			
			return "/shop/order";
		}
		@ResponseBody
		@RequestMapping(value={"/shop/order/axios","/shop/order/axios/"}, method=RequestMethod.GET)
		public List<CustomOrderInfo> orderProductInfo(@RequestParam("productId") int[] productIds, // 주문 뷰에 상품 정보 보냄.
		@RequestParam("optionId") int[] optionIds,
		@RequestParam("quantity") int[] quantitys,
		@Nullable @RequestParam("bagId") int[] bagId
		){
			
			
			List<CustomOrderInfo> infos = new ArrayList<CustomOrderInfo>();

			for(int i=0; i<productIds.length; i++){
				CustomOrderInfo item = new CustomOrderInfo();
				Prd prd = prdRepository.findByProductId(productIds[i]);
				PrdOption option = pOptionRepository.findByOptionId(optionIds[i]);

				item.setId(prd.getProductId());
				item.setName(prd.getName());
				item.setImg(prd.getImg());
				item.setOptionId(option.getOptionId());
				item.setOptionName(option.getoTitle());
				item.setPrice(prd.getPrice());
				item.setoPrice(option.getoPrice());
				item.setCount(quantitys[i]);

				if(bagId != null){
					item.setBagId(bagId[i]);
				}
				
				infos.add(item);
				
			}
			


			return infos;
		}
		@RequestMapping(value={"/shop/order/error","/shop/order/error"})
		public String error(){
			return "/shop/orderError";
		}
		@RequestMapping(value={"/shop/order","/shop/order/"}, method=RequestMethod.POST)
		public String productBuy(
			@RequestParam("productId") int[] productIds, //상품 id
			@RequestParam("optionId") int[] optionIds, //옵션 id
			@RequestParam("count") int[] quantitys, // 수량
			@RequestParam("addr") String addr, // 도로명 주소
			@RequestParam("addr2") String addr2, // 상세주소
			@RequestParam("receiver") String receiver, //수취인
			@RequestParam("phone") String phone, // 수취인 연락처
			@Nullable @RequestParam("bagId") int[] bagId,
			@Nullable Authentication authentication 
			) {
				CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
				
				//재고 감소, 검증
				// System.out.println("카카오 과연 되는것인가? "+kakaoPay.kakaoPayReady());

				for(int i=0; i<optionIds.length; i++){
					PrdOption option = pOptionRepository.findByOptionId(optionIds[i]);
					if( !(option.getpStock() < quantitys[i]) ){//재고가 있므면.
						// option.setpStock( option.getpStock() - quantitys[i]);
						

						// if(option.getoTitle().equals("기본")){
						// 	System.out.println("기본옵션임");
						// 	Prd productItem5 = prdRepository.findByProductId(option.getProductId());			
							
							
						// 	System.out.println(productItem5.toString());
						// 	try{
						// 		// StringUtil dateError = new StringUtil(productItem5.getEndDate());

						// 		productItem5.setEndDate(productItem5.getEndDate().split(" ")[0]);
						// 		productItem5.setStock(option.getpStock());
						// 		prdRepository.saveAndFlush(productItem5);
						// 	}catch(Exception e){
						// 		e.printStackTrace();
						// 	}
							
						// }
						
						// pOptionRepository.saveAndFlush(option);

						

					}else{
						return "redirect:/shop/order/error";
					}
				}

				// 윗쪽 재고 관리.  아랫쪽은 주문 정보 입력.
				
				String title = "";
				int  sum = 0;
				int count = 0;

				for(int i=0;i<productIds.length;i++){

					Prd prd = prdRepository.findByProductId(productIds[i]);
					PrdOption option = pOptionRepository.findByOptionId(optionIds[i]);

					if(i ==0 ){
						title = prd.getName();
					}
				// 	Order order = new Order();
				// 	order.setrId(userDetails.getR_ID());
				// 	order.setAddr(addr);
				// 	order.setAddr2(addr2);
				// 	// order.setInvoice(invoice);
				// 	order.setPhone(phone);
				// 	order.setReceiver(receiver);
				// 	order.setPrice((prd.getPrice()+option.getoPrice())*quantitys[i]);
				// 	order.setState("0");
				// 	orderRepository.saveAndFlush(order);

					
				// 	OrderList orderList = new OrderList();
					
				// 	orderList.setCount(quantitys[i]);
				// 	orderList.setOptionId(option.getOptionId());
				// 	orderList.setOrderId(order.getOrderId());
				// 	orderList.setPrice((prd.getPrice()+option.getoPrice())*quantitys[i]);
				// 	orderList.setProductId(prd.getProductId());
					
				// 	orderListRepository.saveAndFlush(orderList);
					sum += (prd.getPrice()+option.getoPrice())*quantitys[i];
					count += quantitys[i];
				}
				if(productIds.length > 1){
					title += " 외"+(count-1)+"개 상품";
				}
				

				
				
				
				// if(bagId[0] != 0){// bagId 값이 넘어오지 않으면, [ 0 ]  으로 초기화 되는 듯 하다. @Nullable

				// 	for(int id : bagId){
						
				// 		mybagRepository.deleteById(id);
				// 	}
				// }
			
			BuyProductsDTO buy = new BuyProductsDTO(productIds, optionIds, quantitys, addr, addr2, receiver, phone, bagId);
			

			return "redirect:"+kakaoPay.kakaoPayReady(title,count,sum,buy);// 상품명, 개수, 금액, dto
			// return "redirect:/shop/order/ok";
		}

		@RequestMapping(value="/shop/kakaoPaySuccess")
		public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, @Nullable Authentication authentication ){

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			System.out.println("카카오 토큰 : "+pg_token);
			// System.out.println("카카오 결제 생세 정보 : "+kakaoPay.kakaoPayInfo(pg_token));
			KakaoPayApprovalVO kakao  = kakaoPay.kakaoPayInfo(pg_token);
			System.out.println("aaa: "+ kakao.getPrd());
			BuyProductsDTO prd =  kakao.getPrd();
			int[] optionIds = prd.getOptionIds();
			int[] quantitys = prd.getQuantitys();
			int[] productIds = prd.getProductIds();


			for(int i=0; i<optionIds.length; i++){
				PrdOption option = pOptionRepository.findByOptionId(optionIds[i]);
				// if( !(option.getpStock() < quantitys[i]) ){//재고가 있므면.
					option.setpStock( option.getpStock() - quantitys[i]);
					

					if(option.getoTitle().equals("기본")){
						System.out.println("기본옵션임");
						Prd productItem5 = prdRepository.findByProductId(option.getProductId());			
						
						
						System.out.println(productItem5.toString());
						try{
							// StringUtil dateError = new StringUtil(productItem5.getEndDate());

							productItem5.setEndDate(productItem5.getEndDate().split(" ")[0]);
							productItem5.setStock(option.getpStock());
							prdRepository.saveAndFlush(productItem5);
						}catch(Exception e){
							e.printStackTrace();
						}
						
					}
					
					pOptionRepository.saveAndFlush(option);

				// }else{
					// return "redirect:/shop/order/error";
				// }
			}

			for(int i=0;i<productIds.length;i++){

				Prd product = prdRepository.findByProductId(productIds[i]);
				PrdOption option = pOptionRepository.findByOptionId(optionIds[i]);

				Order order = new Order();
				order.setrId(userDetails.getR_ID());
				order.setAddr(prd.getAddr());
				order.setAddr2(prd.getAddr2());
				// order.setInvoice(invoice);
				order.setPhone(prd.getPhone());
				order.setReceiver(prd.getReceiver());
				order.setPrice((product.getPrice()+option.getoPrice())*quantitys[i]);
				order.setState("0");
				orderRepository.saveAndFlush(order);

				OrderList orderList = new OrderList();
				
				orderList.setCount(quantitys[i]);
				orderList.setOptionId(option.getOptionId());
				orderList.setOrderId(order.getOrderId());
				orderList.setPrice((product.getPrice()+option.getoPrice())*quantitys[i]);
				orderList.setProductId(product.getProductId());
				
				orderListRepository.saveAndFlush(orderList);

			}

			return "redirect:/shop/order/ok";
		}
		
		@RequestMapping(value={"/shop/kakaoPayCancel","/shop/kakaoPayCancel/"})
		public String kakaoPayCancle(){

			return "/shop/orderError";
		}
		@RequestMapping(value={"/shop/kakaoPayFail","/shop/kakaoPayFail"})
		public String kakaoPayFail(){

			return "/shop/orderError";
		}



		@RequestMapping(value={"/shop/order/ok","/shop/order/ok/"}, method=RequestMethod.GET)
		public String orderBuyOk() {

			return "/shop/buySuccess";
		}

		// 주문 리스트 뷰
		@RequestMapping(value={"/shop/orderList","/shop/orderList/"}, method=RequestMethod.GET)
		public String orderList() {
			return "/shop/orderList";
		}

		// 주문 리스트 데이터 Axios
		@ResponseBody
		@RequestMapping(value="/shop/orderList/axios", method=RequestMethod.GET) 
		public CustomOrderListSelect orderListAxios(@Nullable Authentication authentication, @PageableDefault Pageable page) {

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			CustomOrderListSelect orderList =  customOrderListRepository.getOrderListbyUserId(userDetails.getR_ID(), page);

			return orderList;
		}

		// 주문 상세 뷰
		@RequestMapping(value={"/shop/orderShow/{orderListId}","/shop/orderShow/{orderListId}/"}, method=RequestMethod.GET) 
		public String orderShow(@PathVariable("orderListId") int listId, Model model){ 
			model.addAttribute("listId", listId);
			return "/shop/orderShow";
		}

		// 주문 상세 데이터 Axios
		@ResponseBody
		@RequestMapping(value={"/shop/orderShow/{orderListId}/axios","/shop/orderShow/{orderListId}/axios/"}, method=RequestMethod.GET)
		public CustomOrderListSelect orderShowAxios(@PathVariable("orderListId") int listId) {

			// CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

			CustomOrderListSelect orderItems =  customOrderListRepository.getOrderListbyOrderListId(listId);

			return orderItems;
		}
		
		@ResponseBody
		@RequestMapping(value={"/shop/orderShow/{orderId}/axios","/shop/orderShow/{orderId}/axios"}, method=RequestMethod.PUT)
		public JSONObject orderUpdateAxios(@PathVariable("orderId") int orderId, @RequestBody CustomOrderUpdate info) {

			JSONObject result = new JSONObject();

			// System.out.println("info : "+info.getInvoice());
			// System.out.println("info : "+info.getState());

			try{
				System.out.println(orderId);
				Order order = orderRepository.findByOrderId(orderId);

				String invoice = info.getInvoice();
				if(info.getInvoice().length() == 0){
					invoice = "0";
				}
				
				order.setInvoice(invoice);
				order.setState(info.getState());

				orderRepository.saveAndFlush(order);	
				result.put("success", "주문 정보 수정 완료");
			}catch(Exception e){
				e.printStackTrace();
				result.put("error","오류발생! 주문 정보 수정에 실패했습니다.");
			}
			

			

			return result;
		}
		
		// @RequestMapping(value="/kakaoPay", method=RequestMethod.POST)
		// public String kakaoPay() {
		// 	return "redirect" + kakaoPay.kakaoPayReady();
		// }

		
}

