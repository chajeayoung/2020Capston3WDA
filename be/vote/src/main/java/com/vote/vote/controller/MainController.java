package com.vote.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Audition;
import com.vote.vote.db.dto.ProgramManager;
import com.vote.vote.repository.AuditionConJpaRepository;
import com.vote.vote.repository.AuditionJpaRepository;
import com.vote.vote.repository.CustomAuditionJpaRepository;
import com.vote.vote.repository.ProgramManagerJpaRepository;



@Controller
public class MainController {

	@Autowired
	AuditionJpaRepository auditionRepository;
	
	@Autowired
	AuditionConJpaRepository auditionConRepository;
	
	@Autowired
	ProgramManagerJpaRepository pmRepository;
	
	@Autowired
	CustomAuditionJpaRepository caRepository;
	
	@RequestMapping("/")
	public String index(Principal user, @PageableDefault Pageable pageable,Model model) {
		// System.out.println("/ --> index");
		// if(user != null){
		// 	// UserDetails u = (UserDetails)user;
		// 	System.out.println(u);
		// }

	if(user != null){

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				CustomUserDetails sessionUser = (CustomUserDetails)principal;
				ProgramManager pManager = pmRepository.findById(sessionUser.getR_ID());
				
				if(sessionUser.getROLE().equals("2")) {
					int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); 
					pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "auditionid"));
					model.addAttribute("auditionlist", auditionRepository.findByProgramidOrderByAuditionidDesc(pManager.getProgramId(),pageable));
					
									
					System.out.println(auditionRepository.findAll(pageable));
					
			        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "formid"));
					model.addAttribute("auditionconlist", caRepository.findByRid(auditionRepository.findByProgramid(pManager.getProgramId()),pageable));

		  }
	}

//	@GetMapping("/audition/list")
//	public String audition(Model model, @PageableDefault Pageable pageable, Authentication authentication){
//
//		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//		ProgramManager pManager = pmRepository.findById(userDetails.getR_ID());
//
//		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); 
//		pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "auditionid"));
//		// model.addAttribute("auditionlist", auditionRepository.findAll(pageable));
//		model.addAttribute("auditionlist", auditionRepository.findByProgramid(pManager.getProgramId(),pageable));
//		
//		
//		
//		return "audition/list";
//	}
		

		return "main_index";
	}
	// @RequestMapping("/about")
	// public String about() {
	// 	// System.out.println("/ --> home");
	// 	return "about";
	// }
	// @RequestMapping("/blog")
	// public String blog() {
	// 	// System.out.println("/ --> home");
	// 	return "blog";
	// }
	// @RequestMapping("/contact")
	// public String contact() {
	// 	// System.out.println("/ --> home");
	// 	return "contact";
	// }
	
	
}