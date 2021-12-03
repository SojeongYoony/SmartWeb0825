package com.koreait.ex05.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.ex05.domain.Member;

@Controller
public class MemberController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	/* AJAX 처리할 때는 VIEW를 반환하지 않는다 */
	// AJAX는 데이터를 반환한다.
	// text/plain, text/xml, application/json 등   -- data반환 타입
	@ResponseBody // 내가 지금 반환하는 String은 View가 아님!!을 알려준다. == view를 반환하지 않는다 데이터를 반환한다
	@GetMapping(value = "member/v1.do",
				produces = "text/plain; charset=UTF-8") // AJAX의 dataType과 SET 뭔가를 만든다...! produces text/plain	
	public String v1(@RequestParam(value = "id") String id) {
		String result = "입력된 아이디는 " + id + "입니다.";
		return result;	// 문자열을 반환한다. -- text/plain 반환
	}
	/* 방법 2, 방법 3
	@GetMapping("member/v1.do")
	public String v2(HttpServletRequest request) {
		return "index";
	}
	@GetMapping("member/v1.do")
	public String v2(Member member) {
		return "index";
	}
	*/
	
	@ResponseBody
	@GetMapping(value = "member/v2.do",
			 produces = "application/json; charset=UTF-8")
	public Member v2(Member member) {
		return member;  // 반환되는 bean은 jackson data-bind에 의해서 json으로 변환됨 : HashMap / Bean 자동 변환 해 줌 
	}
	
	@ResponseBody
	@GetMapping(value = "member/v3.do", 
			 produces = "application/json; charset=UTF-8")
	public Map<String, String> v3(HttpServletRequest request) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("id", request.getParameter("id"));
		resultMap.put("pw", request.getParameter("pw"));
		return resultMap; // 반환되는 Map은 jackson data-bind에 의해서 json으로 변환됨
	}
	
	@ResponseBody
	@PostMapping(value = "member/v4.do",
			  produces = "application/json; charset=UTF-8")
	public Map<String, Object> v4(@RequestBody Member member){ // 전달된 json 데이터는 jackson data-bind에 의해서 bean으로 변환된다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", member.getId());
		map.put("pw", member.getPw());
		map.put("addOn", 1000);  	// Map의 장점 : 추가 데이터를 실어서 보낼 수 있다 DTO보다 MAP으로 작업하는 것이 좋다 (String / int 타입이 섞여 있기 때문에 Object를 씀)
		return map;
	}
	
}
