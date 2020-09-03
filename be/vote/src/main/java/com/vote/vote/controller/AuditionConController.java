package com.vote.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Audition;
import com.vote.vote.db.dto.AuditionCon;
import com.vote.vote.db.dto.AuditionOption;
import com.vote.vote.db.dto.AuditionOptionValue;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Popular;
import com.vote.vote.repository.AuditionConJpaRepository;
import com.vote.vote.repository.AuditionJpaRepository;
import com.vote.vote.repository.AuditionOptionJpaRepository;
import com.vote.vote.repository.AuditionOptionValueJpaRepository;
import com.vote.vote.repository.CustomAuditionOptionRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.ProgramManagerJpaRepository;
import com.vote.vote.repository.PopularJpaRepository;
import com.vote.vote.service.StorageService;
import com.vote.vote.db.dto.Program;
import com.vote.vote.db.dto.ProgramManager;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import groovyjarjarcommonscli.OptionBuilder;

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

	@Autowired
	private PopularJpaRepository popularRepository;
	
	
	@RequestMapping("/sendAddress")
	public String serch1(@RequestParam(value = "confirm") String confirm, Model model) {
		List<AuditionCon> auditioncon = auditionConRepository.findByConfirm(confirm);
		System.out.println(confirm);
		model.addAttribute("auditionconlist", auditioncon);

		return "audition_con/list";
	}

	// @RequestMapping(value={"/sendAddress"},method = RequestMethod.POST)
	// public String messageCenterHome(Model model,HttpSession
	// session,HttpServletRequest request) {

	// String selectedCity= request.getParameter("confirm");
	// return "/audition_con/list";
	// }

	@GetMapping("/audition_con/list")
	public String audition(Model model, @PageableDefault Pageable pageable, Authentication authentication) {

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		ProgramManager pManager = pmRepository.findById(userDetails.getR_ID());

		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "formid"));
		// model.addAttribute("auditionconlist", auditionConRepository.findAll(pageable));
		model.addAttribute("auditionconlist", auditionConRepository.findByProgramid(pManager.getProgramId(),pageable));
		
		return "/audition_con/list";
	}

	// @RequestMapping("/audition_con/list")
	// public String list(Model model) { // 2~ n
	// model.addAttribute("auditionconlist",auditionConRepository.findAll());
	// return "/audition_con/list";
	// }

	@RequestMapping("/audition_con/listm")
	public String listmm(@Valid AuditionCon auditionCon, Integer rid, Model model) { // only 1

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CustomUserDetails sessionUser = (CustomUserDetails) principal;

		System.out.println(sessionUser.getR_ID());
		AuditionCon auditioncon = auditionConRepository.findByRidOrderByFormidDesc(sessionUser.getR_ID());

		System.out.println(auditioncon.toString());
		model.addAttribute("auditionconlistm", auditioncon);

		// System.out.println(sessionUser.getR_ID()+"의 오디션 폼의 오디션
		// fk는"+auditioncon.getAuditionid());

		return "/audition_con/listm";

	}

	@GetMapping("/audition_con/read/{formid}")
	public String read(Model model, @PathVariable int formid) {
		AuditionCon auditioncon = auditionConRepository.findByFormid(formid);
		// Popular popular = popularRepository.findByRid(auditioncon.getRid());

		System.out.println("auditioncon.getAuditionid(): " + auditioncon.getAuditionid());
		model.addAttribute("auditionCon", auditionConRepository.findByFormid(formid));
		// model.addAttribute("popular", popularRepository.findByRid(auditioncon.getRid()));
		// model.addAttribute("popular", popular);
		model.addAttribute("options",
				customAuditionOptionRepository.getOptionWithValue(auditioncon.getAuditionid(), formid));

		return "audition_con/read";
	}

	@GetMapping("/audition_con/form/{auditionId}")
	public String form(Model model, @PathVariable int auditionId, Principal principal) {

		AuditionCon auditionCon = new AuditionCon();
		Audition audition = auditionRepository.findByAuditionid(auditionId);
		Member member = memberRepository.findByUserid(principal.getName());
		model.addAttribute("auditionCon",new AuditionCon());
		model.addAttribute("audition",audition);
		model.addAttribute("auditionId", auditionId);
		model.addAttribute("member", member);
//		model.addAttribute("addr", member.getAddr());
//		model.addAttribute("gender", member.getGender());
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
				
			Member member = memberRepository.findByUserid(principal.getName());
			Audition audition = auditionRepository.findByAuditionid(auditionId);
			auditioncon.setProgramid(audition.getProgramid());
			
			
			
			System.out.println(" 신청인 정보 : "+auditioncon);


		if(bindingResult.hasErrors()) {
			return "/audition_con/form";
		} else if(filename.isEmpty()) {
			auditioncon.setRid(member.getNo());
			auditioncon.setUsername(member.getName());
			
			auditioncon.setFdate(new Date());
			auditioncon.setFusername(member.getName());
			auditioncon.setFuserphone(member.getPhone());
			System.out.println(auditioncon.toString());
			auditionConRepository.save(auditioncon);
			sessionStatus.setComplete();
			return "redirect:/audition/complete";
		} else {
			
		    String filenamePath = StringUtils.cleanPath(filename.getOriginalFilename());
            // Member member = memberRepository.findByUserid(principal.getName());
			auditioncon.setUsername(member.getName());
			
            // 게시글저장

            auditioncon.setRid(member.getNo());

            auditioncon.setFprofile(filenamePath);
            
            auditionConRepository.saveAndFlush(auditioncon);

         	auditioncon.setFdate(new Date());
			auditioncon.setAuditionid(auditionId);
			auditioncon.setRid(Integer.valueOf(userDetails.getR_ID()));
			auditioncon.setFusername(member.getName());
			auditioncon.setFuserphone(member.getPhone());
			
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
					auditioncon.setFusername(member.getName());
			auditioncon.setFuserphone(member.getPhone());
					result.setAuditionCon(auditioncon.getFormid());
					auditionOptionValueRepository.saveAndFlush(result);
				}			
			}
				

            System.out.println("게시글업로드완료");
            return "redirect:/audition/complete";
            

		}
	}
	
	@GetMapping("/audition_con/show/{formid}")
	public String show(@PathVariable int formid, Model model){

		AuditionCon auditioncon = auditionConRepository.findByFormid(formid);
		System.out.println("auditioncon.getAuditionid(): "+auditioncon.getAuditionid());
		model.addAttribute("auditionCon", auditionConRepository.findByFormid(formid));
		model.addAttribute("options", customAuditionOptionRepository.getOptionWithValue(auditioncon.getAuditionid(),formid));
		
		return "audition_con/show";
	}
	
		
		
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


	// 오디션 관리자가 합격/불합격 판단 관련 
	@GetMapping("/audition_con/update/{formid}")
	public String con_update_view(Model model, @PathVariable int formid){
		AuditionCon auditioncon = auditionConRepository.findByFormid(formid);
		model.addAttribute("auditionCon", auditioncon);		
		return "audition_con/list";
	}

	@PostMapping("/audition_con/update/{formid}")
	public String con_update_ok(AuditionCon auditioncon, BindingResult bindingResult,
	@PathVariable int formid
	){
		if (bindingResult.hasErrors()) {
			return "/audition_con/list";
		} else {
			AuditionCon con = auditionConRepository.findByFormid(formid);
			con.setConfirm(auditioncon.getConfirm());
			auditionConRepository.save(con);
		return "redirect:/audition_con/list";
		}
	}
	// 오디션 신청서 수정 부분	
	@GetMapping(value={"/myAudition/update/{conId}","/myAudition/update/{conId}/"})
	public String edit(@PathVariable int conId, Model model) {
		
		AuditionCon auditioncon = auditionConRepository.findByFormid(conId);
		System.out.println("auditioncon.getAuditionid(): "+auditioncon.getAuditionid());
		model.addAttribute("auditionCon", auditionConRepository.findByFormid(conId));
		model.addAttribute("options", customAuditionOptionRepository.getOptionWithValue(auditioncon.getAuditionid(),conId));

		return "/audition_con/edit";
		
	}
	@PostMapping(value={"/myAudition/update/{conId}","/myAudition/update/{conId}/"})
	public String update(@PathVariable int conId, Model model, AuditionCon auditionCon,@Nullable @RequestParam("option") String[] options,
	@RequestParam(name = "profile") MultipartFile[] file) {

		System.out.println(auditionCon);
		AuditionCon con = auditionConRepository.findByFormid(conId);
		con.setFtitle(auditionCon.getFtitle());
		// con.setFprofile(fprofile);
		con.setFuserphone(auditionCon.getFuserphone());
		con.setFusermail(auditionCon.getFusermail());
		con.setFusername(auditionCon.getFusername());
		con.setFaddr(auditionCon.getFaddr());
		con.setFeducation(auditionCon.getFeducation());
		con.setFgender(auditionCon.getFgender());
		con.setFheight(auditionCon.getFheight());
		con.setFweight(auditionCon.getFweight());
		con.setFblood(auditionCon.getFblood());
		con.setFfamily(auditionCon.getFfamily());
		con.setFhobby(auditionCon.getFhobby());
		con.setFability(auditionCon.getFability());
		con.setIntroduce(auditionCon.getIntroduce());
		con.setBirth(auditionCon.getBirth2());

		auditionConRepository.saveAndFlush(con);
		if(!file[0].isEmpty()){
			String filenamePath = StringUtils.cleanPath(file[0].getOriginalFilename());
			storageService.store2(file[0]);
			con.setFprofile(filenamePath);

		}
		if(options != null && !options[0].isEmpty()){
			List<AuditionOption> audiOptions = auditionOptionReopository.findByAuditionIdOrderByNo(con.getAuditionid());
			List<AuditionOptionValue> optionValues = auditionOptionValueRepository.findByAuditionConOrderByNo(conId);
			
			int count = audiOptions.size() - optionValues.size();

			for(int i =0 ; i < optionValues.size(); i++){ // 기존의 옵션에 수정

				AuditionOptionValue value = optionValues.get(i);
				AuditionOption audiOption = audiOptions.get(i);

				value.setOptionNo( audiOption.getNo() );
				value.setValue( options[i] );

				auditionOptionValueRepository.saveAndFlush(value);
			}

			for(int i=0; i<count; i++){ // 추가적인 옵션이 생겼을 때 
				AuditionOptionValue value = new AuditionOptionValue();
				value.setAuditionCon(con.getFormid());
				value.setAuditionId(con.getAuditionid());
				value.setOptionNo(audiOptions.get(i+optionValues.size()).getNo());
				value.setValue(options[i+optionValues.size()]);

				auditionOptionValueRepository.saveAndFlush(value);
			}

		}
		
		
		


		return "redirect:/userInfo/myAudition";
		
	}
	
	@GetMapping("/audition_con/serch")
	public String serch(@RequestParam(value="합격") String 합격, Model model) {
		List<AuditionCon> auditioncon = auditionConRepository.findByConfirm("합격");
		
			model.addAttribute("auditionconlist", auditioncon);
			
			return "audition_con/list";
	}

	
}