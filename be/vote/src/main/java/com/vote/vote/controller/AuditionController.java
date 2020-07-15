package com.vote.vote.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import com.vote.vote.db.dto.Audition;
import com.vote.vote.db.dto.AuditionOption;
import com.vote.vote.db.dto.Member;
import com.vote.vote.repository.AuditionJpaRepository;
import com.vote.vote.repository.AuditionOptionJpaRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.ProgramManagerJpaRepository;
import com.vote.vote.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
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

@Controller
public class AuditionController {

	
	@Autowired
	AuditionJpaRepository auditionRepository;
	
	@Autowired
	private StorageService storageService;

	@Autowired
	private MemberJpaRepository memberRepository;

	@Autowired
	private ProgramManagerJpaRepository pmRepository;

	@Autowired
	private AuditionOptionJpaRepository auditionOptionReopository;
	
//	@RequestMapping("/audition/list")
//	public String list(Model model) {
//		model.addAttribute("auditionlist",auditionRepository.findAll());
//		System.out.println(auditionRepository.findAll());
//		return "audition/list";
//	}

	@GetMapping("/audition/list")
	public String audition(Model model, @PageableDefault Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); 
		pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "auditionid"));
		model.addAttribute("auditionlist", auditionRepository.findAll(pageable));
		
		
		return "audition/list";
	}

	@GetMapping("/audition/listuser")
	public String auditionuser(Model model, @PageableDefault Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); 
		pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "auditionid"));
		model.addAttribute("auditionuserlist", auditionRepository.findAll(pageable));
		return "audition/listuser";
	}
	
	@RequestMapping("/audition/complete")
	public String com(Model model) {

		return "audition/complete";
	}
	

	
	@GetMapping("/audition/serch")
	public String serch(@RequestParam(value="keyword") String keyword, Model model) {
		List<Audition> audition = auditionRepository.findByAtitle(keyword);
		
				model.addAttribute("auditionlist", audition);
				
				return "audition/list";
	}

	@GetMapping("/audition/serchuser")
	public String serchuser(@RequestParam(value="keyword") String keyword, Model model) {
		List<Audition> audition = auditionRepository.findByAtitle(keyword);
		
				model.addAttribute("auditionuserlist", audition);
				
				return "audition/listuser";
	}
	


	@GetMapping("/audition/read/{auditionid}")
	public String read(Model model, @PathVariable int auditionid){
		model.addAttribute("audition", auditionRepository.findByAuditionid(auditionid));
		model.addAttribute("options", auditionOptionReopository.findByAuditionIdOrderByNo(auditionid));
		Audition audition = auditionRepository.findByAuditionid(auditionid);
		auditionRepository.save(audition);

		return "audition/read";
	}

	@GetMapping("/audition/readuser/{auditionid}")
	public String readuser(Model model, @PathVariable int auditionid){
		model.addAttribute("audition", auditionRepository.findByAuditionid(auditionid));

		Audition audition = auditionRepository.findByAuditionid(auditionid);
		auditionRepository.save(audition);

		return "audition/readuser";
	}
	
	
	@GetMapping("/audition/write")
	public String register(Model model) {
		
		model.addAttribute("audition",new Audition());
		return "audition/write";
	}
	
	@PostMapping("/audition/write")
	public String write(@Valid Audition audition, BindingResult bindingResult, SessionStatus sessionStatus,
			Principal principal, Model model, RedirectAttributes redirAttrs,
			@Nullable @RequestParam("option") String[] option,
            @RequestParam(name = "filename") MultipartFile filename	) {
		
		
	// 	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //    CustomUserDetails sessionUser = (CustomUserDetails)principal;
		
		if(bindingResult.hasErrors()) {
			return "/audition/write";
		} else if(filename.isEmpty()) {
			Member member = memberRepository.findByUserid(principal.getName());
			audition.setRid(member.getNo());
			audition.setAusername(member.getName());
			auditionRepository.save(audition);
			sessionStatus.setComplete();
			return "redirect:/audition/list";
		} else {
			
		    String filenamePath = StringUtils.cleanPath(filename.getOriginalFilename());
            Member member = memberRepository.findByUserid(principal.getName());
//            ProgramManager pm = pmRepository.findById(member.getNo());
			
            // 게시글저장
//            audition.setStartdate(pm.getProgramId());
			audition.setRid(member.getNo());
			audition.setAusername(member.getName());
//            audience.setADate(new Date());
            audition.setAfile(filenamePath);
            auditionRepository.saveAndFlush(audition);

            // 파일 저장
            storageService.store(filename);
            // rfile.setApplyid(audience.getApplyId());
            // rfile.setFilename(filenamePath);
            // rfileRepository.saveAndFlush(rfile);
			sessionStatus.setComplete();
			
			
			if(option!=null && !option[0].isEmpty()){// 데이터가 있으면,	
				for(int i=0; i<option.length; i++){
					AuditionOption auditionOption = new AuditionOption();
					auditionOption.setAuditionId(audition.getAuditionid());
					auditionOption.setName(option[i]);
					
					auditionOptionReopository.saveAndFlush(auditionOption);
				}
			}


            System.out.println("게시글업로드완료");
            return "redirect:/audition/list";
            
            
            
//			audition.setRid(sessionUser.getR_ID());
//			System.out.println(audition.toString());
//			auditionRepository.save(audition);
//			sessionStatus.setComplete();
//			return "redirect:/audition/list";
		}
	}
	
//	@PostMapping("/audition/write")
//	  public String write(
//			  
//			  SessionStatus sessionStatus,
//	            Principal principal,			       
//	            RedirectAttributes redirAttrs,
//	            @RequestParam(name = "afile") MultipartFile afile,
//	            @RequestParam(name= "atitle") String atitle,
//	            @RequestParam(name= "acontent") String acontent,
//	            @RequestParam(name= "acategory") String acategory
////	            @RequestParam(name= "") String ftitle,
////	            @RequestParam(name= "faddr") String faddr,
////	            @RequestParam(name= "feducation") String feducation,
////	            @RequestParam(name= "fgender") String fgender,
////	            @RequestParam(name= "fheight") String fheight,
////	            @RequestParam(name= "fweight") String fweight,
////	            @RequestParam(name= "fblood") String fblood,
////	            @RequestParam(name= "ffamily") String ffamily,
////	            @RequestParam(name= "fhobby") String fhobby,
////	            @RequestParam(name= "fability") String fability
//	            ) {
//				
//				String fprofilePath = storageService.store2(afile); 
//	            Member member = memberRepository.findByUserid(principal.getName());
////	            ProgramManager pm = pmRepository.findById(member.getNo());
//	           // Audition audition = auditionRepository.findByAuditionid(member.getNo());
//	           
//	            			         
//	            AuditionCon auditionCon = new AuditionCon();
//	            Audition audition = new Audition(); 
//	          
////	            auditionCon.setAuditionid(audition.getAuditionid());
//	            audition.setRid(member.getNo());
//	            audition.setAfile(fprofilePath);
//	            audition.setAtitle(atitle);
//	            audition.setAcontent(acontent);
//	            audition.setAcategory(acategory);
////	            auditionCon.setFtitle(ftitle);
////	            auditionCon.setFaddr(faddr);
////	            auditionCon.setFeducation(feducation);
////	            auditionCon.setFgender(fgender);
////	            auditionCon.setFheight(fheight);
////	            auditionCon.setFweight(fweight);
////	            auditionCon.setFblood(fblood);
////	            auditionCon.setFfamily(ffamily); 
////	            auditionCon.setFhobby(fhobby);
////	            auditionCon.setFability(fability);
//	                 
//	            auditionRepository.saveAndFlush(audition);
//
//	            // 파일 저장	       
//	            auditionRepository.save(audition);
//	            sessionStatus.setComplete();
//	            System.out.println("게시글업로드완료");
//	            return "redirect:/audition/list";
//	           
//}
//	

	@GetMapping("/audition/update/{auditionid}")
	public String update(Model model, @PathVariable int auditionid){
		Audition audition = auditionRepository.findByAuditionid(auditionid);
		model.addAttribute("audition", audition);		
		model.addAttribute("options", auditionOptionReopository.findByAuditionIdOrderByNo(auditionid));
		return "audition/update";
	}


	@PostMapping("/audition/update/{auditionid}")
	public String update_post(@Valid Audition audition, BindingResult bindingResult, SessionStatus sessionStatus,
			Principal principal, Model model, RedirectAttributes redirAttrs,
			@RequestParam(name = "filename") MultipartFile filename	,
			@Nullable @RequestParam("preAddOption") String[] preAddOptions,
			@Nullable @RequestParam("option") String[] options ) {

		if(bindingResult.hasErrors()) {
			return "/audition/update";
		}
		
		List<AuditionOption> auditionOption = auditionOptionReopository.findByAuditionIdOrderByNo(audition.getAuditionid());

		// 기존에 추가된 커스텀 옵션 체크
		if(preAddOptions != null && !preAddOptions[0].isEmpty()){
			if( auditionOption.size() != preAddOptions.length ){ // 기존의 추가옵션이  제거된 경우.
				for(AuditionOption audi_option : auditionOption){
					boolean state = true;

					for(int i=0; i<preAddOptions.length; i++){
						System.out.println(preAddOptions[i]);
						System.out.println(audi_option.getName());
						if(preAddOptions[i].equals(audi_option.getName())){//if(preAddOptions[i].equals(audi_option.getName())){
							state = false;
							System.out.println("존재하는 옵션");
							break;
						}						
					}

					if(state){
						System.out.println("옵션 삭제");
						auditionOptionReopository.delete(audi_option);
					}
						
				}
			}
		}
		// 새롭게 추가된 커스텀 옵셥 

		if(options != null && !options[0].isEmpty()){
			for(int i=0; i<options.length; i++){
				AuditionOption newOption = new AuditionOption();
				newOption.setAuditionId(audition.getAuditionid());
				newOption.setName(options[i]);
				auditionOptionReopository.saveAndFlush(newOption);
			}
			

		}

		if(filename.isEmpty()) {
			Member member = memberRepository.findByUserid(principal.getName());
			audition.setRid(member.getNo());
			audition.setAusername(member.getName());
			auditionRepository.save(audition);
			sessionStatus.setComplete();
			auditionRepository.save(audition).getAuditionid();
			return "redirect:/audition/list";
		} else {
			
		    String filenamePath = StringUtils.cleanPath(filename.getOriginalFilename());
            Member member = memberRepository.findByUserid(principal.getName());
//            ProgramManager pm = pmRepository.findById(member.getNo());
			
            // 게시글저장
			audition.setRid(member.getNo());
			audition.setAusername(member.getName());
            audition.setAfile(filenamePath);
            auditionRepository.saveAndFlush(audition).getAuditionid();

            // 파일 저장
            storageService.store(filename);
            sessionStatus.setComplete();
            System.out.println("게시글업로드완료");
            return "redirect:/audition/list";

		}
	}
	
	@GetMapping("/audition/delete/{auditionid}")
	public String delete(@PathVariable int auditionid, Model model){
		model.addAttribute("auditionid", auditionid);
		return "/audition/delete";
	}
	
	@PostMapping("/audition/{auditionid}")
	public String deletee(@PathVariable int auditionid){
		Audition audition = auditionRepository.findByAuditionid(auditionid);
				auditionRepository.delete(audition);
		
		return "redirect:/audition/list";
	}
	
	
}