package com.vote.vote.controller;

import java.security.Principal;

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
import com.vote.vote.repository.AuditionJpaRepository;



@Controller
public class MainController {

	@Autowired
	AuditionJpaRepository auditionRepository;
	
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
						model.addAttribute("auditionlist", auditionRepository.findAll());

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