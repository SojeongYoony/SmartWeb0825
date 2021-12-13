package com.koreait.ex13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.ex13.domain.Member;
import com.koreait.ex13.service.MemberService;

@Controller
@RequestMapping("member/*")
public class MemberController {
	@Autowired
	private MemberService service;

	@GetMapping("loginPage")
	public String loginPage() {
		return "member/login";
	}
	
	@GetMapping("joinPage")
	public String joinPage() {
		return "member/join";
	}

	@PostMapping(value="idCheck", produces="application/json; charset=UTF-8") 
	@ResponseBody
	public Map<String, Object> idCheck(@RequestParam("id") String id) {
		return service.idCheck(id);
	}
	
	@PostMapping(value ={"emailCheck", "findId"}, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> findMemberByEmail(@RequestParam("email") String email){
		return service.findMemberByEmail(email);
	}
	
	@PostMapping(value = "sendAuthCode", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> sendAuthCode(@RequestParam("email") String email){
		return service.sendAuthCode(email);
	}
	
	@PostMapping(value = "join") // 회원가입 후 page 이동 할 것.
	public String join(Member member) {
		service.join(member);
		return "redirect:/"; // index.jsp로 redirect 이동
	}
	
	@PostMapping(value="login")
	public String login(HttpServletRequest request) {
//		public String login(Member member, HttpSession session) { // login정보는 member(dto)로 받고 session에 저장하여 바로 보내는 방법
		service.login(request);
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) { // session을 선언하여 바로 session사용할 수 있음. 굳이 request에서 빼서 쓰지 않아도 됨
		if ( session.getAttribute("loginUser") != null ) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@GetMapping("findIdPage")
	public String findIdPage() {
		return "member/findId";
	}
	
	@GetMapping("findPwPage")
	public String findPwPage() {
		return "member/findPw";
	}
	
	@GetMapping("updatePwPage")
	public String updatePwPage(@ModelAttribute("email") String email) { // param으로 오는 걸 다시 Model에 실어줌
		return"member/updatePw";
	}
	
	@PostMapping("updatePw")
	public String updatePw(Member member) {
		service.updatePw(member);
		return "redirect:loginPage";
	}
	
	@GetMapping("myPage")
	public String myPage() {
		return "member/myPage";
	}
	
	@PostMapping("updateMember")
	public String updateMember(Member member, HttpSession session) { // no, name, email
		service.updateMember(member, session);
		return "redirect:/";
	}
	
	@GetMapping("leaveMember")
	public String leaveMember(@RequestParam("no")Long no, HttpSession session) {
		service.leaveMember(no, session);
		if ( session.getAttribute("loginUser") != null ) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	
	
	
	
}
