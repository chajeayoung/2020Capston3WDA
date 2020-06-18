package com.vote.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Prd;
import com.vote.vote.db.dto.PrdCategory;
import com.vote.vote.db.dto.PrdCategoryD;
import com.vote.vote.db.dto.PrdColor;
import com.vote.vote.db.dto.PrdImage;
import com.vote.vote.db.dto.PrdOption;
import com.vote.vote.db.dto.PrdSize;
import com.vote.vote.db.dto.ProgramManager;
import com.vote.vote.repository.Asdf;
import com.vote.vote.repository.CustomPrdJapRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.PrdCateDJpaRepository;
import com.vote.vote.repository.PrdCategoryDJpaRepository;
import com.vote.vote.repository.PrdCategoryJpaRepository;
import com.vote.vote.repository.PrdColorJpaRepository;
import com.vote.vote.repository.PrdImageJpaRepository;
import com.vote.vote.repository.PrdJpaRepository;
import com.vote.vote.repository.PrdOptionJpaRepository;
import com.vote.vote.repository.PrdSizeJpaRepository;
import com.vote.vote.repository.ProgramManagerJpaRepository;
import com.vote.vote.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		

		return json;

	}

	@RequestMapping("/shop/cart")
	public String cart() {

		return "asdf";
	}
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
		
		
		


		return "redirect:/userInfo";
	}
	


	@RequestMapping(value={"/shop/store/axios","/shop/store/axios/"})// 상품저장 관련 정보 가져오기
	@ResponseBody
	public JSONArray categoryAxios(){// 상품 생성 뷰
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
		List<PrdOption> option = pOptionRepository.findByProductId(p_id);
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
			prdRepository.saveAndFlush(prd);
			
			List<PrdOption> options = pOptionRepository.findByProductId(p_id);
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
		List<PrdOption> option = pOptionRepository.findByProductId(prdId);
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

}
