package com.vote.vote.controller;


import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vote.vote.db.dto.Hotclib;
import com.vote.vote.db.dto.Member;
import com.vote.vote.db.dto.Program;
import com.vote.vote.db.dto.ProgramManager;
import com.vote.vote.db.dto.Reply;
import com.vote.vote.db.dto.Rfile;
import com.vote.vote.repository.HotclibRepository;
import com.vote.vote.repository.MemberJpaRepository;
import com.vote.vote.repository.ProgramJpaRepository;
import com.vote.vote.repository.ProgramManagerJpaRepository;
import com.vote.vote.repository.ReplyRepository;
import com.vote.vote.repository.RfileRepository;
import com.vote.vote.service.StorageService;



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
	
	@Autowired
	private ProgramJpaRepository programRepository;

	// 게시글 리스트 보여주기
	// @GetMapping("/community/{program}/hotclib")
	// public String hotclib(Model model, @PageableDefault Pageable pageable) {
	// 	int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
	// 	pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "hotclibid"));
	// 	model.addAttribute("hotclibList", hotclibRepository.findAll(pageable));
	// 	model.addAttribute("rfile", rfileRepository.findAll());
	// 	return "hotclib/list";
	// }

	@GetMapping("/community/{program}/hotclib")
	public String hotclib(Model model, @PageableDefault Pageable pageable,@PathVariable int program) {
		// int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
		// pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "hotclibid"));
//		model.addAttribute("hotclibList", hotclibRepository.findAll(pageable));
//		model.addAttribute("rfile", rfileRepository.findAll());
		System.out.println(program);
		return "hotclib/list";
	}
	
	@ResponseBody
	@RequestMapping(value={"/community/{program}/hotclib/axios"})
	public JSONArray hotclibAxios(@PathVariable("program") int program) {
	
		System.out.println("sd");
		JSONArray result = new JSONArray();
		
		List<Hotclib> hotclibList = hotclibRepository.findByProgramid(program);

		for(Hotclib hotclib : hotclibList){
			JSONObject json = new JSONObject();
			json.put("program", hotclib.getProgramid());
			json.put("hotclibid", hotclib.getHotclibid());
			json.put("filename2", hotclib.getFilename2());
			json.put("htitle", hotclib.getHtitle());
			json.put("h_date", hotclib.getH_date());
			json.put("hviewcount", hotclib.getHviewcount());
			json.put("husername", hotclib.getHusername());
			
			result.add(json);
		}
		return result;
	}

	//관리자페이지 핫클립관리
	// @GetMapping("/hotclib/manager")
	// public String mhotclib(Model model){
	// 	model.addAttribute("hotclibList1", hotclibRepository.findAll());
	// 	return "hotclib/list1";
	// }

	// @GetMapping("/list1")
	// public String hotclib1(Model model, @PageableDefault Pageable pageable){
	// 	int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
	// 	pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "hcountview"));
	// 	model.addAttribute("hotclibList", hotclibRepository.findAll(pageable));
	// 	return "hotclib/list1";
	// }
	
	// 댓글 리스트,게시글 상세보기
	 @GetMapping("/community/{program}/hotclib/read/{hotclibid}")
	 public String read(Model model, @PathVariable int hotclibid,@PathVariable("program") int program) {
	 	Rfile rfile = rfileRepository.findByHotclibid(hotclibid);
	 	model.addAttribute("rfile", rfile);

	 	model.addAttribute("hotclib", hotclibRepository.findById(hotclibid));	
	 	List<Reply> reply = replyRepository.findByHotclibid(hotclibid);
	 	model.addAttribute("replyList", reply);
	 	Hotclib hotclib = hotclibRepository.findById(hotclibid);
	 	hotclib.setHviewcount(hotclib.getHviewcount() + 1);
	 	hotclibRepository.save(hotclib);
		System.out.println(hotclibid);
	 	return "hotclib/read";
	 }

	 @ResponseBody
	@RequestMapping(value={"/community/{program}/hotclib/read/{hotclibid}/axios"})
	public JSONObject hotclibreadAxios(@PathVariable("program") int program,
	@PathVariable int hotclibid) {
	
		System.out.println("가나다");
		JSONObject result = new JSONObject();
		JSONObject rfilee = new JSONObject();
		JSONObject hotclibb = new JSONObject();
		// List<Hotclib> hotclibList = hotclibRepository.findByProgramid(program);
		Rfile rfile = rfileRepository.findByHotclibid(hotclibid);
		List<Reply> replyList = replyRepository.findByHotclibid(hotclibid);
		Hotclib hotclib = hotclibRepository.findById(hotclibid);

		result.put("reply", replyList);
		result.put("rfile", rfile);
		result.put("hotclib", hotclib);
		
		return result;
	}
		
	

		


	 

//	댓글등록
	 @PostMapping("/community/{programid}/hotclib/read/{hotclibid}")
	 public String read(Reply reply,
	 @PathVariable int hotclibid,
	 @PathVariable int programid,
	 SessionStatus sessionStatus,
	 Principal principal){
	 	    String userid = principal.getName(); 
	 		Member member = memberRepository.findByUserid(userid); 
	 		int r_id = member.getNo();
	 		reply.setR_id(r_id);
			
	 		reply.setRusername(member.getName());	
	 	reply.setR_date(new Date());	
	 	replyRepository.saveAndFlush(reply);
	 	Hotclib hotclib = hotclibRepository.findById(hotclibid);

	 	hotclib.setHreplycount(hotclib.getHreplycount() + 1);
	 	hotclibRepository.save(hotclib);
	 	 replyRepository.findById(reply.getReplyid());
	 	 reply.setR_content(reply.getR_content());
	 	 replyRepository.save(reply);

	 	 Hotclib hotclib1 = new Hotclib();
	 	 hotclib1.setHotclibid(hotclibid);
	 	sessionStatus.setComplete();
		
	 	return "redirect:/community/{programid}/hotclib/read/{hotclibid}";
		
		
	 }

	//게시글업로드
	 @GetMapping("/community/{programid}/hotclib/upload")
	 public String upload(Model model, @PathVariable int programid){
	 	model.addAttribute("hotclib", new Hotclib());
//	 	System.out.println(program);
	 	return "/hotclib/upload";
	 } 

	//게시글업로드
	 @PostMapping("/community/{programid}/hotclib/upload")
	 public String upload(
	 	@RequestParam(name="filename2") MultipartFile filename2,
	 	@RequestParam(name="filename") MultipartFile filename,
	 	@RequestParam(name="htitle") String htitle,
	 	@RequestParam(name="h_content") String h_content,
	 	@RequestParam(name="h_reply") String h_reply,
	 	@PathVariable int programid,
	 	RedirectAttributes redirAttrs,
	 	SessionStatus sessionStatus,
	 	Principal principal){
	 	String filename2Path = storageService.store2(filename2);
	 	Member member = memberRepository.findByUserid(principal.getName());
	 	ProgramManager pm = pmRepository.findById(member.getNo());
		
	 	Hotclib hotclib= new Hotclib();
		// //핫클립 테이블에 프로그램아이디, 사용자아이디, 날짜, 파일이름2저장
		 hotclib.setHusername(member.getName());
		 hotclib.setProgramid(pm.getProgramId());
		 hotclib.setNo(member.getNo());
		 hotclib.setHtitle(htitle);
		 hotclib.setH_content(h_content);
		 hotclib.setH_reply(h_reply);
		 hotclib.setH_date(new Date());		
		 hotclib.setFilename2(filename2Path);
		 hotclibRepository.saveAndFlush(hotclib);  // 저장하고 커밋까지 Flush
 
		 //Rfile 테이블에 핫클립번호 파일이름 저장
		 Rfile rfile = new Rfile();
		 String filenamePath = storageService.store2(filename);
		 rfile.setHotclibid(hotclib.getHotclibid());
		 rfile.setFilename(filenamePath); 
		
		 rfileRepository.saveAndFlush(rfile);
		 sessionStatus.setComplete();
		 return "redirect:/community/{programid}/hotclib";
		 }
		
	//게시글삭제
	 @GetMapping("/community/{programid}/hotclib/delete/{hotclibid}")
	 public String delete(@PathVariable int hotclibid, @PathVariable int programid,Model model){
	 	model.addAttribute("hotclibid", hotclibid);
	 	return "hotclib/delete";
	 }

	//게시글삭제
	 @PostMapping("/community/{programid}/hotclib/{hotclibid}")
	 public String delete(@PathVariable int hotclibid, 
			 @PathVariable int programid, 
			 Integer fileid, 
			 Integer replyid){
	 	hotclibRepository.deleteById(hotclibid);
	 	return "redirect:/community/{programid}/hotclib";
	
	 }

	//제목으로 검색
	 @GetMapping("/community/{programid}/hotclib/search")
	 public String search(@RequestParam(value="keyword") String keyword, Model model,
			 @PathVariable int programid){
	 	List<Hotclib> hotclib = hotclibRepository.findByHtitle(keyword);
	 	model.addAttribute("hotclibList", hotclib);
	 	return "hotclib/list";
	 }

	//게시글수정
	 @GetMapping("/community/{programid}/hotclib/update/{hotclibid}")
	 public String update(Model model, @PathVariable int hotclibid
			 , @PathVariable int programid){
	 	Hotclib hotclib = hotclibRepository.findById(hotclibid);
	 	model.addAttribute("hotclib", hotclib);		
	 	return "hotclib/update";
	 }
	//게시글수정
	 @PostMapping("/community/{programid}/hotclib/update/{hotclibid}")
	 public String update(Hotclib hotclib, BindingResult bindingResult
			 , @PathVariable int programid){
	 	if (bindingResult.hasErrors()) {
	 		return "hotclib/update";
	 	} else {
	 		hotclib.setH_date(new Date());
			
	 	hotclibRepository.save(hotclib).getHotclibid(); 
	 	return "redirect:/community/{programid}/hotclib";
	 	}
	 }	

	//댓글삭제
	 @PostMapping("/community/{programid}/hotclib/read/{hotclibid}/{replyid}")
	 public String replydelete(@PathVariable int hotclibid,
			 @PathVariable int replyid, 
			 @PathVariable int programid,
			 Model model){
	 	replyRepository.deleteById(replyid);
	 	Hotclib hotclib = hotclibRepository.findById(hotclibid);
	 	hotclib.setHotclibid(hotclibid);
		
	 	hotclib.setHreplycount(hotclib.getHreplycount()- 1);
	 	hotclibRepository.save(hotclib);
	 	return "redirect:/community/{programid}/hotclib/read/{hotclibid}";
	 }

	//댓글수정
	 @GetMapping("/community/{programid}/hotclib/replyupdate/{hotclibid}/{replyid}")
	 public String replyupdate(@PathVariable int hotclibid, 
			 @PathVariable int replyid, 
			 @PathVariable int programid, 
			 Model model){
	 	Hotclib hotclib = hotclibRepository.findById(hotclibid);
	 	model.addAttribute("hotclib", hotclib);		
	 	return "/community/{programid}/hotclib/read/{hotclibid}";
	
	 }
	
	//댓글수정
	 @PostMapping("/community/{programid}/hotclib/replyupdate/{hotclibid}/{replyid}")
	 public String replyupdate(Model model, 
	 @PathVariable int hotclibid,
	 @PathVariable int replyid,
	 @PathVariable int programid,
	 Reply reply,
	 Principal principal){
	 	String userid = principal.getName(); 
	 	Member member = memberRepository.findByUserid(userid); 
	 	int r_id = member.getNo();
	 	reply.setR_id(r_id);

	 	replyRepository.findById(reply.getReplyid());
	 	reply.setR_content(reply.getR_content());
	 	reply.setR_date(new Date());	
	 	replyRepository.save(reply);

	 	Hotclib hotclib = new Hotclib();
	 	hotclib.setHotclibid(hotclibid);
	 	return "redirect:/community/{programid}/hotclib/read/{hotclibid}";
	 }
	
}