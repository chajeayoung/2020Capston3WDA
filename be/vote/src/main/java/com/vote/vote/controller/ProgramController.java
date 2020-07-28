package com.vote.vote.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Company;
import com.vote.vote.db.dto.Member;
import com.vote.vote.klaytn.Klaytn;
import com.vote.vote.repository.CompanyJpaRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.service.KakaoAPIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.vote.vote.db.customSelect.CustomPopularBoard;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/proRegIndex")
public class ProgramController {
	
    @Autowired
    MemberJpaRepository memberRepository;
    
    @Autowired
    CompanyJpaRepository companyRepository;
    
    @RequestMapping(value={"","/"})
	public String index(Principal principal, Model model) {
    	String userid = principal.getName(); // 세션에있는 사용자 이메일
    	System.out.println(userid);
    	
    	Member member = memberRepository.findByUserid(userid);
    	System.out.println(member.toString());
    	
    	int r_id = member.getNo();
    	System.out.println(r_id);
    	Long c = companyRepository.countByRid(r_id);
    	
    	System.out.println(c);
    	
    	model.addAttribute("haveProgram",c);

    	
		return "role/proRegIndex";
	}
  
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerOk(Company cc, RedirectAttributes redirAttrs, Principal principal){
       	
    	String userid = principal.getName(); // 세션에있는 사용자 이메일
    	System.out.println(userid);
    	Member member = memberRepository.findByUserid(userid); //세션에 있는 사용자 이메일로 사용자정보를 가져옴
    	int r_id = member.getNo();
    	System.out.println(member.getNo()); // 사용자 고유번호를 가져옴
    	System.out.println(member.toString());

    	System.out.println("proRegIndex/register 입니다.");        
       
        if(companyRepository.findById(cc.getId()) == null){
        		
        	cc.setCconfirm("0");
        	cc.setRid(r_id);
        	System.out.println(cc.toString());
        	
            companyRepository.saveAndFlush(cc);
        	redirAttrs.addFlashAttribute("message", "프로그램 신청완료");
            return "redirect:/proRegIndex";
        }else{
            redirAttrs.addFlashAttribute("message", "사업자 등록번호 중복입니다.");
           return "redirect:/proRegindex/register";
        }

    }
    
    //사업자번호 중복확인
    @ResponseBody
    @RequestMapping(value= {"/register/checkId/{id}","/register/checkId/{id}/"},
    method=RequestMethod.GET,
   produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject register_check_id(@PathVariable("id") int id){ 
        JSONObject result = new JSONObject();
        System.out.println(id);
        
        Company company = companyRepository.findById(id);
        if(company == null){
            result.put("message", "등록가능한 사업자입니다.");
            result.put("check", 1);
        }else{
            result.put("message", "이미 존재하는 사업자입니다.");
            result.put("check", 0);
        }
        System.out.println(result);
        return  result;
    }
     
}