package com.vote.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Audition;
import com.vote.vote.repository.AuditionConJpaRepository;
import com.vote.vote.repository.AuditionJpaRepository;



@Controller
public class MainController {

	@Autowired
	AuditionJpaRepository auditionRepository;
	
	@Autowired
	AuditionConJpaRepository auditionConRepository;
	
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
		
				if(sessionUser.getROLE().equals("2")) {
					int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); 
					pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "auditionid"));
					model.addAttribute("auditionlist", auditionRepository.findAll(pageable));
					
									
					System.out.println(auditionRepository.findAll(pageable));
					
			        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "formid"));
					model.addAttribute("auditionconlist", auditionConRepository.findAll(pageable));

		  }
	}

		

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