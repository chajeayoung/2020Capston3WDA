package com.vote.vote.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.lang.Nullable;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Audition;
import com.vote.vote.db.dto.AuditionCon;
import com.vote.vote.db.dto.AuditionOption;
import com.vote.vote.db.dto.AuditionOptionValue;
import com.vote.vote.db.dto.AuditionResult;
import com.vote.vote.db.dto.Member;
import com.vote.vote.repository.AuditionConJpaRepository;
import com.vote.vote.repository.AuditionJpaRepository;
import com.vote.vote.repository.AuditionOptionJpaRepository;
import com.vote.vote.repository.AuditionOptionValueJpaRepository;
import com.vote.vote.repository.CustomAuditionOptionRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.ProgramManagerJpaRepository;
import com.vote.vote.service.StorageService;


@Controller
public class AuditionConController {
	
	@Autowired
	AuditionConJpaRepository auditionConRepository;
	
	@Autowired
	private StorageService storageService;

	@Autowired
	private MemberJpaRepository memberRepository;

	@Autowired
	private ProgramManagerJpaRepository pmRepository;
	
	@Autowired
	private AuditionJpaRepository auditionRepository;

	@Autowired
	private AuditionOptionJpaRepository auditionOptionReopository;

	@Autowired
	private AuditionOptionValueJpaRepository auditionOptionValueRepository;

	@Autowired
	private CustomAuditionOptionRepository customAuditionOptionRepository;
	
	@RequestMapping("/sendAddress")
	public String serch1(@RequestParam(value="confirm") String confirm, Model model) {
		List<AuditionCon> auditioncon = auditionConRepository.findByConfirm(confirm);
				System.out.println(confirm);
				model.addAttribute("auditionconlist", auditioncon);
				
				return "audition_con/list";
	}

	// @RequestMapping(value={"/sendAddress"},method = RequestMethod.POST)
	// public String messageCenterHome(Model model,HttpSession session,HttpServletRequest request) {

	// 	String selectedCity= request.getParameter("confirm");
	// 	return "/audition_con/list";
	// }

	
	@GetMapping("/audition_con/list")
	public String audition(Model model, @PageableDefault Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); 
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "formid"));
		model.addAttribute("auditionconlist", auditionConRepository.findAll(pageable));
		return "/audition_con/list";
	}
	
	// @RequestMapping("/audition_con/list")
	// public String list(Model model) { // 2~ n
	// 	model.addAttribute("auditionconlist",auditionConRepository.findAll());
	// 	return "/audition_con/list";
	// }
	
	
	
	

	@RequestMapping("/audition_con/listm")
	public String listmm(@Valid AuditionCon auditionCon, Integer rid, Model model) { // only 1
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails sessionUser = (CustomUserDetails)principal;
        
        System.out.println(sessionUser.getR_ID());
        AuditionCon auditioncon = auditionConRepository.findByRid(sessionUser.getR_ID());
        
        
		System.out.println(auditioncon.toString());
		model.addAttribute("auditionconlistm",auditioncon);
		
//		System.out.println(sessionUser.getR_ID()+"의 오디션 폼의 오디션 fk는"+auditioncon.getAuditionid());

				
		
		return "/audition_con/listm";
	
	}
	

	
	@GetMapping("/audition_con/read/{formid}")
	public String read(Model model, @PathVariable int formid){
		AuditionCon auditioncon = auditionConRepository.findByFormid(formid);
		System.out.println("auditioncon.getAuditionid(): "+auditioncon.getAuditionid());
		model.addAttribute("auditionCon", auditionConRepository.findByFormid(formid));
		model.addAttribute("options", customAuditionOptionRepository.getOptionWithValue(auditioncon.getAuditionid(),formid));
		
		return "audition_con/read";
	}
	

	@GetMapping("/audition_con/form/{auditionId}")
	public String form(Model model
			, @PathVariable int auditionId
			) {
		
		AuditionCon auditionCon = new AuditionCon();

		model.addAttribute("auditionCon",new AuditionCon());
		model.addAttribute("auditionId", auditionId);
		model.addAttribute("options", auditionOptionReopository.findByAuditionIdOrderByNo(auditionId));

		return "audition_con/form";
	}
	
	/////////////////////////////////////////////////////////////
	
	@PostMapping("/audition_con/form/{auditionId}")
	public String write(@Valid AuditionCon auditioncon, BindingResult bindingResult, SessionStatus sessionStatus,
			Principal principal, Model model, RedirectAttributes redirAttrs, @Nullable @RequestParam("option") String[] options,
			@PathVariable int auditionId,
			@RequestParam(name = "filename") MultipartFile filename	,
			@Nullable Authentication authentication ) {
		
			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			
			System.out.println(" 신청인 정보 : "+auditioncon);
		if(bindingResult.hasErrors()) {
			return "/audition_con/form";
		} else if(filename.isEmpty()) {
			Member member = memberRepository.findByUserid(principal.getName());
			auditioncon.setRid(member.getNo());
			auditioncon.setUsername(member.getName());
			auditioncon.setFdate(new Date());
			System.out.println(auditioncon.toString());
			auditionConRepository.save(auditioncon);
			sessionStatus.setComplete();
			return "redirect:/audition/complete";
		} else {
			
		    String filenamePath = StringUtils.cleanPath(filename.getOriginalFilename());
            Member member = memberRepository.findByUserid(principal.getName());
			auditioncon.setUsername(member.getName());
			
            // 게시글저장

            auditioncon.setRid(member.getNo());

            auditioncon.setFprofile(filenamePath);
            
            auditionConRepository.saveAndFlush(auditioncon);

         	auditioncon.setFdate(new Date());
			auditioncon.setAuditionid(auditionId);
			auditioncon.setRid(Integer.valueOf(userDetails.getR_ID()));
			auditionConRepository.save(auditioncon);

            
            
            // 파일 저장
            storageService.store(filename);

			sessionStatus.setComplete();
		
			if(options != null && !options[0].isEmpty()){

				List<AuditionOption> a_ops = auditionOptionReopository.findByAuditionIdOrderByNo(auditionId); // 오디션 옵션들.

				for(int i=0; i<options.length; i++){
					AuditionOptionValue result = new AuditionOptionValue();
					result.setAuditionId(auditionId);
					result.setOptionNo(a_ops.get(i).getNo());
					result.setValue(options[i]);
					result.setAuditionCon(auditioncon.getFormid());
					auditionOptionValueRepository.saveAndFlush(result);
				}			
			}
				

            System.out.println("게시글업로드완료");
            return "redirect:/audition/complete";
            

		}
	}
	
	
	
	
	
//	@PostMapping("/audition_con/form")
//			  public String form(
//					  
//					  SessionStatus sessionStatus,
//			            Principal principal,			       
//			            RedirectAttributes redirAttrs,
//			            @RequestParam(name = "fprofile") MultipartFile fprofile,
//			            @RequestParam(name= "fusername") String fusername,
//			            @RequestParam(name= "fuserphone") String fuserphone,
//			            @RequestParam(name= "fusermail") String fusermail,
//			            @RequestParam(name= "ftitle") String ftitle,
//			            @RequestParam(name= "faddr") String faddr,
//			            @RequestParam(name= "feducation") String feducation,
//			            @RequestParam(name= "fgender") String fgender,
//			            @RequestParam(name= "fheight") String fheight,
//			            @RequestParam(name= "fweight") String fweight,
//			            @RequestParam(name= "fblood") String fblood,
//			            @RequestParam(name= "ffamily") String ffamily,
//			            @RequestParam(name= "fhobby") String fhobby,
//			            @RequestParam(name= "fability") String fability) {
//						
//						String fprofilePath = storageService.store2(fprofile); 
//			            Member member = memberRepository.findByUserid(principal.getName());
////			            ProgramManager pm = pmRepository.findById(member.getNo());
//			           // Audition audition = auditionRepository.findByAuditionid(member.getNo());
//			           
//			            			         
//			            AuditionCon auditionCon = new AuditionCon();
//			            Audition audition = new Audition(); 
//			          
////			            auditionCon.setAuditionid(audition.getAuditionid());
//			            auditionCon.setRid(member.getNo());
//			            auditionCon.setFprofile(fprofilePath);
//			            auditionCon.setFusername(fusername);
//			            auditionCon.setFuserphone(fuserphone);
//			            auditionCon.setFusermail(fusermail);
//			            auditionCon.setFtitle(ftitle);
//			            auditionCon.setFaddr(faddr);
//			            auditionCon.setFeducation(feducation);
//			            auditionCon.setFgender(fgender);
//			            auditionCon.setFheight(fheight);
//			            auditionCon.setFweight(fweight);
//			            auditionCon.setFblood(fblood);
//			            auditionCon.setFfamily(ffamily); 
//			            auditionCon.setFhobby(fhobby);
//			            auditionCon.setFability(fability);
//			                 
//			            auditionConRepository.saveAndFlush(auditionCon);
//
//			            // 파일 저장	       
//			            sessionStatus.setComplete();
//			            System.out.println("게시글업로드완료");
//			            return "redirect:/audition_con/list";
//			           
//	}
	
//			@RequestParam(name="fprofile") MultipartFile fprofile,
//			@RequestParam(name="formid", required = false) Integer formid,
//			Model model,
//			RedirectAttributes redirAttrs,
//			AuditionCon auditioncon,
//			BindingResult bindingResult, 
//			SessionStatus sessionStatus,
//			Principal principal){
//				
//			
//			if (bindingResult.hasErrors()) {
//				return "audition_con/form";
//			} else {
//		
//				
//				
//			AuditionCon fprofile = new AuditionCon();
//			storageService.store(fprofile);
//			String filenamePath = StringUtils.cleanPath(fprofile.getOriginalFilename());
//			
//			
//			
//			auditionConRepository.saveAndFlush(auditioncon);  // 저장하고 커밋까지 Flush
//					
//			fprofile.setFormid(fprofile.getFormid());
//			fprofile.setFprofile(filenamePath); 
//			
//			auditionConRepository.saveAndFlush(fprofile);
//			sessionStatus.setComplete();
//			return "redirect:/audition_con";
//			}
		
		
//	public String form(@Valid AuditionCon auditionCon, BindingResult bindingResult, SessionStatus sessionStatus) {
//		if(bindingResult.hasErrors()) {
//			return "/audition_con/form";
//		} else {
//			auditionCon.setFdate(new Date());
//			System.out.println(auditionCon.toString());
//			auditionConRepository.save(auditionCon);
//			sessionStatus.setComplete();
//			return "redirect:/audition/complete";
//		}
//	}
	
	@GetMapping("/audition_con/update/{formid}")
	public String update(Model model, @PathVariable int formid){
		AuditionCon auditioncon = auditionConRepository.findByFormid(formid);
		model.addAttribute("auditionCon", auditioncon);		
		return "audition_con/list";
	}

	@PostMapping("/audition_con/update/{formid}")
	public String update(AuditionCon auditioncon, BindingResult bindingResult
	){
		if (bindingResult.hasErrors()) {
			return "/audition_con/list";
		} else {
			
			auditionConRepository.save(auditioncon).getFormid();
		return "redirect:/audition_con/list";
		}
	}	
	
	@GetMapping("/audition_con/serch")
	public String serch(@RequestParam(value="keyword") String keyword, Model model) {
		List<AuditionCon> auditioncon = auditionConRepository.findByConfirm(keyword);
		
				model.addAttribute("auditionconlist", auditioncon);
				
				return "audition_con/list";
	}

	
}