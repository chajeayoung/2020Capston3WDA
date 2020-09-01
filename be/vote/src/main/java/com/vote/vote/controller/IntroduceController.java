package com.vote.vote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/introduce")
public class IntroduceController {
    @RequestMapping(value={"/blockChain","/blockChain/"})
	public String blockChain() {
		
		return "/introduce/blockChain";
	}
    
    @RequestMapping(value= {"/service","/service/"})
   public String service() {
	   
	   return "/introduce/service";
   }
}


