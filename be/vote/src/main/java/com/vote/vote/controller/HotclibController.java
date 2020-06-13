package com.vote.vote.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.vote.vote.config.CustomUserDetails;
import com.vote.vote.db.dto.Hotclib;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.ProgramManager;
import com.vote.vote.db.dto.Reply;
import com.vote.vote.db.dto.Rfile;
import com.vote.vote.repository.HotclibRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.ProgramManagerJpaRepository;
import com.vote.vote.repository.ReplyRepository;
import com.vote.vote.repository.RfileRepository;
import com.vote.vote.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HotclibController {
	
	@Autowired
	private HotclibRepository hotclibRepository;

	@Autowired
	private StorageService storageService;

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private RfileRepository rfileRepository;

	@Autowired
	private MemberJpaRepository memberRepository;

	@Autowired
	private ProgramManagerJpaRepository pmRepository;
	
	@GetMapping("/hotclib")
	public String hotclib(Model model, @PageableDefault Pageable pageable){
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); 
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "hotclibid"));
		model.addAttribute("hotclibList", hotclibRepository.findAll(pageable));
		model.addAttribute("rfiles", rfileRepository.findAll());
		return "hotclib/list";
	}
	
	@GetMapping("/hotclib/read/{hotclibid}")
	public String read(Model model, @PathVariable int hotclibid, String filename) {
		
		model.addAttribute("rfile", rfileRepository.findByFilename(filename));
		model.addAttribute("hotclib", hotclibRepository.findById(hotclibid));	
		List<Reply> reply = replyRepository.findByHotclibid(hotclibid);
		model.addAttribute("replyList", reply);
		Hotclib hotclib = hotclibRepository.findById(hotclibid);
		hotclib.setHviewcount(hotclib.getHviewcount() + 1);
		hotclibRepository.save(hotclib);
		
		return "hotclib/read";
	}

	@PostMapping("/hotclib/read/{hotclibid}")
	public String read(
	@PathVariable int hotclibid,
	 BindingResult bindingResult, 
	SessionStatus sessionStatus,
	Principal principal){
		Reply reply = new Reply();
		    String userid = principal.getName(); 
			Member member = memberRepository.findByUserid(userid); 
			int r_id = member.getNo();
			reply.setR_id(r_id);
			
		 if (bindingResult.hasErrors()) {
		 	return "hotclib/read/{hotclibid}";
		 } else {
		
		reply.setR_date(new Date());	
		replyRepository.saveAndFlush(reply);
		 sessionStatus.setComplete();
		
		return "redirect:/hotclib/read/{hotclibid}";
		
		}
	}


	@GetMapping("/hotclib/upload")
	public String upload(Model model){
		model.addAttribute("hotclib", new Hotclib());
		return "hotclib/upload";
	}

	@PostMapping("/hotclib/upload")
	public String upload(
		@RequestParam(name="filename2") MultipartFile filename2,
		@RequestParam(name="filename") MultipartFile filename,
		@RequestParam(name="htitle") String htitle,
		@RequestParam(name="h_content") String h_content,
		RedirectAttributes redirAttrs,
		SessionStatus sessionStatus,
		Principal principal){
		String filename2Path = storageService.store2(filename2);
		Member member = memberRepository.findByUserid(principal.getName());
		ProgramManager pm = pmRepository.findById(member.getNo());
		Hotclib hotclib= new Hotclib();
		// //핫클립 테이블에 프로그램아이디, 사용자아이디, 날짜, 파일이름2저장
		hotclib.setProgramid(pm.getProgramId());
		hotclib.setNo(member.getNo());
		hotclib.setHtitle(htitle);
		hotclib.setH_content(h_content);
		hotclib.setH_date(new Date());		
		hotclib.setFilename2(filename2Path);
		hotclibRepository.saveAndFlush(hotclib);  // 저장하고 커밋까지 Flush

		// //Rfile 테이블에 핫클립번호 파일이름 저장
		Rfile rfile = new Rfile();
		String filenamePath = storageService.store2(filename);
		rfile.setHotclibid(hotclib.getHotclibid());
		rfile.setFilename(filenamePath); 
		
		rfileRepository.saveAndFlush(rfile);
		sessionStatus.setComplete();
		return "redirect:/hotclib";
		}
		

	@GetMapping("/hotclib/delete/{hotclibid}")
	public String delete(@PathVariable int hotclibid,Model model){
		model.addAttribute("hotclibid", hotclibid);
		return "hotclib/delete";
	}

	@PostMapping("/hotclib/{hotclibid}")
	public String delete(@PathVariable int hotclibid){
		
		hotclibRepository.deleteById(hotclibid);
		return "redirect:/hotclib";
	
	}
	
	@GetMapping("/hotclib/search")
	public String search(@RequestParam(value="keyword") String keyword, Model model){
		List<Hotclib> hotclib = hotclibRepository.findByHtitle(keyword);
		model.addAttribute("hotcliblist", hotclib);
		return "hotclib/list";
	}

	
	@GetMapping("/hotclib/update/{hotclibid}")
	public String update(Model model, @PathVariable int hotclibid){
		Hotclib hotclib = hotclibRepository.findById(hotclibid);
		model.addAttribute("hotclib", hotclib);		
		return "hotclib/update";
	}

	@PostMapping("/hotclib/update/{hotclibid}")
	public String update(BindingResult bindingResult){
		Hotclib hotclib = new Hotclib();
		if (bindingResult.hasErrors()) {
			return "hotclib/update";
		} else {
			hotclib.setH_mdate(new Date());
			
		hotclibRepository.save(hotclib).getHotclibid(); 
		return "redirect:/hotclib";
		}
	}	
	
	@PostMapping("/hotclib/read/{hotclibid}/{replyid}")
	public String replydelete(@PathVariable int hotclibid, @PathVariable int replyid, Model model){
		replyRepository.deleteById(replyid);
		Hotclib hotclib = new Hotclib();
		hotclib.setHotclibid(hotclibid);
		return "redirect:/hotclib/read/{hotclibid}";
	}

}